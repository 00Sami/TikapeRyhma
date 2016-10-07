package HIENONIMI.domain;

import java.util.*;

public class Alue {

    private Integer id;
    private String nimi;
    private List<Aihe> aiheet;

    public Alue(Integer id, String nimi, List<Aihe> aiheet) {
        this.id = id;
        this.nimi = nimi;
        this.aiheet = aiheet;
    }

    public List<Aihe> getAiheet() {
        return aiheet;
    }

    public Integer getId() {
        return id;
    }

    public void setAiheet(List<Aihe> aiheet) {
        this.aiheet = aiheet;
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
