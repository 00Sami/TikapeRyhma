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

        // käytetään oletuksena paikallista sqlite-tietokantaa
        String jdbcOsoite = "jdbc:sqlite:HIENONIMI.db";
        // jos heroku antaa käyttöömme tietokantaosoitteen, otetaan se käyttöön
        if (System.getenv("DATABASE_URL") != null) {
            jdbcOsoite = System.getenv("DATABASE_URL");
        }

        Database database = new Database(jdbcOsoite);

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
            int id = -1;
            try {
                id = Integer.parseInt(req.params(":id"));
            } catch (NumberFormatException e) {
                //miksi tässä koitetaan parsia faviconia??
            }
            map.put("aiheet", aiheDao.findAll(id));
            map.put("alue", alueDao.findOne(id));
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
            
            try {                
                map.put("alue", alueDao.findOne(Integer.parseInt(req.params(":id"))));
            } catch (NumberFormatException e) {}
            
            //jos sivujen määrä = 1, niin ei lisätä sivuja joten yksisivuisessa threadissä ei näy sivunumeroa
            if (sivut.size() > 1) {
                map.put("sivut", sivut);
            }
            return new ModelAndView(map, "aihe");
        }, new ThymeleafTemplateEngine());

        post("/:id/:aid", (req, res) -> {
            String kayttaja = req.queryParams("kayttaja");
            String viesti = req.queryParams("viesti");
            if (kayttaja.isEmpty() || viesti.isEmpty()) {
                return "Virhe! Tyhjä viesti tai kayttaja.";
            }

            if (viesti.length() > 1999 || kayttaja.length() > 24) {
                return "Virhe! Liian pitkä viesti tai käyttäjä";
            }
            int aiheId = -1;
            try {
                aiheId = Integer.parseInt(req.params(":aid"));
            } catch (NumberFormatException e) {}
            
            Kayttaja nykyinen = haeTaiTeeKayttaja(kayttaja, kayttajaDao);
            viestiDao.teeUusi(nykyinen.getId(), aiheId, viesti);
            String takas = "/" + req.params(":id") + "/" + req.params(":aid") + "?sivu=" + viestiDao.sivunumerot(Integer.parseInt(req.params(":aid"))).size();
            res.redirect(takas);
            return "";
        });

        post("/:id", (req, res) -> {
            String kayttaja = req.queryParams("kayttaja");
            String aihe = req.queryParams("aihe");
            String viesti = req.queryParams("viesti");
            if (kayttaja.isEmpty() || viesti.isEmpty() || aihe.isEmpty()) {
                return "Virhe! Tyhjä viesti, kayttaja tai aihe.";
            }
            if (viesti.length() > 1999 || kayttaja.length() > 24 || aihe.length() > 49) {
                return "Virhe! Liian pitkä viesti, käyttäjä tai aihe";
            }

            int alueId = -1;
            try {
                alueId = Integer.parseInt(req.params(":id"));
            } catch (NumberFormatException e) {}
            aiheDao.teeUusi(alueId, aihe);
            int aiheId = aiheDao.etsiUusin(alueId).getId();
            Kayttaja nykyinen = haeTaiTeeKayttaja(kayttaja, kayttajaDao);
            viestiDao.teeUusi(nykyinen.getId(), aiheId, viesti);

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
