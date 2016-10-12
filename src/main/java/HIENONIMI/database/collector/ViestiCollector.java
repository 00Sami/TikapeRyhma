package HIENONIMI.database.collector;

import HIENONIMI.database.Collector;
import HIENONIMI.domain.Viesti;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViestiCollector implements Collector<Viesti> {

    @Override
    public Viesti collect(ResultSet rs) throws SQLException {
        return new Viesti(rs.getInt("id"), rs.getInt("aihe_id"), rs.getInt("kayttaja_id"), rs.getString("viesti"), rs.getString("aika"), rs.getString("kayttaja") );
    }

}
