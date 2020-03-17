package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import model.AlertHelper;
import model.Users;

import java.net.URL;
import java.util.ResourceBundle;

public class approvalsController implements Initializable {

    @FXML
    TableView tableTbl;

    @FXML
    TableColumn nameTbl;
    @FXML
    TableColumn usernameTbl;
    @FXML
    TableColumn passwordTbl;
    @FXML
    TableColumn platesTbl;
    @FXML
    TableColumn roleTbl;
    @FXML
    TableColumn approvalTbl;


    @FXML
    Button deleteBtn;
    @FXML
    Button backBtn;
    @FXML
    Button odjavaBtn;

    @FXML
    Button approveBtn;




    public void approveAction (ActionEvent ev){
        Window owner = approveBtn.getScene().getWindow();
        Users user = (Users) this.tableTbl.getSelectionModel().getSelectedItem();

        if (user==null){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Označite korisnika za odobrenje");
            return;
        }
        else{
            user.setApproval(1);
            Users.update(user);
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Uspjeh!",
                    "Korisnik "+ user.getName() + " uspješno odobren");
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

    public void deleteZahtjev(ActionEvent ev){
        Window owner = approveBtn.getScene().getWindow();
        Users u = (Users) this.tableTbl.getSelectionModel().getSelectedItem();
        if(u==null){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Označite korisnika za trajno brisanje");
            return;
        }
        else{
            Users.remove(u);
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Uspijeh!",
                    "Zahtjev korisnika "+ u.getName() + " trajno obrisan!");
            this.popuniKorisnike();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.nameTbl.setCellValueFactory(new PropertyValueFactory<>("name"));
        this.usernameTbl.setCellValueFactory(new PropertyValueFactory<>("username"));
        this.passwordTbl.setCellValueFactory(new PropertyValueFactory<>("password"));
        this.platesTbl.setCellValueFactory(new PropertyValueFactory<>("plates"));
        this.roleTbl.setCellValueFactory(new PropertyValueFactory<>("Role"));
        this.approvalTbl.setCellValueFactory(new PropertyValueFactory<>("approval"));
        popuniKorisnike();
    }
    Users users = new Users();

    private void popuniKorisnike(){
        ObservableList<Users> u = (ObservableList<Users>) users.selectNeodobreni();
        this.tableTbl.setItems(u);
    }

}
