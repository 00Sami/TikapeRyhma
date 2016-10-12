package HIENONIMI.database.collector;

import HIENONIMI.database.Collector;
import HIENONIMI.domain.Kayttaja;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KayttajaCollector implements Collector<Kayttaja> {

    @Override
    public Kayttaja collect(ResultSet rs) throws SQLException {
        return new Kayttaja(rs.getInt("id"), rs.getString("nimi"));
    }
    
}
