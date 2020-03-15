package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Users;

import java.net.URL;
import java.util.ResourceBundle;

public class adduserController implements Initializable {

    @FXML
    TextField nameTxt;
    @FXML
    TextField usernameTxt;
    @FXML
    TextField passwordTxt;
    @FXML
    TextField plateTxt;
    @FXML
    ChoiceBox roleChoice;

    @FXML
    Button dodajBtn;
    @FXML
    Button backBtn;

    String Role;
    ObservableList<String> role = FXCollections.observableArrayList();

    public void back(ActionEvent ev){
        Utils u = new Utils();
        u.showNewWindow("users", ev);
    }
    public void addUser(){
        String name = nameTxt.getText();
        String username = usernameTxt.getText();
        String password = passwordTxt.getText();
        String plates =plateTxt.getText();

        if(name.equals("") || username.equals("")|| password.equals("") || plates.equals("") || Role.equals("")){
            return;
        }

        Users u = new Users(0,name,username,password,plates,Role,1);
        Users.add(u);
        //popuniKorisnike();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.role.add(0,"ADMIN");
        this.role.add(1,"KORISNIK");
        roleChoice.setItems(this.role);
        roleChoice.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                Role=(role.get(newValue.intValue()));
            }
        });


    }
}
