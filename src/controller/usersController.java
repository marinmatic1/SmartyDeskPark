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
    Button deleteBtn;
    @FXML
    Button backBtn;
    @FXML
    Button odjavaBtn;
    @FXML
    Button openAddUserBtn;

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
