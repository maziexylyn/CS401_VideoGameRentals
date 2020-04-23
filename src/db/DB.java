package db;
import Classes.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {

    private static final String host = "jdbc:mysql://258.mauk.house:3306/vgrzdb";
    private static final String username = "vgrzapp";
    private static final String password = "[VideoGameRentalzApp2020]";
    private Connection conn;

    public DB(){
        this.conn = null;
    }

    public boolean openDB(){
        System.out.println("Opening DB...");
        boolean isOpen = false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(DB.host, DB.username, DB.password);
            isOpen = true;
        }catch(Exception err){
            System.out.println(err);
        }
        return isOpen;
    }

    public void closeDB(){
        try{
            this.conn.close();
        }catch(Exception err){
            System.out.println(err);
        }
    }

    public Connection getConn() {
        return conn;
    }
}
