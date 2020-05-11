package db;

import java.sql.*;

/**
 * Database class handles all information associated with database connectivity.
 */

public class DB {

    private static final String host = "jdbc:mysql://258.mauk.house:3306/vgrzdb";
    private static final String username = "vgrzapp";
    private static final String password = "[VideoGameRentalzApp2020]";
    private Connection conn;

    public DB(){
        this.conn = null;
    }

    /**
     * Opens the database connection
     * @return Boolean to check if the connection is open
     */
    public boolean openDB(){
        System.out.println("Opening DB...");
        boolean isOpen = false;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(DB.host, DB.username, DB.password);
            isOpen = true;
        }catch(Exception err){
            err.printStackTrace();
        }
        return isOpen;
    }

    /**
     * Closes the database connection
     */
    public void closeDB(){
        try{
            this.conn.close();
        }catch(Exception err){
            err.printStackTrace();
        }
        System.out.println("Closed DB...");
    }

    public Connection getConn() {
        return conn;
    }
}
