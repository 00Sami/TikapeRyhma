package HIENONIMI;

import HIENONIMI.database.AiheDao;
import java.util.HashMap;
import spark.ModelAndView;
import static spark.Spark.*;
import spark.template.thymeleaf.ThymeleafTemplateEngine;
import HIENONIMI.database.Database;
import HIENONIMI.database.AlueDao;
import HIENONIMI.database.ViestiDao;

public class Main {

    public static void main(String[] args) throws Exception {
        Database database = new Database("jdbc:sqlite:HIENONIMI.db");
        database.init();

        AlueDao alueDao = new AlueDao(database);
        AiheDao aiheDao = new AiheDao(database);
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
        
         
        
        /*
        Näitten jälkeen pitäisi tehä POSTit. 
        /:id         uusi aiheen teko, mahdollisesti uusi kayttaja
        /:id/:aid    uusi viestin teko, mahdollisesti uusi kayttaja
        
        Sekä templateihin textboxit ja nappulat submittaamiseen
         */
    }
}
