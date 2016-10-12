package HIENONIMI.database;

import HIENONIMI.database.collector.AlueCollector;
import java.sql.SQLException;
import java.util.List;
import HIENONIMI.domain.Alue;

public class AlueDao implements Dao<Alue, Integer> {

    private Database database;

    public AlueDao(Database database) {
        this.database = database;
    }

    @Override
    public Alue findOne(Integer key) throws SQLException {
        String komento = "SELECT * FROM Alue WHERE id = ?";
        List<Alue> alueet = database.queryAndCollect(komento, new AlueCollector(), key);

        if (alueet.isEmpty()) {
            return null;
        } else {
            return alueet.get(0);
        }
    }

    @Override
    public List<Alue> findAll() throws SQLException {
        //tämä viesti counter on väärin. ei laske kaikkia
        String komento = "SELECT Alue.id, Alue.nimi, count(Viesti.id) AS viesteja FROM Alue LEFT JOIN Aihe ON Alue.id = Aihe.Alue_id LEFT JOIN Viesti ON Aihe.id = Viesti.Aihe_id GROUP BY Alue.id";
        List<Alue> alueet = database.queryAndCollect(komento, new AlueCollector());

        if (alueet.isEmpty()) {
            return null;
        } else {
            return alueet;
        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
        // ei toteutettu
    }

}
