package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class addreservationController implements Initializable {

    @FXML
    Button backBtn;
    public void back(ActionEvent ev){
        Utils u = new Utils();
        u.showNewWindow("reservations", ev);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
