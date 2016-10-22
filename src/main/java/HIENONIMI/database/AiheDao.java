package HIENONIMI.database;

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
        String komento = "SELECT Aihe.id, Aihe.Alue_id, Aihe.nimi, count(Viesti.id) AS viesteja, MAX(Viesti.aika) as aika FROM Alue JOIN Aihe ON Alue.id = Aihe.Alue_id JOIN Viesti ON Aihe.id = Viesti.Aihe_id WHERE Aihe.alue_id = ? GROUP BY Aihe.id ORDER BY aika DESC LIMIT 10";
        List<Aihe> aiheet = database.queryAndCollect(komento, rs -> new Aihe(rs.getInt("id"), rs.getInt("Alue_id"), rs.getString("nimi"), rs.getInt("viesteja"), rs.getString("aika")), alueId);

        if (aiheet.isEmpty()) {
            return null;
        } else {
            return aiheet;
        }
    }

    public Aihe findOne(int id) throws SQLException {
        String komento = "SELECT Aihe.id, Aihe.Alue_id, Aihe.nimi FROM Aihe WHERE Aihe.id = ?";
        return (Aihe) database.queryAndCollect(komento, rs -> new Aihe(rs.getInt("id"), rs.getInt("Alue_id"), rs.getString("nimi")), id).get(0);
    }

    public Integer uusimmanViestinId(int alueId) throws SQLException {
        String komento = "SELECT MAX(Aihe.id) AS id FROM Aihe WHERE Aihe.Alue_id = ?";
        List<Integer> id = database.queryAndCollect(komento, rs -> rs.getInt("id"), alueId);

        if (id.isEmpty()) {
            return null;
        } else {
            return id.get(0);
        }
    }

    public void teeUusi(Integer alue_id, String nimi) throws SQLException {
        String komento = "INSERT INTO Aihe (Alue_id, nimi) VALUES (?, ?)";
        System.out.println("tehtiin: " + nimi);
        database.update(komento, alue_id, nimi);
    }

}
