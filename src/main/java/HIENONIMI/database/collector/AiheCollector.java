package HIENONIMI.database.collector;

import HIENONIMI.database.Collector;
import HIENONIMI.domain.Aihe;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AiheCollector implements Collector<Aihe> {
    
    @Override
    public Aihe collect(ResultSet rs) throws SQLException {
        return new Aihe(rs.getInt("id"), rs.getInt("alue_id"), rs.getString("nimi"), rs.getInt("viesteja"), rs.getString("aika"));
    }
}
