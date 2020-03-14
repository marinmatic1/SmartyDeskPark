package model;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private String host;
    private String user;
    private String password;
    private String db;

    private java.sql.Connection connection;

    public Connection() {
        this.host = "62.31.23.222:3306";
        this.user = "cloud";
        this.password = "WeIgHt123";
        this.db = "smartpark";
    }

    public Connection(String host, String user, String password, String db) {
        this.host = host;
        this.user = user;
        this.password = password;
        this.db = db;
    }

    public java.sql.Connection getConnection () throws Exception {
        this.connect();
        return this.connection;
    }

    public void connect () throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String connectionString =
                "jdbc:mysql://"+
                        this.host+"/"+
                        this.db+"?user="+
                        this.user+"&password="+
                        this.password+"&useSSL=false";
        System.out.println(connectionString);
        this.connection = DriverManager.getConnection(connectionString);
    }
    public void disconnect () throws Exception {
        this.connection.close();
    }
}
