package HIENONIMI.domain;

import java.sql.Timestamp;

public class Alue {

    private Integer id;
    private String nimi;
    private int viesteja;
    //private Timestamp viimeisin;

    public Alue(Integer id, String nimi, int viesteja) {
        this.id = id;
        this.nimi = nimi;
        this.viesteja = viesteja;
        //this.viimeisin = viimeisin;
    }

    public int getViesteja() {
        return viesteja;
    }

    public void setViesteja(int viesteja) {
        this.viesteja = viesteja;
    }

    /*    public Timestamp getViimeisin() {
    return viimeisin;
    }
    
    public void setViimeisin(Timestamp viimeisin) {
    this.viimeisin = viimeisin;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

}
