package db;
import Classes.Platform;
import Classes.User;

import java.sql.*;


public class DB {

    private static final String host = "jdbc:mysql://258.mauk.house:3306/vgrzdb";
    private static final String username = "vgrzapp";
    private static final String password = "[VideoGameRentalzApp2020]";
    private Connection conn;

    public DB(){
        this.conn = null;
    }

    public static void main(String[] args){
        DB db = new DB();

        if(db.openDB()) {
            System.out.println("Database is connected");
            System.out.println(Platform.create(db.getConn(),"MaziePlatform"));
//            Platform[] platforms = Platform.readList(db.getConn());
//            for(Platform p: platforms){
//                System.out.println(p.getName());
//            }
        }
        db.closeDB();
        System.out.println("Database is closed");
    }

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
