package HIENONIMI;

import HIENONIMI.database.AiheDao;
import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import HIENONIMI.database.Database;
import HIENONIMI.database.AlueDao;
import HIENONIMI.database.KayttajaDao;
import HIENONIMI.database.ViestiDao;
import HIENONIMI.domain.Aihe;
import HIENONIMI.domain.Kayttaja;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
         if (System.getenv("PORT") != null) {
            port(Integer.valueOf(System.getenv("PORT")));
        }
        
        Database database = new Database("jdbc:sqlite:HIENONIMI.db");
        database.init();

        AlueDao alueDao = new AlueDao(database);
        AiheDao aiheDao = new AiheDao(database);
        KayttajaDao kayttajaDao = new KayttajaDao(database);
        ViestiDao viestiDao = new ViestiDao(database);

        get("/", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("alueet", alueDao.findAll());

            return new ModelAndView(map, "index");
        }, new ThymeleafTemplateEngine());

        get("/:id", (req, res) -> {
            HashMap map = new HashMap<>();
            try {
                map.put("aiheet", aiheDao.findAll(Integer.parseInt(req.params(":id"))));
            } catch (NumberFormatException e) {
                //miksi tässä koitetaan parsia faviconia??
            }

            return new ModelAndView(map, "alue");
        }, new ThymeleafTemplateEngine());

        get("/:id/:aid", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("alue", req.params(":id"));
            map.put("aihe", req.params(":aid"));
            try {
                map.put("viestit", viestiDao.naytaKymmenen(Integer.parseInt(req.params(":aid")), Integer.parseInt(req.queryParams("sivu"))));
            } catch (Exception e) {
                map.put("viestit", viestiDao.naytaKymmenen(Integer.parseInt(req.params(":aid"))));
            }
            List<Integer> sivut = viestiDao.sivunumerot(Integer.parseInt(req.params(":aid")));
            //jos sivujen määrä = 1, niin ei lisätä sivuja joten yksisivuisessa threadissä ei näy sivunumeroa
            if (sivut.size() > 1) {
                map.put("sivut", sivut);
            } 
            return new ModelAndView(map, "aihe");
        }, new ThymeleafTemplateEngine());

        post("/:id/:aid", (req, res) -> {
            String kayttaja = req.queryParams("kayttaja");
            String viesti = req.queryParams("viesti");
            //pitäis lisätä stringien pituustestit
            if (!kayttaja.isEmpty() && !viesti.isEmpty()) {
                int aiheId = Integer.parseInt(req.params(":aid"));
                Kayttaja nykyinen = haeTaiTeeKayttaja(kayttaja, kayttajaDao);
                viestiDao.teeUusi(nykyinen.getId(), aiheId, viesti);
            } else {
                //pitäiskö antaa erroria tai jotain
            }
            //pitäis varmaan ohjata viimeiselle sivulle
            String takas = "/" + req.params(":id") + "/" + req.params(":aid");
            res.redirect(takas);
            return "";
        });

        post("/:id", (req, res) -> {
            String kayttaja = req.queryParams("kayttaja");
            String aihe = req.queryParams("aihe");
            String viesti = req.queryParams("viesti");
            //pitäis lisätä stringien pituustestit
            if (!kayttaja.isEmpty() && !viesti.isEmpty() && !aihe.isEmpty()) {
                int alueId = Integer.parseInt(req.params(":id"));
                aiheDao.teeUusi(alueId, aihe);
                int aiheId = aiheDao.etsiUusin(alueId).getId();
                Kayttaja nykyinen = haeTaiTeeKayttaja(kayttaja, kayttajaDao);
                viestiDao.teeUusi(nykyinen.getId(), aiheId, viesti);
            } else {
                //pitäiskö antaa erroria tai jotain
            }
            String takas = "/" + req.params(":id");
            res.redirect(takas);
            return "";
        });

    }

    private static Kayttaja haeTaiTeeKayttaja(String kayttaja, KayttajaDao kayttajaDao) throws SQLException {
        Kayttaja nykyinen = kayttajaDao.findOne(kayttaja);
        if (nykyinen == null) {
            nykyinen = kayttajaDao.teeUusi(kayttaja);
        }
        return nykyinen;
    }

}
