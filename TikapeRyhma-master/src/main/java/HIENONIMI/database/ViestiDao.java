package HIENONIMI.database;

import HIENONIMI.domain.Viesti;
import java.sql.SQLException;
import java.util.List;

public class ViestiDao {

    private Database database;

    public ViestiDao(Database database) {
        this.database = database;
    }

    public List<Viesti> findAll(int aiheId) throws SQLException {
        String komento = "SELECT Viesti.id as id, Viesti.aihe_id as aihe_id, Viesti.kayttaja_id as kayttaja_id, viesti.viesti, viesti.aika, Kayttaja.nimi as kayttaja FROM Viesti INNER JOIN Kayttaja ON Viesti.kayttaja_id = Kayttaja.id WHERE aihe_id = ?";
        List<Viesti> viestit = database.queryAndCollect(komento, rs -> new Viesti(rs.getInt("id"), rs.getInt("aihe_id"), rs.getInt("kayttaja_id"), rs.getString("viesti"), rs.getString("aika"), rs.getString("kayttaja")), aiheId);

        if (viestit.isEmpty()) {
            return null;
        } else {
            return viestit;
        }
    }

    public void teeUusi(Integer kayttaja_id, Integer aihe_id, String viesti) throws SQLException {
        String komento = "INSERT INTO Viesti (Kayttaja_id, Aihe_id, viesti) VALUES (?, ?, ?)";
        System.out.println("tehtiin: " + viesti);
        database.update(komento, kayttaja_id, aihe_id, viesti);
    }
}
