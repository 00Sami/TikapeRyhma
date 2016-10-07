package HIENONIMI.domain;

public class Kayttaja {

    private Integer id;
    private String nimi;

    public Kayttaja(Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Integer getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

}
