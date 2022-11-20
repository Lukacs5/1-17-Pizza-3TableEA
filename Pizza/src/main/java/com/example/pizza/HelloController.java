package com.example.pizza;

import com.google.gson.Gson;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Boolean.parseBoolean;
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
    public VBox vbEgyeb;
    public VBox vbParhuzamos;
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
    public TextField tfRest1Delete;
    public VBox vbRest1Delete;
    public Label lDeleted;
    public VBox vbRest1Update;
    public Label lUpdated;
    public TextField tfRest1UpdateId;
    public TextField tfRest1UpdateName;
    public TextField tfRest1UpdateEmail;
    public ComboBox cbRest1UpdateGender;
    public ComboBox cbRest1UpdateStatus;
    public TextField tfRest2PostId;
    public ComboBox tfRest2PostStatus;
    public ComboBox tfRest2PostGender;
    public TextField tfRest2PostEmail;
    public TextField tfRest2PostName;
    public Label lResponseRest2Post;
    public TextField tfRest2Delete;
    public VBox vbRest2Delete;
    public Label lDeleted2;
    public VBox vbRest2Update;
    public Label lUpdated2;
    public TextField tfRest2UpdateId;
    public TextField tfRest2UpdateName;
    public TextField tfRest2UpdateEmail;
    public ComboBox cbRest2UpdateGender;
    public ComboBox cbRest2UpdateStatus;
    public Button bParhuzamos;
    public Label lParhuzamos1;
    public Label lParhuzamos2;
    public TextField tfStreamId;
    public ComboBox cbStreamkateg;
    public VBox vbStream;
    public CheckBox cbStreamVega;
    public TableView tStream;
    public ToggleGroup group1;
    public VBox vbRest2Post;
    public TableView tRest2Get;
    public VBox vbRest2Get;
    public VBox vbRest2;
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
            case "Rest2Get":
                mainCucc.getChildren().add(vbRest2);
                vbRest2.getChildren().removeAll(vbRest2.getChildren());
                vbRest2.getChildren().addAll(vbRest2Get);
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
            case "Rest2Post":
                mainCucc.getChildren().add(vbRest2);
                vbRest2.getChildren().removeAll(vbRest2.getChildren());
                vbRest2.getChildren().addAll(vbRest2Post);
                break;
            case "Rest1Delete":
                mainCucc.getChildren().add(vbRest1);
                vbRest1.getChildren().removeAll(vbRest1.getChildren());
                vbRest1.getChildren().addAll(vbRest1Delete);
                break;
            case "Rest2Delete":
                mainCucc.getChildren().add(vbRest2);
                vbRest2.getChildren().removeAll(vbRest2.getChildren());
                vbRest2.getChildren().addAll(vbRest2Delete);
                break;
            case "Rest1Update":
                mainCucc.getChildren().add(vbRest1);
                vbRest1.getChildren().removeAll(vbRest1.getChildren());
                cbRest1UpdateGender.getItems().removeAll(cbRest1UpdateGender.getItems());
                cbRest1UpdateStatus.getItems().removeAll(cbRest1UpdateStatus.getItems());
                cbRest1UpdateGender.getItems().addAll("male","female");
                cbRest1UpdateStatus.getItems().addAll("active","inactive");
                vbRest1.getChildren().addAll(vbRest1Update);
                break;
            case "Rest2Update":
                mainCucc.getChildren().add(vbRest2);
                vbRest2.getChildren().removeAll(vbRest2.getChildren());
                cbRest2UpdateGender.getItems().removeAll(cbRest2UpdateGender.getItems());
                cbRest2UpdateStatus.getItems().removeAll(cbRest2UpdateStatus.getItems());
                cbRest2UpdateGender.getItems().addAll("male","female");
                cbRest2UpdateStatus.getItems().addAll("active","inactive");
                vbRest2.getChildren().addAll(vbRest2Update);
                break;
            case "Parhuzamos":
                mainCucc.getChildren().add(vbEgyeb);
                vbEgyeb.getChildren().removeAll(vbEgyeb.getChildren());
                vbEgyeb.getChildren().addAll(vbParhuzamos);
                break;
            case "Stream":
                mainCucc.getChildren().add(vbEgyeb);
                vbEgyeb.getChildren().removeAll(vbEgyeb.getChildren());
                vbEgyeb.getChildren().addAll(vbStream);
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
            tRest1Get.getItems().removeAll(tRest1Get.getItems());
            tRest1Get.getColumns().removeAll(tRest1Get.getColumns());
            RestUser[] user = RestKliens.GET("https://gorest.co.in/public/v2/users");

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
        lResponseRest1Post.setText("Válasz:" + RestKliens.POST(user,"https://gorest.co.in/public/v2/users?access-token=336ac269fd5abc610b4de1d779127b223cb9dbf7078eed498a82a7a48abdf140"));
    }
    public void Rest1Delete(ActionEvent actionEvent) { Mutat("Rest1Delete"); }
    public void DoRest1Delete(ActionEvent actionEvent) throws IOException {
        String id = tfRest1Delete.getText();
        String re = RestKliens.DELETE("https://gorest.co.in/public/v2/users/"+id+"?access-token=336ac269fd5abc610b4de1d779127b223cb9dbf7078eed498a82a7a48abdf140");
        if(re == "Hiba!") lDeleted.setText("Hiba, nincs ilyen id!");
        else  lDeleted.setText("Sikeres törlés!");
    }
    public void Rest1Update(ActionEvent actionEvent) { Mutat("Rest1Update"); }
    public void DoRest1Update(ActionEvent actionEvent) throws IOException {
        RestUser user = new RestUser(Integer.parseInt(tfRest1UpdateId.getText()),tfRest1UpdateName.getText(),tfRest1UpdateEmail.getText(),cbRest1UpdateGender.getSelectionModel().getSelectedItem().toString(),cbRest1UpdateStatus.getSelectionModel().getSelectedItem().toString());
        if(RestKliens.PUT(user,"https://gorest.co.in/public/v2/users/"+user.getId()+"?access-token=336ac269fd5abc610b4de1d779127b223cb9dbf7078eed498a82a7a48abdf140") == "Hiba!") lUpdated.setText("Az id nem megfelelő vagy az email foglalt.");
        else lUpdated.setText("Sikeres");
    }
    public void Parhuzamos(ActionEvent actionEvent) {
        Mutat("Parhuzamos");
    }
    public void DoAThing(ActionEvent actionEvent)
    {
        Thread thread1 = new Thread(() -> {
            try {
                while(true)
                {
                    Platform.runLater(() -> lParhuzamos1.setText("Alma"));
                    Thread.sleep(2000);
                    Platform.runLater(() -> lParhuzamos1.setText(""));
                    Thread.sleep(2000);
                }
            }
            catch (Exception exc) {
                // should not be able to get here...
                throw new Error("Unexpected interruption");
            }
        });
        thread1.start();
        Thread thread2 = new Thread(() -> {
            try {
                while (true)
                {
                    Platform.runLater(() -> lParhuzamos2.setText("Korte"));
                    Thread.sleep(3000);
                    Platform.runLater(() -> lParhuzamos2.setText(""));
                    Thread.sleep(3000);
                }
            }
            catch (Exception exc) {
                // should not be able to get here...
                throw new Error("Unexpected interruption");
            }
        });
        thread2.start();
    }
    public void Stream(ActionEvent actionEvent) {
        Mutat("Stream");
        cbStreamkateg.getItems().removeAll(cbStreamkateg.getItems());
        cbStreamkateg.getItems().add(null);
        cbStreamkateg.getItems().addAll(kategoriak);
    }
    public void FilterStream(ActionEvent actionEvent) {
        ArrayList<osszRendeles> rend = dataBase.Rendelesek("");
        ArrayList<String> AdatB = new ArrayList<>();
        for(int i =0; i < rend.size(); i++)
        {
            AdatB.add(rend.get(i).toString());
        }
        String le ="";
        if(!tfStreamId.getText().isEmpty())
        {
            le +="^"+tfStreamId.getText()+",.*";
        }
        if(group1.getSelectedToggle() != null )
        {
            RadioButton selectedRadioButton = (RadioButton) group1.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText();
            if(!toogleGroupValue.equals("Semmi")){ le += ".*," + toogleGroupValue + ",.*";}
        }
        if(cbStreamkateg.getSelectionModel().getSelectedItem() != null) {
            le += ".*" + cbStreamkateg.getSelectionModel().getSelectedItem() + ",.*";
        }

        if(cbStreamVega.isSelected())
        {
            le+= "true$" ;

        }
        Pattern pattern = Pattern.compile(le);

        List<String> matching = AdatB.stream()
                .filter(pattern.asPredicate())
                .collect(Collectors.toList());

        ArrayList<osszRendeles> StreamRned = new ArrayList<>();
        for(int i = 0 ; i < matching.size(); i++)
        {
            String a[] = matching.get(i).split(",");
            osszRendeles b = new osszRendeles(Integer.parseInt(a[0]),Integer.parseInt(a[1]),Integer.parseInt(a[2]),a[3],a[4],a[5],a[6],parseBoolean(a[7]));
            StreamRned.add(b);
        }
        tStream.getItems().removeAll(tStream.getItems());
        tStream.getColumns().removeAll(tStream.getColumns());

        az = new TableColumn("Id");
        darab = new TableColumn("db");
        ar = new TableColumn("Ár");
        pizzanev = new TableColumn("PizzaNév");
        kategorianev = new TableColumn("KategóriaNév");
        felvetel = new TableColumn("Felvetel");
        kiszallitas = new TableColumn("kiszallitas");
        vega = new TableColumn("vega");

        tStream.getColumns().addAll(az, darab,ar,pizzanev,kategorianev,felvetel,kiszallitas,vega);

        az.setCellValueFactory(new PropertyValueFactory<>("az"));
        darab.setCellValueFactory(new PropertyValueFactory<>("darab"));
        ar.setCellValueFactory(new PropertyValueFactory<>("ar"));
        pizzanev.setCellValueFactory(new PropertyValueFactory<>("pizzanev"));
        kategorianev.setCellValueFactory(new PropertyValueFactory<>("kategorianev"));
        felvetel.setCellValueFactory(new PropertyValueFactory<>("felvetel"));
        kiszallitas.setCellValueFactory(new PropertyValueFactory<>("kiszallitas"));
        vega.setCellValueFactory(new PropertyValueFactory<>("vega"));

        tStream.getItems().addAll(StreamRned);
    }

    public void Rest2Get(ActionEvent actionEvent) throws IOException {
        Mutat("Rest2Get");
        Rest2Tabla();
    }
    public void Rest2Tabla() throws IOException {
        tRest2Get.getItems().removeAll(tRest2Get.getItems());
        tRest2Get.getColumns().removeAll(tRest2Get.getColumns());
        pizza[] pizza = AzureRestKliens.GET("http://20.13.128.109:8080/api");

        TableColumn RestPNev = new TableColumn("RestPNev");
        TableColumn RestKNev = new TableColumn("RestKNev");
        TableColumn RestVega = new TableColumn("RestVega");

        tRest2Get.getColumns().addAll(RestPNev,RestKNev,RestVega);

        RestPNev.setCellValueFactory(new PropertyValueFactory<>("nev"));
        RestKNev.setCellValueFactory(new PropertyValueFactory<>("knev"));
        RestVega.setCellValueFactory(new PropertyValueFactory<>("vega"));

        tRest2Get.getItems().addAll(pizza);
    }

    public void Rest2Post(ActionEvent actionEvent) {
        Mutat("Rest2Post");
    }
    public void Rest2PostDo(ActionEvent actionEvent) throws IOException {
        pizza Pizza = new pizza(tfRest2PostId.getText(),tfRest2PostName.getText(),tfRest2PostEmail.getText());
        lResponseRest2Post.setText("Válasz:" + AzureRestKliens.POST(Pizza,"http://20.13.128.109:8080/api"));
    }
    public void Rest2Delete(ActionEvent actionEvent) {
        Mutat("Rest2Delete");
    }
    public void DoRest2Delete(ActionEvent actionEvent) throws IOException {
        String nev = tfRest2Delete.getText();
        String re = AzureRestKliens.DELETE("http://20.13.128.109:8080/api/"+nev);
        if(re == "Hiba!") lDeleted.setText("Hiba, nincs ilyen id!");
        else  lDeleted.setText("Sikeres törlés!");
    }
    public void Rest2Update(ActionEvent actionEvent) {
        Mutat("Rest2Update");
    }


}
