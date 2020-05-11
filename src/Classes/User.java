package Classes;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;

/**
 * User class handles all information associated with a user.
 */
public class User {
	private int id;
	private String passwordHash;
	private String email;
	private int role_id;

    public User(int id, String email, String passwordHash, int role_id ) {
        this.id = id;
        this.passwordHash = passwordHash;
        this.email = email;
        this.role_id = role_id;
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

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    /////////////////////////////////
    ////// DB Access Functions //////
    /////////////////////////////////

    /**
     * Calls stored procedure to create new user
     * @param conn An open connection to the database
     * @param user_email User's email
     * @param user_password User's password
     * @param role Type Enum for role
     * @return Boolean to check if the user was created
     */
    public static boolean create(Connection conn, String user_email, String user_password, Role.Type role) {
        boolean isCreated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL User_Create(?, ?, ?, ?)");
                stmt.setString(1, user_email);
                stmt.setString(2, user_password);
                stmt.setInt(3, role.getCode());
                stmt.registerOutParameter(4, Types.TINYINT);
                stmt.execute();
                isCreated = stmt.getBoolean(4);
                stmt.close();
            }
        }catch(Exception error){
            error.printStackTrace();
        }
        return isCreated;
    }

    /**
     * Calls stored procedure to check if user exists by user's email address
     * @param conn An open connection to the database
     * @param user_email User's email
     * @return Boolean to check if the user exists by email
     */
    public static boolean existsByEmail(Connection conn, String user_email){
        boolean exists = false;
        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL User_Exists_ByEmail(?, ?)");
                stmt.setString(1, user_email);
                stmt.registerOutParameter(2, Types.TINYINT);
                stmt.execute();
                exists = stmt.getBoolean(2);
                stmt.close();
            }
        }catch(Exception error){
            error.printStackTrace();
        }
        return exists;
    }

    /**
     * Calls stored procedure to read a user by user's email address
     * @param conn An open connection to the database
     * @param user_email User's email
     * @return User object
     */
    public static User readByEmail(Connection conn, String user_email) {
        User temp = null;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL User_Read_ByEmail(?)");
                stmt.setString(1, user_email);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    temp = new User(
                            rs.getInt("id"),
                            rs.getString("email"),
                            rs.getString("passwordHash"),
                            rs.getInt("role_id")
                    );
                }
                rs.close();
                stmt.close();
            }
        }catch(Exception error){
            error.printStackTrace();
        }
        return temp;
    }

}
