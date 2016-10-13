package HIENONIMI.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;
    private Connection connection;

    public Database(String databaseAddress) throws Exception {
        this.databaseAddress = databaseAddress;
        this.connection = DriverManager.getConnection(databaseAddress);
    }
    
    
    public <T> List<T> queryAndCollect(String query, Collector<T> col, Object... params) throws SQLException {
        
        List<T> rows = new ArrayList<>();
        PreparedStatement stmt = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            rows.add(col.collect(rs));
        }

        rs.close();
        stmt.close();
        return rows;
    }
    
    
    public int update(String updateQuery, Object... params) throws SQLException {
        
        PreparedStatement stmt = connection.prepareStatement(updateQuery);

        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }

        int changes = stmt.executeUpdate();
       
        stmt.close();

        return changes;
    }
    
    

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }

    public void init() {
        List<String> lauseet = sqliteLauseet();

        // "try with resources" sulkee resurssin automaattisesti lopuksi
        try (Connection conn = getConnection()) {
            Statement st = conn.createStatement();

            // suoritetaan komennot
            for (String lause : lauseet) {
                System.out.println("Running command >> " + lause);
                st.executeUpdate(lause);
            }

        } catch (Throwable t) {
            // jos tietokantataulu on jo olemassa, ei komentoja suoriteta
            System.out.println("Error >> " + t.getMessage());
        }
    }

    private List<String> sqliteLauseet() {
        ArrayList<String> lista = new ArrayList<>();

        // tietokantataulujen luomiseen tarvittavat komennot suoritusjärjestyksessä
        lista.add("CREATE TABLE Alue (id INTEGER PRIMARY KEY,nimi varchar(30) NOT NULL);");
        lista.add("CREATE TABLE Aihe (id INTEGER PRIMARY KEY, alue_id integer NOT NULL, nimi varchar(50) NOT NULL, FOREIGN KEY (alue_id) REFERENCES Alue(id));");
        lista.add("CREATE TABLE Viesti (id INTEGER PRIMARY KEY,	aihe_id integer NOT NULL, kayttaja_id integer NOT NULL, viesti varchar(2000) NOT NULL, aika DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY (aihe_id) REFERENCES Aihe(id), FOREIGN KEY (kayttaja_id) REFERENCES Kayttaja(id));");
        lista.add("CREATE TABLE Kayttaja (id INTEGER PRIMARY KEY, nimi varchar(25) NOT NULL);");
        //pitäiskö käyttäjänimi olla unique?
        //tähän varmaan pitäisi lisätä alueita myös niin jos tehdään uusi DB, on paikkoja mihin postata..
        return lista;
    }
}
