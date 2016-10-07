package HIENONIMI.domain;

import java.util.List;

public class Aihe {

    private Integer id;
    private Alue alue;
    private String nimi;
    private List<Viesti> viestit;

    public Aihe(Integer id, Alue alue, String nimi, List<Viesti> viestit) {
        this.id = id;
        this.alue = alue;
        this.nimi = nimi;
        this.viestit = viestit;
    }

    public List<Viesti> getViestit() {
        return viestit;
    }

    public Integer getId() {
        return id;
    }

    public Alue getAlue() {
        return alue;
    }

    public String getNimi() {
        return nimi;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setViestit(List<Viesti> viestit) {
        this.viestit = viestit;
    }

    public void setAlue(Alue alue) {
        this.alue = alue;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

}
