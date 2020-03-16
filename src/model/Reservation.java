package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Reservation {
    public Reservation() {
    }
    private int id;
    private String vrijeme_od;
    private String vrijeme_do;
    private int user_reservedID;

    private String plates;
    public String getPlates() { return plates; }
    public void setPlates(String plates) { this.plates = plates; }


    public Reservation(int id, String vrijeme_od, String vrijeme_do, int user_reservedID) {
        this.id = id;
        this.vrijeme_od = vrijeme_od;
        this.vrijeme_do = vrijeme_do;
        this.user_reservedID = user_reservedID;
    }

    public Reservation(int id, String vrijeme_od, String vrijeme_do, int user_reservedID, String plates) {
        this.id = id;
        this.vrijeme_od = vrijeme_od;
        this.vrijeme_do = vrijeme_do;
        this.user_reservedID = user_reservedID;
        this.plates = plates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVrijeme_od() {
        return vrijeme_od;
    }

    public void setVrijeme_od(String vrijeme_od) {
        this.vrijeme_od = vrijeme_od;
    }

    public String getVrijeme_do() {
        return vrijeme_do;
    }

    public void setVrijeme_do(String vrijeme_do) {
        this.vrijeme_do = vrijeme_do;
    }

    public int getUser_reservedID() {
        return user_reservedID;
    }

    public void setUser_reservedID(int user_reservedID) {
        this.user_reservedID = user_reservedID;
    }


    public static Reservation add(Reservation reservation) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("INSERT INTO reservations VALUES (null, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmnt.setInt(1, reservation.getId());
            stmnt.setString(2, reservation.getVrijeme_od());
            stmnt.setString(3, reservation.getVrijeme_do());
            stmnt.setInt(4, reservation.getUser_reservedID());
            stmnt.executeUpdate();

            ResultSet rs = stmnt.getGeneratedKeys();
            if(rs.next()){
                reservation.setId(rs.getInt(1));
            }
            stmnt.close();
            return reservation;
        }

        catch (SQLException e){
            System.out.println("Nisam uspio dodati rezervaciju. " + e.getMessage());
            return null;
        }
    }

    public static Reservation get(int id) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("SELECT * FROM reservations WHERE ID=?");
            stmnt.setInt(1, id);
            ResultSet rs = stmnt.executeQuery();

            if (rs.next()){
                return new Reservation(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                );
            }
            stmnt.close();
            return null;
        } catch (SQLException e) {
            System.out.println("Rezervacija se ne moze izvuci iz baze " + e.getMessage());
            return null;
        }
    }

    public static boolean remove(Reservation reservation) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("DELETE FROM reservations WHERE ID=?");
            stmnt.setInt(1, reservation.getId());
            stmnt.executeUpdate();
            stmnt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Nisam uspio obrisati rezervaciju: " + e.getMessage());
            return false;
        }
    }

    public static boolean update(Reservation reservation) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("UPDATE reservations SET vrijeme_od=?, vrijeme_do=?, user_rezervirao_fk  WHERE ID=?");
            stmnt.setString(1, reservation.getVrijeme_od());
            stmnt.setString(2, reservation.getVrijeme_do());
            stmnt.setInt(3, reservation.getUser_reservedID());
            stmnt.setInt(4, reservation.getId());
            stmnt.executeUpdate();
            stmnt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Nisam uspio urediti rezervaciju: " + e.getMessage());
            return false;
        }
    }

    public static List<Reservation> select() {
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        try {
            Statement stmnt = Database.CONNECTION.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM reservations");


            while(rs.next()){
                reservations.add(new Reservation(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4)
                ));
            }
            stmnt.close();
            return reservations;
        } catch (SQLException e) {
            System.out.println("Nisam uspio izvuci rezervacije iz baze: " + e.getMessage());
            return reservations;
        }
    }

    public static List<Reservation> selectReservation() {

        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        try {
            Statement stmnt = Database.CONNECTION.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT reservations.ID, reservations.vrijeme_od, reservations.vrijeme_do, reservations.user_rezervirao_fk, users.atablica\n" +
                    "FROM reservations, users\n" +
                    "WHERE user_rezervirao_fk=users.ID");


            while(rs.next()){
                reservations.add(new Reservation(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                ));
            }
            return reservations;
        } catch (SQLException e) {
            System.out.println("Nisam uspio izvuci rezervacije iz baze: " + e.getMessage());
            return reservations;
        }

    }


}
