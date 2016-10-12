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
            HashMap map = new HashMap<>();
            map.put("aiheet", aiheDao.findAll(Integer.parseInt(req.params("id"))));

            return new ModelAndView(map, "alue");
        }, new ThymeleafTemplateEngine());

        get("/:id/:aid", (req, res) -> {
            HashMap map = new HashMap<>();
            map.put("viestit", viestiDao.findAll(Integer.parseInt(req.params("aid"))));

            return new ModelAndView(map, "aihe");
        }, new ThymeleafTemplateEngine());

        post("/:id/:aid", (req, res) -> { // ei toimi vielä
            String kayttaja = req.queryParams("kayttaja");
            String viesti = req.queryParams("viesti");

            if (!kayttaja.isEmpty() && !viesti.isEmpty()) {
                viestiDao.teeUusi(kayttajaDao.teeUusi(kayttaja).getId(), Integer.parseInt(req.params("aid")), viesti);
            }

            res.redirect("/:id/:aid");
            return "";
        });
        
        /*
        Näitten jälkeen pitäisi tehä POSTit. 
        /:id         uusi aiheen teko, mahdollisesti uusi kayttaja
        /:id/:aid    uusi viestin teko, mahdollisesti uusi kayttaja
        
        Sekä templateihin textboxit ja nappulat submittaamiseen
         */
    }
}
