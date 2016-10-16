package HIENONIMI.database;

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
        List<Alue> alueet = database.queryAndCollect(komento, rs -> new Alue(rs.getInt("id"), rs.getString("nimi")), key);

        if (alueet.isEmpty()) {
            return null;
        } else {
            return alueet.get(0);
        }
    }

    @Override
    public List<Alue> findAll() throws SQLException {
        String komento = "SELECT Alue.id, Alue.nimi, count(Viesti.id) AS viesteja, MAX(viesti.aika) as aika FROM Alue LEFT JOIN Aihe ON Alue.id = Aihe.Alue_id LEFT JOIN Viesti ON Aihe.id = Viesti.Aihe_id GROUP BY Alue.id ORDER BY Alue.nimi ASC";
        List<Alue> alueet = database.queryAndCollect(komento, rs -> new Alue(rs.getInt("id"), rs.getString("nimi"), rs.getInt("viesteja"), rs.getString("aika")));

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
