package com.example.pizza;

import java.sql.*;
import java.util.ArrayList;

public class Adatbazis {
    private Connection cn;

    public Adatbazis(String url) {
        try {
            cn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public ArrayList<osszRendeles> Rendelesek(String a) {
        ArrayList<osszRendeles> rend = new ArrayList<>();
        try {
            Statement st = cn.createStatement();
            ResultSet res = st.executeQuery("SELECT rendeles.az,rendeles.darab,rendeles.pizzanev,kategoria.nev,pizza.vegetarianus,(kategoria.ar*rendeles.darab) as ar,rendeles.felvetel,rendeles.kiszallitas FROM (rendeles inner join pizza on pizza.nev = rendeles.pizzanev) inner join kategoria on kategoria.nev = pizza.kategorianev "+ a);
            while (res.next()) {
                int az = res.getInt("az");
                int db = res.getInt("darab");
                String pnev = res.getString("pizzanev");
                String knev = res.getString("nev");
                boolean vega = res.getBoolean("vegetarianus");
                int ar = res.getInt("ar");
                String fel = res.getString("felvetel");
                String ki = res.getString("kiszallitas");
                osszRendeles rendeles = new osszRendeles(az, db, ar, pnev, knev, fel, ki, vega);
                rend.add(rendeles);
            }
            return rend;
        } catch (SQLException e) {
            return null;
        }
    }
    public ArrayList<String> Kategoriak() {
        ArrayList<String> rend = new ArrayList<>();
        try {
            Statement st = cn.createStatement();
            ResultSet res = st.executeQuery("SELECT kategoria.nev as nev FROM kategoria");
            while (res.next()) {

                String kateg = res.getString("nev");
                rend.add(kateg);
            }
            return rend;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }

}
