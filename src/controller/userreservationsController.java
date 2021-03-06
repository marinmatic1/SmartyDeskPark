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

import java.net.URL;
import java.util.ResourceBundle;

public class userreservationsController implements Initializable  {

        @FXML
        TableView tableTbl;

        @FXML
        TableColumn datumodTbl;
        @FXML
        TableColumn vrijemeodTbl;
        @FXML
        TableColumn datumdoTbl;
        @FXML
        TableColumn vrijemedoTbl;
        @FXML
        TableColumn userTbl;
        @FXML
        TableColumn platesTbl;

        @FXML
        Button refreshBtn;
        @FXML
        Button backBtn;
        @FXML
        Button odjavaBtn;
        @FXML
        Button deleteBtn;

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

        public void refresh(ActionEvent ev){
            Window owner = refreshBtn.getScene().getWindow();
            popuniRezervacije();
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Uspjeh!",
                    "Podaci su osvježeni. ");
        }

        public void back(ActionEvent ev){
            Utils u = new Utils();
            u.showNewWindow("users", ev);
        }

        public void odjava(ActionEvent ev){
            Utils u = new Utils();
            u.showNewWindow("login", ev);
        }

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            this.datumodTbl.setCellValueFactory(new PropertyValueFactory<>("datum_od"));
            this.vrijemeodTbl.setCellValueFactory(new PropertyValueFactory<>("vrijeme_od"));
            this.datumdoTbl.setCellValueFactory(new PropertyValueFactory<>("datum_do"));
            this.vrijemedoTbl.setCellValueFactory(new PropertyValueFactory<>("vrijeme_do"));
            this.userTbl.setCellValueFactory(new PropertyValueFactory<>("imekorisnika"));
            this.platesTbl.setCellValueFactory(new PropertyValueFactory<>("plates"));

            popuniRezervacije();

        }
        Reservation reservations = new Reservation();

        private void popuniRezervacije(){
            ObservableList<Reservation> r = (ObservableList<Reservation>) reservations.selectUserReservation();
            this.tableTbl.setItems(r);
        }
    }


