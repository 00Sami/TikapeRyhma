package HIENONIMI.database;

import HIENONIMI.domain.Kayttaja;
import java.sql.SQLException;
import java.util.List;

public class KayttajaDao implements Dao<Kayttaja, Integer> {

    private Database database;

    public KayttajaDao(Database database) {
        this.database = database;
    }

    @Override
    public Kayttaja findOne(Integer key) throws SQLException {
        String komento = "SELECT * FROM Kayttaja WHERE id = ?";
        List<Kayttaja> kayttajat = database.queryAndCollect(komento, rs -> new Kayttaja(rs.getInt("id"), rs.getString("nimi")), key);

        if (kayttajat.isEmpty()) {
            return null;
        } else {
            return kayttajat.get(0);
        }
    }

    public Kayttaja findOne(String nimi) throws SQLException {
        String komento = "SELECT * FROM Kayttaja WHERE nimi = ?";
        List<Kayttaja> kayttajat = database.queryAndCollect(komento, rs -> new Kayttaja(rs.getInt("id"), rs.getString("nimi")), nimi);

        if (kayttajat.isEmpty()) {
            return null;
        } else {
            return kayttajat.get(0);
        }
    }

    public Kayttaja teeUusi(String nimi) throws SQLException {
        String komento = "INSERT INTO Kayttaja (nimi) VALUES (?)";
        database.update(komento, nimi);
        return findOne(nimi);
    }

    @Override
    public List<Kayttaja> findAll() throws SQLException {
        String komento = "SELECT * FROM Kayttaja";
        List<Kayttaja> kayttajat = database.queryAndCollect(komento, rs -> new Kayttaja(rs.getInt("id"), rs.getString("nimi")));

        if (kayttajat.isEmpty()) {
            return null;
        } else {
            return kayttajat;
        }
    }

    @Override
    public void delete(Integer key) throws SQLException {
    }

}
