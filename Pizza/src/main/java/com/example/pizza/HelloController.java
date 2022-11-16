package com.example.pizza;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class HelloController {


    @FXML public VBox OlvasasMenu;
    @FXML public TableView<osszRendeles> tOlvas;
    //Lapok:
    public VBox vbOlvas;
    public VBox vbInsert;
    public VBox vbUpdate;
    public VBox vbShowUpdate;
    public VBox vbDelete;
    public VBox vbRest1Get;
    public VBox vbRest1;
    public HBox mainCucc;

    public VBox vbRest1Post;

    public TableView tRest1Get;
    //Gombok mütyürkék:
    public ComboBox cbOlvasas;
    public TextField tfOlvas;
    public RadioButton rbPopey;
    public RadioButton rbRaki;
    public CheckBox cbVega;
    public ToggleGroup group;
    public TextField tfInsertPizzaNev;
    public ComboBox cbInsertKategoriaNev;
    public CheckBox tfInsertVega;
    public ComboBox cbShowKateg;
    public TextField tfUpdateKategNev;
    public TextField tfupdateKategAr;
    public ComboBox cbShowPizza;
    //POST
    public TextField tfRest1PostId;
    public ComboBox tfRest1PostStatus;
    public ComboBox tfRest1PostGender;
    public TextField tfRest1PostEmail;
    public TextField tfRest1PostName;
    public Label lResponseRest1Post;
    //Táblázat cuccok
    @FXML private TableColumn<osszRendeles, String> az;
    @FXML private TableColumn<osszRendeles, String> darab;
    @FXML private TableColumn<osszRendeles, String> ar;
    @FXML private TableColumn<osszRendeles, String> pizzanev;
    @FXML private TableColumn<osszRendeles, String> kategorianev;
    @FXML private TableColumn<osszRendeles, String> felvetel;
    @FXML private TableColumn<osszRendeles, String> kiszallitas;
    @FXML private TableColumn<osszRendeles, String> vega;
    @FXML private TableColumn<RestUser, String> RestUserid;
    @FXML private TableColumn<RestUser, String> RestUserName;
    @FXML private TableColumn<RestUser, String> RestUserEmail;
    @FXML private TableColumn<RestUser, String> RestUserGender;
    @FXML private TableColumn<RestUser, String> RestStatus;
    String url = "jdbc:mysql://localhost/pizzatabla?user=root";
    Adatbazis dataBase = new Adatbazis(url);
    ArrayList<String> kategoriak = dataBase.Kategoriak();
    public void Clear()
    {
        mainCucc.getChildren().removeAll(mainCucc.getChildren());
        cbShowKateg.setDisable(false);
    }
    public void Mutat(String a)
    {
        Clear();
        switch (a)
        {
            case "OlvasMenu":
                mainCucc.getChildren().add(OlvasasMenu);
                OlvasasMenu.getChildren().removeAll(OlvasasMenu.getChildren());
                OlvasasMenu.getChildren().add(tOlvas);
                break;
            case "Olvas2Menu":
                mainCucc.getChildren().add(OlvasasMenu);
                OlvasasMenu.getChildren().removeAll(OlvasasMenu.getChildren());
                OlvasasMenu.getChildren().addAll(vbOlvas,tOlvas);
                break;
            case "Insert":
                mainCucc.getChildren().add(OlvasasMenu);
                OlvasasMenu.getChildren().removeAll(OlvasasMenu.getChildren());
                cbInsertKategoriaNev.getItems().removeAll(cbInsertKategoriaNev.getItems());
                cbInsertKategoriaNev.getItems().addAll(dataBase.Kategoriak());
                OlvasasMenu.getChildren().addAll(vbInsert);
                break;
            case "Update":
                mainCucc.getChildren().add(OlvasasMenu);
                OlvasasMenu.getChildren().removeAll(OlvasasMenu.getChildren());
                cbShowKateg.getItems().removeAll(cbShowKateg.getItems());
                cbShowKateg.getItems().addAll(dataBase.Kategoriak());
                OlvasasMenu.getChildren().addAll(vbUpdate);
                break;
            case "ShowUpdate":
                mainCucc.getChildren().add(OlvasasMenu);
                OlvasasMenu.getChildren().removeAll(OlvasasMenu.getChildren());
                OlvasasMenu.getChildren().addAll(vbUpdate,vbShowUpdate);
                break;
            case "Delete":
                mainCucc.getChildren().add(OlvasasMenu);
                OlvasasMenu.getChildren().removeAll(OlvasasMenu.getChildren());
                cbShowPizza.getItems().removeAll(cbShowPizza.getItems());
                cbShowPizza.getItems().addAll(dataBase.getPizza());
                OlvasasMenu.getChildren().addAll(vbDelete);
                break;
            case "Rest1Get":
                mainCucc.getChildren().add(vbRest1);
                vbRest1.getChildren().removeAll(vbRest1.getChildren());
                vbRest1.getChildren().addAll(vbRest1Get);
                break;
            case "Rest1Post":
                mainCucc.getChildren().add(vbRest1);
                vbRest1.getChildren().removeAll(vbRest1.getChildren());
                tfRest1PostStatus.getItems().removeAll(tfRest1PostStatus.getItems());
                tfRest1PostGender.getItems().removeAll(tfRest1PostGender.getItems());
                tfRest1PostGender.getItems().addAll("male","female");
                tfRest1PostStatus.getItems().addAll("active","inactive");
                vbRest1.getChildren().addAll(vbRest1Post);
                break;
        }
    }
    public  void Tabla(String a)
    {
        ArrayList<osszRendeles> rendelesek = dataBase.Rendelesek(a);

        if(rendelesek == null || rendelesek.isEmpty())
        {
            tOlvas.getItems().removeAll(tOlvas.getItems());
            tOlvas.getColumns().removeAll(tOlvas.getColumns());
            pizzanev = new TableColumn("Nincs adat");
            tOlvas.getColumns().addAll(pizzanev);
            pizzanev.setCellValueFactory(new PropertyValueFactory<>("pizzanev"));

            tOlvas.getItems().add(new osszRendeles(0,0,0,"Nincs ilyen adat!","Nincs","Nincs","Nincs",false));
        }
        else
        {
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
    protected void Insert(){
        Mutat("Insert");
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
        String le ="";
        if(!tfOlvas.getText().isEmpty())
        {
            le +=" Where ";
            le +="rendeles.az = '" + tfOlvas.getText()+"'";
            helper = true;
        }
        if(cbOlvasas.getSelectionModel().getSelectedItem() != null)
        {
            if(helper){le+= " and";}
            else { le +=" Where "; }
            le += " kategoria.nev = '" + cbOlvasas.getSelectionModel().getSelectedItem()+"'";
            helper = true;}
        if(group.getSelectedToggle() != null)
        {
            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText();
            if(helper){le+= " and";}
            else { le +=" Where "; }
            le += " rendeles.pizzanev = '" + toogleGroupValue + "'"; helper = true;
        }
        if(cbVega.isSelected())
        {
            if(helper){le+= " and";}
            else { le +=" Where "; }
            le+= " pizza.vegetarianus =  1" ; helper = true;

        }
        Tabla(le);
        Mutat("Olvas2Menu");
    }

    public void InsertToTable(ActionEvent actionEvent) {

        String PizzaNev = tfInsertPizzaNev.getText();
        String KategoriaNev = cbInsertKategoriaNev.getSelectionModel().getSelectedItem().toString();
        boolean vega;
        if(tfInsertVega.isSelected()) vega = true;
        else vega = false;
        dataBase.InsertPizza(PizzaNev,KategoriaNev,vega);
        cbShowKateg.getItems().removeAll(cbShowKateg.getItems());
        Mutat("");
    }

    public void Update(ActionEvent actionEvent) {
        Mutat("Update");
    }

    public void ShowUpdate(ActionEvent actionEvent) {
            Mutat("ShowUpdate");
            if(cbShowKateg.getSelectionModel().getSelectedItem() == null) Mutat("");
            else
            {
                kategoria kiir = dataBase.getKategoria(cbShowKateg.getSelectionModel().getSelectedItem().toString());
                tfUpdateKategNev.setText(kiir.getNev());
                tfupdateKategAr.setText(String.valueOf(kiir.getAr()));
                cbShowKateg.setDisable(true);
            }
    }

    public void UpdateKateg(ActionEvent actionEvent) {
        kategoria update = new kategoria(tfUpdateKategNev.getText(),Integer.parseInt(tfupdateKategAr.getText()));
        dataBase.UpdateKateg(update);
        cbShowKateg.setDisable(false);
        cbShowKateg.getItems().removeAll(cbShowKateg.getItems());
        Mutat("Update");
    }

    public void Delete(ActionEvent actionEvent) {
        Mutat("Delete");
    }

    public void DeletePizza(ActionEvent actionEvent) {
        String dPizza = cbShowPizza.getSelectionModel().getSelectedItem().toString();
        dataBase.DeletePizza(dPizza);
        cbShowPizza.getItems().removeAll(cbShowPizza.getItems());
        Mutat("Delete");
    }

    public void Rest1Get(ActionEvent actionEvent) throws IOException {
        Mutat("Rest1Get");
        Rest1Tabla();

    }
    public void Rest1Tabla() throws IOException {
            RestUser[] user = RestKliens.GET();

            tRest1Get.getItems().removeAll(tRest1Get.getItems());
            tRest1Get.getColumns().removeAll(tRest1Get.getColumns());

            RestUserid = new TableColumn("RestUserid");
            RestUserName = new TableColumn("RestUserName");
            RestUserEmail = new TableColumn("RestUserEmail");
            RestUserGender = new TableColumn("RestUserGender");
            RestStatus = new TableColumn("RestStatus");

            tRest1Get.getColumns().addAll(RestUserid, RestUserName,RestUserEmail,RestUserGender,RestStatus);

            RestUserid.setCellValueFactory(new PropertyValueFactory<>("id"));
            RestUserName.setCellValueFactory(new PropertyValueFactory<>("name"));
            RestUserEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            RestUserGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            RestStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

            tRest1Get.getItems().addAll(user);
    }
    public void Rest1Post(ActionEvent actionEvent) {
        Mutat("Rest1Post");
    }

    public void Rest1PostDo(ActionEvent actionEvent) throws IOException {
        RestUser user = new RestUser(Integer.parseInt(tfRest1PostId.getText()),tfRest1PostName.getText(),tfRest1PostEmail.getText(),tfRest1PostGender.getSelectionModel().getSelectedItem().toString(),tfRest1PostStatus.getSelectionModel().getSelectedItem().toString());
        lResponseRest1Post.setText("Válasz:" + RestKliens.POST(user));
    }
}