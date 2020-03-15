package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class administracijaController implements Initializable {


    @FXML
    Button approvalsBtn;
    @FXML
    Button usersBtn;
    @FXML
    Button reservationsBtn;
    @FXML
    Button backBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void back(ActionEvent ev){
        Utils u = new Utils();
        u.showNewWindow("login", ev);
    }

    public void openApprovals(ActionEvent ev){
        Utils u = new Utils();
        u.showNewWindow("approvals",ev);
    }
    public void openUsers(ActionEvent ev){
        Utils u = new Utils();
        u.showNewWindow("users",ev);
    }

    public void openReservations(ActionEvent ev){
        Utils u = new Utils();
        u.showNewWindow("reservations",ev);
    }

}
