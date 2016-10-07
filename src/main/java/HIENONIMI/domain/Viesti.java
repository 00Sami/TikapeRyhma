package HIENONIMI.domain;

import java.sql.Timestamp;

public class Viesti {

    private Integer id;
    private Integer aihe_id;
    private Integer kayttaja_id;
    private String viesti;
    private Timestamp aika;

    public Viesti(Integer id, Integer aihe_id, Integer kayttaja_id, String viesti, Timestamp aika) {
        this.id = id;
        this.aihe_id = aihe_id;
        this.kayttaja_id = kayttaja_id;
        this.viesti = viesti;
        this.aika = aika;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAihe(Integer aihe_id) {
        this.aihe_id = aihe_id;
    }

    public void setKayttaja(Integer kayttaja_id) {
        this.kayttaja_id = kayttaja_id;
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

    public Integer getAihe() {
        return aihe_id;
    }

    public Integer getKayttaja() {
        return kayttaja_id;
    }

    public String getViesti() {
        return viesti;
    }

    public Timestamp getAika() {
        return aika;
    }

}
