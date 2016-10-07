package HIENONIMI.domain;

import java.sql.Timestamp;

public class Viesti {

    private Integer id;
    private Aihe aihe;
    private Kayttaja kayttaja;
    private String viesti;
    private Timestamp aika;

    public Viesti(Integer id, Aihe aihe, Kayttaja kayttaja, String viesti, Timestamp aika) {
        this.id = id;
        this.aihe = aihe;
        this.kayttaja = kayttaja;
        this.viesti = viesti;
        this.aika = aika;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAihe(Aihe aihe) {
        this.aihe = aihe;
    }

    public void setKayttaja(Kayttaja kayttaja) {
        this.kayttaja = kayttaja;
    }

    public void setViesti(String viesti) {
        this.viesti = viesti;
    }

    public void setAika(Timestamp aika) {
        this.aika = aika;
    }

    public Integer getId() {
        return id;
    }

    public Aihe getAihe() {
        return aihe;
    }

    public Kayttaja getKayttaja() {
        return kayttaja;
    }

    public String getViesti() {
        return viesti;
    }

    public Timestamp getAika() {
        return aika;
    }

}
