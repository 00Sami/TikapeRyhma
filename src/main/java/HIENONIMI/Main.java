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

public class Main {

    public static void main(String[] args) throws Exception {
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
            //favicon valitus lähtee jos pistää /:id/, mutta tämä rikkoo kaikki linkit ja en ehdit tätä nyt korjaamaan.
            HashMap map = new HashMap<>();
            map.put("aiheet", aiheDao.findAll(Integer.parseInt(req.params(":id"))));

            return new ModelAndView(map, "alue");
        }, new ThymeleafTemplateEngine());

        get("/:id/:aid", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viestit", viestiDao.findAll(Integer.parseInt(req.params("aid"))));

            return new ModelAndView(map, "aihe");
        }, new ThymeleafTemplateEngine());

        post("/:id/:aid", (req, res) -> {
            String kayttaja = req.queryParams("kayttaja");
            String viesti = req.queryParams("viesti");
            if (!kayttaja.isEmpty() && !viesti.isEmpty()) {
                int aiheId = Integer.parseInt(req.params("aid"));
                Kayttaja nykyinen = kayttajaDao.findOne(kayttaja);
                if (nykyinen == null) {
                    nykyinen = kayttajaDao.teeUusi(kayttaja);
                }
                viestiDao.teeUusi(nykyinen.getId(), aiheId, viesti);
            }
            String takas = "/" + req.params("id") + "/" + req.params("aid");
            res.redirect(takas);
            return "";
        });

        post("/:id", (req, res) -> {
            String kayttaja = req.queryParams("kayttaja");
            String aihe = req.queryParams("aihe");
            String viesti = req.queryParams("viesti");
            if (!kayttaja.isEmpty() && !viesti.isEmpty()) {
                int alueId = Integer.parseInt(req.params("id"));
                aiheDao.teeUusi(alueId, aihe);
                int aiheId = aiheDao.etsiUusin(alueId).getId();
                Kayttaja nykyinen = kayttajaDao.findOne(kayttaja);
                if (nykyinen == null) {
                    nykyinen = kayttajaDao.teeUusi(kayttaja);
                }
                viestiDao.teeUusi(nykyinen.getId(), aiheId, viesti);
            }
            String takas = "/" + req.params("id");
            res.redirect(takas);
            return "";
        });

    }
}
