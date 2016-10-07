package HIENONIMI.domain;

import java.util.*;

public class Kayttaja {

    private Integer id;
    private String nimi;
    private List<Viesti> viestit;

    public Kayttaja(Integer id, String nimi, List<Viesti> viestit) {
        this.id = id;
        this.nimi = nimi;
        this.viestit = viestit;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setViestit(List<Viesti> viestit) {
        this.viestit = viestit;
    }

    public Integer getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public List<Viesti> getViestit() {
        return viestit;
    }

}
