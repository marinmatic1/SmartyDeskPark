package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import model.AlertHelper;
import model.Users;

import java.net.URL;
import java.util.ResourceBundle;

public class usersController implements Initializable {
    @FXML
    TableView tableTbl;

    @FXML
    TableColumn nameTbl;
    @FXML
    TableColumn usernameTbl;
    @FXML
    TableColumn plateTbl;
    @FXML
    TableColumn roleTbl;

    @FXML
    Button openURbtn;
    @FXML
    Button deleteBtn;
    @FXML
    Button backBtn;
    @FXML
    Button odjavaBtn;
    @FXML
    Button refreshBtn;
    @FXML
    Button openAddUserBtn;
    @FXML
    Button setAdminBtn;

    public static Users izabranikorisnik;

    public void openUserReservations(ActionEvent ev){
        Window owner = openURbtn.getScene().getWindow();
        Users user = (Users) this.tableTbl.getSelectionModel().getSelectedItem();

        if (user==null){
            AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Upozorenje!",
                    "Označite korisnika za pregled rezervacija. ");
            return;
        }
        else{
            this.izabranikorisnik = user;
            Utils u = new Utils();
            u.showNewWindow("userreservations", ev);
        }
    }
    public int getIzabraniId(){ return izabranikorisnik.getId(); }
    public String getIzabraniIme(){ return izabranikorisnik.getName(); }
    public String getIzabraniUsername(){ return izabranikorisnik.getUsername(); }

    public void refresh(ActionEvent ev){
        Window owner = refreshBtn.getScene().getWindow();
        popuniKorisnike();
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Uspjeh!",
                "Podaci su osvježeni. ");
    }

    public void setAdmin (ActionEvent ev) throws Exception {
        Window owner = setAdminBtn.getScene().getWindow();
        Users user = (Users) this.tableTbl.getSelectionModel().getSelectedItem();

        if (user==null){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Označite korisnika za postavljanje");
            return;
        }
        else if (user.getRole().equals("ADMIN")){
            AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Upozorenje!",
                    "Korisnik "+ user.getName() + " je već postavljen za administratora. Molim izaberite drugog korisnika");
            return;
        }
        else{
            user.setRole("ADMIN");
            Users.update(user);
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Uspjeh!",
                    "Korisnik "+ user.getName() + " uspješno postavljen za administratora");
            popuniKorisnike();
        }
    }
    public void back(ActionEvent ev){
        Utils u = new Utils();
        u.showNewWindow("administracija", ev);
    }

    public void odjava(ActionEvent ev){
        Utils u = new Utils();
        u.showNewWindow("login", ev);
    }

    public void openAdduser(ActionEvent ev){
        Utils u = new Utils();
        u.showNewWindow("adduser", ev);
    }

    public void deleteUser(ActionEvent ev){
        Window owner = deleteBtn.getScene().getWindow();

        Users user = (Users) this.tableTbl.getSelectionModel().getSelectedItem();
        if (user==null){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Označite korisnika za brisanje");
            return;
        }
        else{
            user.setApproval(2);
            Users.update(user);
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Uspjeh!",
                    "Korisnik "+ user.getName() + " uspješno izbrisan");
            popuniKorisnike();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.nameTbl.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.usernameTbl.setCellValueFactory(new PropertyValueFactory<>("username"));
        this.plateTbl.setCellValueFactory(new PropertyValueFactory<>("plates"));
        this.roleTbl.setCellValueFactory(new PropertyValueFactory<>("Role"));

        popuniKorisnike();
    }
    Users users = new Users();

    private void popuniKorisnike(){
        ObservableList<Users> u = (ObservableList<Users>) users.selectOdobreni();
        this.tableTbl.setItems(u);
    }

}
