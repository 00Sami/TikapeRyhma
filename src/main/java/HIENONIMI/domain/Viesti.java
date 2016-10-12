package HIENONIMI.domain;


public class Viesti {

    private Integer id;
    private Integer aihe_id;
    private Integer kayttaja_id;
    private String viesti;
    private String aika;
    private String kayttajanimi;

    public Viesti(Integer id, Integer aihe_id, Integer kayttaja_id, String viesti, String aika, String kayttajanimi) {
        this.id = id;
        this.aihe_id = aihe_id;
        this.kayttaja_id = kayttaja_id;
        this.viesti = viesti;
        this.aika = aika;
        this.kayttajanimi = kayttajanimi;
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

    public void setAika(String aika) {
        this.aika = aika;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAihe_id() {
        return aihe_id;
    }

    public void setAihe_id(Integer aihe_id) {
        this.aihe_id = aihe_id;
    }

    public Integer getKayttaja_id() {
        return kayttaja_id;
    }

    public void setKayttaja_id(Integer kayttaja_id) {
        this.kayttaja_id = kayttaja_id;
    }

    public String getKayttajanimi() {
        return kayttajanimi;
    }

    public void setKayttajanimi(String kayttajanimi) {
        this.kayttajanimi = kayttajanimi;
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

    public String getAika() {
        return aika;
    }

}
