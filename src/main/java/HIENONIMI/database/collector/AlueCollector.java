package HIENONIMI.database.collector;
import HIENONIMI.database.Collector;
import HIENONIMI.domain.Alue;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AlueCollector implements Collector<Alue> {
    
    @Override
    public Alue collect(ResultSet rs) throws SQLException {
        return new Alue(rs.getInt("id"), rs.getString("nimi"), rs.getInt("viesteja"));
    }
}
