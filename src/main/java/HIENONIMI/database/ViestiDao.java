package HIENONIMI.database;

import HIENONIMI.domain.Viesti;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViestiDao {

    private Database database;

    public ViestiDao(Database database) {
        this.database = database;
    }

    public List<Viesti> naytaKymmenen(int aiheId) throws SQLException {
        return naytaKymmenen(aiheId, 1);
    }

    public List<Viesti> naytaKymmenen(int aiheId, int sivu) throws SQLException {

        String komento = "SELECT Viesti.id as id, Viesti.aihe_id as aihe_id, Viesti.kayttaja_id as kayttaja_id, viesti.viesti, viesti.aika, Kayttaja.nimi as kayttaja FROM Viesti INNER JOIN Kayttaja ON Viesti.kayttaja_id = Kayttaja.id WHERE aihe_id = ? ORDER BY Viesti.id LIMIT 10 OFFSET ?*10-10";
        List<Viesti> viestit = database.queryAndCollect(komento, rs -> new Viesti(rs.getInt("id"), rs.getInt("aihe_id"), rs.getInt("kayttaja_id"), rs.getString("viesti"), rs.getString("aika"), rs.getString("kayttaja")), aiheId, sivu);

        if (viestit.isEmpty()) {
            return null;
        } else {
            return viestit;
        }
    }

    public List<Integer> sivunumerot(int aiheId) throws SQLException {
        String komento = "SELECT COUNT(Viesti.id) AS maara FROM Viesti WHERE aihe_id = ?";
        int viestimaara = database.queryAndCollect(komento, rs -> rs.getInt("maara"), aiheId).get(0);
        List<Integer> sivut = new ArrayList<>();
        int sivumaara = (int) Math.ceil(viestimaara * 1.0 / 10);
        for (int i = 1; i <= sivumaara; i++) {
            sivut.add(i);
        }
        return sivut;
    }

    public void teeUusi(Integer kayttaja_id, Integer aihe_id, String viesti) throws SQLException {
        String komento = "INSERT INTO Viesti (Kayttaja_id, Aihe_id, viesti) VALUES (?, ?, ?)";
        System.out.println("tehtiin: " + viesti);
        database.update(komento, kayttaja_id, aihe_id, viesti);
    }
}
