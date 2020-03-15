package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Window;
import model.AlertHelper;
import model.Database;
import model.Users;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    private Label messageLabel;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        String username = nameField.getText();
        String password = passwordField.getText();
        Window owner = submitButton.getScene().getWindow();
        if(nameField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Unesite korisničko ime");
            return;
        }

        if(passwordField.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Unesite lozinku");
            return;
        }

        //AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
        //        "Dobrodošli " + nameField.getText());
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            stmnt.setString(1, username);
            stmnt.setString(2, password);
            ResultSet rs = stmnt.executeQuery();
            if (rs.next()) {
                this.logiraniKorisnik = Users.get(rs.getInt(1));
                Utils u = new Utils();

                if (logiraniKorisnik.getRole().equals("ADMIN")) {
                    u.showNewWindow("administracija", event);
                } else if (logiraniKorisnik.getRole().equals("KORISNIK")){

                messageLabel.setText("Nemate pravo pristupa ovoj stranici");
                messageLabel.setTextFill(Color.web("#FF0000"));
                return;
                }

            }
            else{
                messageLabel.setText("Nemate pravo pristupa ovoj stranici");
                messageLabel.setTextFill(Color.web("#FF0000"));
                return;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public static Users logiraniKorisnik;

    public int getLogiraniId(){
        return logiraniKorisnik.getId();
    }
    public String getLogiraniIme(){
        return logiraniKorisnik. getName();
    }
    public String getLogiraniUsername(){
        return logiraniKorisnik.getUsername();
    }
    public String getLogiraniPassword(){
        return logiraniKorisnik.getPassword();
    }
    public String getLogiraniAtablica(){
        return logiraniKorisnik.getPlates();
    }
    public String getLogiraniUloga(){
        return logiraniKorisnik.getRole();
    }
    public int getLogiraniPotvrda(){
        return logiraniKorisnik.getApproval();
    }

}
