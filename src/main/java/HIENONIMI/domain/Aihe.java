package HIENONIMI.domain;

public class Aihe {

    private Integer id;
    private Integer alue_id;
    private String nimi;
    private int viesteja;
    private String viimeisin;

    public Aihe(Integer id, Integer alue_id, String nimi, int viesteja, String aika) {
        this.id = id;
        this.alue_id = alue_id;
        this.nimi = nimi;
        this.viesteja = viesteja;
        this.viimeisin = aika;
    }

    public Integer getAlue_id() {
        return alue_id;
    }

    public void setAlue_id(Integer alue_id) {
        this.alue_id = alue_id;
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

    public Integer getAlue() {
        return alue_id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAlue(Integer alue_id) {
        this.alue_id = alue_id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getViimeisin() {
        return viimeisin;
    }

    public void setViimeisin(String viimeisin) {
        this.viimeisin = viimeisin;
    }

}
