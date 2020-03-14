package model;

public class Reservation {
    public Reservation() {
    }
    private int id;
    private String vrijeme_od;
    private String vrijeme_do;
    private int user_reservedID;

    public Reservation(int id, String vrijeme_od, String vrijeme_do, int user_reservedID) {
        this.id = id;
        this.vrijeme_od = vrijeme_od;
        this.vrijeme_do = vrijeme_do;
        this.user_reservedID = user_reservedID;
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





}
