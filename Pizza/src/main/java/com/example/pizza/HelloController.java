package com.example.pizza;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class HelloController {


    @FXML public VBox OlvasasMenu;
    @FXML public TableView<osszRendeles> tOlvas;
    public StackPane spOlvas;
    public ComboBox cbOlvasas;
    public TextField tfOlvas;
    public RadioButton rbPopey;
    public RadioButton rbRaki;
    public CheckBox cbVega;
    public ToggleGroup group;
    @FXML private TableColumn<osszRendeles, String> az;
    @FXML private TableColumn<osszRendeles, String> darab;
    @FXML private TableColumn<osszRendeles, String> ar;
    @FXML private TableColumn<osszRendeles, String> pizzanev;
    @FXML private TableColumn<osszRendeles, String> kategorianev;
    @FXML private TableColumn<osszRendeles, String> felvetel;
    @FXML private TableColumn<osszRendeles, String> kiszallitas;
    @FXML private TableColumn<osszRendeles, String> vega;
    String url = "jdbc:mysql://localhost/pizzatabla?user=root";
    Adatbazis dataBase = new Adatbazis(url);

    ArrayList<String> kategoriak = dataBase.Kategoriak();
    public void Clear()
    {
        OlvasasMenu.setVisible(false);
        spOlvas.setVisible(false);
    }
    public void Mutat(String a)
    {
        Clear();
        switch (a)
        {
            case "OlvasMenu":
                OlvasasMenu.setVisible(true);
                break;
            case "Olvas2Menu":
                OlvasasMenu.setVisible(true);
                spOlvas.setVisible(true);
                break;
        }
    }
    public  void Tabla(String a)
    {
        ArrayList<osszRendeles> rendelesek = dataBase.Rendelesek(a);
        tOlvas.getItems().removeAll(tOlvas.getItems());
        tOlvas.getColumns().removeAll(tOlvas.getColumns());

        az = new TableColumn("Id");
        darab = new TableColumn("db");
        ar = new TableColumn("Ár");
        pizzanev = new TableColumn("PizzaNév");
        kategorianev = new TableColumn("KategóriaNév");
        felvetel = new TableColumn("Felvetel");
        kiszallitas = new TableColumn("kiszallitas");
        vega = new TableColumn("vega");

        tOlvas.getColumns().addAll(az, darab,ar,pizzanev,kategorianev,felvetel,kiszallitas,vega);

        az.setCellValueFactory(new PropertyValueFactory<>("az"));
        darab.setCellValueFactory(new PropertyValueFactory<>("darab"));
        ar.setCellValueFactory(new PropertyValueFactory<>("ar"));
        pizzanev.setCellValueFactory(new PropertyValueFactory<>("pizzanev"));
        kategorianev.setCellValueFactory(new PropertyValueFactory<>("kategorianev"));
        felvetel.setCellValueFactory(new PropertyValueFactory<>("felvetel"));
        kiszallitas.setCellValueFactory(new PropertyValueFactory<>("kiszallitas"));
        vega.setCellValueFactory(new PropertyValueFactory<>("vega"));

        if(rendelesek == null)
        {
            tOlvas.getItems().add(new osszRendeles(0,0,0,"Nincs","Nincs","Nincs","Nincs",false));
        }
        else
        {
            for(int i = 0; i < rendelesek.size(); i++ )
            {
                tOlvas.getItems().add(rendelesek.get(i));
            }
        }
    }

    @FXML
    private void initialize()
    {
        Clear();

    }

    @FXML
    protected void Olvasas() {
        Mutat("OlvasMenu");
        Tabla("");
    }

    @FXML
    public void Olvasas2(ActionEvent actionEvent) {
        Mutat("Olvas2Menu");
        Tabla("");
        cbOlvasas.getItems().add(null);
        cbOlvasas.getItems().addAll(kategoriak);
    }

    public void Filter(ActionEvent actionEvent) {
        boolean helper = false;
        String le =" Where ";
        System.out.println(tfOlvas.getText());
        if(!tfOlvas.getText().isEmpty())
        {
            le +="rendeles.az = '" + tfOlvas.getText()+"'";
            helper = true;
        }
        if(cbOlvasas.getSelectionModel().getSelectedItem() != null)
        {
            if(helper){le+= " and";}
            le += " kategoria.nev = '" + cbOlvasas.getSelectionModel().getSelectedItem()+"'";
            helper = true;}
        if(group.getSelectedToggle() != null)
        {
            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText();
            if(helper){le+= " and";}
            le += " rendeles.pizzanev = '" + toogleGroupValue + "'"; helper = true;
        }
        if(cbVega.isSelected())
        {
            le+= " pizza.vegetarianus =  1" ; helper = true;

        }
        Tabla(le);
        Mutat("Olvas2Menu");
    }
}