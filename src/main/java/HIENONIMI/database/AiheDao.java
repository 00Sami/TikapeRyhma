package HIENONIMI.database;

import HIENONIMI.database.collector.AiheCollector;
import HIENONIMI.domain.Aihe;
import java.sql.SQLException;
import java.util.List;

public class AiheDao {
    private Database database;
    /*
    En implementannu Dao tähän, koska tätä käytetään vain yhdessä haussa ja Dao:n
    kaikki metodit ovat turhia. Ehkä..
    */

    public AiheDao(Database database) {
        this.database = database;
    }
    
    public List<Aihe> findAll(int alueId) throws SQLException {
        // tähänkin timestamp
        String komento = "SELECT Alue.id, Aihe.Alue_id, Aihe.nimi, count(Viesti.id) AS viesteja FROM Alue JOIN Aihe ON Alue.id = Aihe.Alue_id JOIN Viesti ON Aihe.id = Viesti.Aihe_id WHERE Aihe.alue_id = ? GROUP BY Aihe.id";
        List<Aihe> aiheet = database.queryAndCollect(komento, new AiheCollector(), alueId);

        if (aiheet.isEmpty()) {
            return null;
        } else {
            return aiheet;
        }
    }

}
