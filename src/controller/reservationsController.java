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
import model.Reservation;
import model.Users;

import java.net.URL;
import java.util.ResourceBundle;

public class reservationsController implements Initializable {
    @FXML
    TableView tableTbl;

    @FXML
    TableColumn vrijemeodTbl;
    @FXML
    TableColumn vrijemedoTbl;
    @FXML
    TableColumn iduserTbl;
    @FXML
    TableColumn platesTbl;

    @FXML
    Button deleteBtn;

    @FXML
    Button backBtn;
    @FXML
    Button odjavaBtn;

    public void back(ActionEvent ev){
        Utils u = new Utils();
        u.showNewWindow("administracija", ev);
    }

    public void odjava(ActionEvent ev){
        Utils u = new Utils();
        u.showNewWindow("login", ev);
    }

    public void deleteReservation(ActionEvent ev){
        Window owner = deleteBtn.getScene().getWindow();

        Reservation reservation = (Reservation) this.tableTbl.getSelectionModel().getSelectedItem();
        if (reservation==null){
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Označite rezervaciju za brisanje");
            return;
        }
        else{
            Reservation.remove(reservation);
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Uspjeh!",
                    "Rezervacija "+ reservation.getId() + " uspješno izbrisana");
            popuniRezervacije();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.vrijemeodTbl.setCellValueFactory(new PropertyValueFactory<>("vrijeme_od"));
        this.vrijemedoTbl.setCellValueFactory(new PropertyValueFactory<>("vrijeme_do"));
        this.iduserTbl.setCellValueFactory(new PropertyValueFactory<>("user_reservedID"));
        this.platesTbl.setCellValueFactory(new PropertyValueFactory<>("plates"));

        popuniRezervacije();

    }
    Reservation reservations = new Reservation();

    private void popuniRezervacije(){
        ObservableList<Reservation> r = (ObservableList<Reservation>) reservations.selectReservation();
        this.tableTbl.setItems(r);
    }
}
