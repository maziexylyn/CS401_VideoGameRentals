package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class User {
	private int id;
	private String passwordHash;
	private String email;
	private boolean isAdmin;

    public User(int id, String email, String passwordHash, boolean isAdmin) {
        this.id = id;
        this.passwordHash = passwordHash;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    /////////////////////////////////
    ////// DB Access Functions //////
    /////////////////////////////////

    public static User createUser(Connection conn, String email, String passwordHash){
        User temp = null;
        try{
            if(conn != null){
                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO user (email, passwordHash) VALUES ('"+email+"','"+passwordHash+"');";
                stmt.executeUpdate(sql);

                temp = readUserByEmail(conn, email);

            }
        }catch (Exception error){
            error.printStackTrace();
        }
        return temp;
    }

    public static User readUserByEmail(Connection conn, String email){
        System.out.println("Getting User...");

        User temp = null;

        try{
            if(conn != null){
                Statement stmt = conn.createStatement();
                String sql = "SELECT id, email, passwordHash, isAdmin FROM user WHERE email='"+email+"' LIMIT 1;";
                ResultSet rs = stmt.executeQuery(sql);

                while(rs.next()){
                    temp = new User(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("passwordHash"),
                            rs.getBoolean("isAdmin")
                    );
                }

            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return temp;

    }

}
