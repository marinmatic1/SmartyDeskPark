package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Users {
    public Users() {
    }
    private int id;
    private String name;
    private String username;
    private String password;
    private String plates;
    private String role;
    private int approval;

    public Users(int id, String name, String username, String password, String plates, String role, int approval) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.plates = plates;
        this.role = role;
        this.approval = approval;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) throws Exception{
        if (role.toLowerCase().equals("admin") || role.toLowerCase().equals("korisnik"))
            this.role = role.toUpperCase();
        else
            throw new Exception("Molim vas odaberite ispravnu ulogu korisnika");
    }

    public int getApproval() {
        return approval;
    }

    public void setApproval(int approval) {
        this.approval = approval;
    }

    public static Users add(Users user) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("INSERT INTO users VALUES (null, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmnt.setString(1, user.getName());
            stmnt.setString(2, user.getUsername());
            stmnt.setString(3, user.getPassword());
            stmnt.setString(4, user.getPlates());
            stmnt.setString(5, user.getRole());
            stmnt.setInt(6, user.getApproval());
            stmnt.executeUpdate();

            ResultSet rs = stmnt.getGeneratedKeys();
            if(rs.next()){
                user.setId(rs.getInt(1));
            }
            stmnt.close();
            return user;
        }

    catch (SQLException e){
        System.out.println("Nisam uspio dodati korisnika: " + e.getMessage());
        return null;
        }
    }

    public static Users get(int id) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("SELECT * FROM users WHERE ID=?");
            stmnt.setInt(1, id);
            ResultSet rs = stmnt.executeQuery();

            if (rs.next()){
                return new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)
                );
            }
            stmnt.close();
            return null;
        } catch (SQLException e) {
            System.out.println("Korisnik se ne moze izvuci iz baze " + e.getMessage());
            return null;
        }
    }

    public static boolean remove(Users user) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("DELETE FROM users WHERE ID=?");
            stmnt.setInt(1, user.getId());
            stmnt.executeUpdate();
            stmnt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Nisam uspio pobrisati korisnika: " + e.getMessage());
            return false;
        }
    }

    public static boolean update(Users user) {
        try {
            PreparedStatement stmnt = Database.CONNECTION.prepareStatement("UPDATE users SET name=?, username=?, password=?, atablica=?, uloga=?, potvrda=?  WHERE ID=?");
            stmnt.setString(1, user.getName());
            stmnt.setString(2, user.getUsername());
            stmnt.setString(3, user.getPassword());
            stmnt.setString(4, user.getPlates());
            stmnt.setString(5, user.getRole());
            stmnt.setInt(6, user.getApproval());
            stmnt.setInt(7, user.getId());
            stmnt.executeUpdate();
            stmnt.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Nisam uspio urediti korisnika: " + e.getMessage());
            return false;
        }
    }

    public static List<Users> select() {
        ObservableList<Users> users = FXCollections.observableArrayList();
        try {
            Statement stmnt = Database.CONNECTION.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM users");


            while(rs.next()){
                users.add(new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)
                ));
            }
            stmnt.close();
            return users;
        } catch (SQLException e) {
            System.out.println("Nisam uspio izvuci korisnike iz baze: " + e.getMessage());
            return users;
        }
    }

    public static List<Users> selectOdobreni() {
        ObservableList<Users> users = FXCollections.observableArrayList();
        try {
            Statement stmnt = Database.CONNECTION.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM users WHERE potvrda = 1");


            while(rs.next()){
                users.add(new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)
                ));
            }
            stmnt.close();
            return users;
        } catch (SQLException e) {
            System.out.println("Nisam uspio izvuci odobrene korisnike iz baze: " + e.getMessage());
            return users;
        }
    }
    public static List<Users> selectNeodobreni() {
        ObservableList<Users> users = FXCollections.observableArrayList();
        try {
            Statement stmnt = Database.CONNECTION.createStatement();
            ResultSet rs = stmnt.executeQuery("SELECT * FROM users WHERE potvrda =2");


            while(rs.next()){
                users.add(new Users(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7)
                ));
            }
            stmnt.close();
            return users;
        } catch (SQLException e) {
            System.out.println("Nisam uspio izvuci neodobrene korisnike iz baze: " + e.getMessage());
            return users;
        }
    }


}
