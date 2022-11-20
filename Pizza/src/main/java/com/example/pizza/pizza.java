package com.example.pizza;

public class pizza {
    public String nev, kategorianev;
    public String vegetarianus;

    public pizza(String nev, String knev, String vega) {
        this.nev = nev;
        this.kategorianev = knev;
        this.vegetarianus = vega;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getKnev() {
        return kategorianev;
    }

    public void setKnev(String knev) {
        this.kategorianev = knev;
    }

    public String getVega() {
        return vegetarianus;
    }

    public void setVega(String vega) {
        this.vegetarianus = vega;
    }

    @Override
    public String toString() {
        return
                "{" +
                        "\"id\":" + nev + ","+
                        "\"name\":\"" + kategorianev + "\","+
                        "\"email\":\"" + vegetarianus + "\","+
                        '}';
    }
}
