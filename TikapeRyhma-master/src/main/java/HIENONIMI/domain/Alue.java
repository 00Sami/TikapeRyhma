package HIENONIMI.domain;

public class Alue {

    private Integer id;
    private String nimi;
    private int viesteja;
    private String viimeisin;
    
    
    public Alue(Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
        this.viesteja = 0;
        this.viimeisin = "";
    }    
    
    public Alue(Integer id, String nimi, int viesteja, String aika) {
        this.id = id;
        this.nimi = nimi;
        this.viesteja = viesteja;
        this.viimeisin = aika;
    }

    public String getViimeisin() {
        return viimeisin;
    }

    public void setViimeisin(String viimeisin) {
        this.viimeisin = viimeisin;
    }

    public int getViesteja() {
        return viesteja;
    }

    public void setViesteja(int viesteja) {
        this.viesteja = viesteja;
    }

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
