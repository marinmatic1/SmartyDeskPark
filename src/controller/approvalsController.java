package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    public void back(ActionEvent ev){
        Utils u = new Utils();
        u.showNewWindow("administracija", ev);
    }
    public void deleteZahtjev(ActionEvent ev){
        Users u = (Users) this.tableTbl.getSelectionModel().getSelectedItem();
        if(u==null){
            return;
        }
        else{
            Users.remove(u);
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
