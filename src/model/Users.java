package model;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public void setRole(String role) {
        this.role = role;
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
            return user;
        }

    catch (SQLException e){
        System.out.println("Nisam uspio dodati korisnika: " + e.getMessage());
        return null;
        }
    }







}
