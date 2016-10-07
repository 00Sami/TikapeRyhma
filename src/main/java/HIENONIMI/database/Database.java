package HIENONIMI.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private String databaseAddress;

    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
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

        return lista;
    }
}
