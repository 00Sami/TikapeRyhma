package HIENONIMI.domain;

public class Aihe {

    private Integer id;
    private Integer alue_id;
    private String nimi;

    public Aihe(Integer id, Integer alue_id, String nimi) {
        this.id = id;
        this.alue_id = alue_id;
        this.nimi = nimi;
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

}
