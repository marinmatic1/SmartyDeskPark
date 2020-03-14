package model;

public class Users {
    public Users() {
    }
    private int id;
    private String name;
    private String username;
    private String password;
    private String plates;
    private String role;
    private boolean approval;

    public Users(int id, String name, String username, String password, String plates, String role, boolean approval) {
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

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    



}
