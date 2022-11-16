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
    public kategoria getKategoria(String nev)
    {
        try {
            Statement st = cn.createStatement();
            ResultSet res = st.executeQuery("SELECT * FROM kategoria Where kategoria.nev = +'"+nev+"'");
            res.next();
            String kategNev = res.getString("nev");
            int kategAr = res.getInt("ar");
            kategoria re = new kategoria(kategNev,kategAr);
            return re;
            }
            catch (SQLException ex) {
            System.out.println(ex);
            return null;
            }
    }
    public ArrayList<String> getPizza()
    {
        try {
            ArrayList<String> list = new ArrayList<>();
            Statement st = cn.createStatement();
            ResultSet res = st.executeQuery("SELECT nev FROM pizza");
            while (res.next())
            {
                String pizzaNev = res.getString("nev");
                list.add(pizzaNev);
            }
            return list;
        }
        catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    public void InsertPizza(String pNev,String kNev, boolean vega) {
        int help;
        if(vega) help = 1;
        else help = 0;
        try {
            Statement st = cn.createStatement();
            st.execute("Insert into pizza Values ('"+pNev+"','"+kNev+"',"+ help +")");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void UpdateKateg(kategoria a) {
        try {
            Statement st = cn.createStatement();
            st.execute( "UPDATE kategoria" +
                            " SET nev = '"+a.getNev()+"', ar= "+a.getAr()+"" +
                            " WHERE nev = '"+a.getNev()+"'");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void DeletePizza(String a) {
        try {
            Statement st = cn.createStatement();
            st.execute( "DELETE FROM pizza WHERE nev ='" + a + "'");
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
