package Classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Rating class handles all information associated with a game's rating.
 */
public class Rating {
    private int id;
    private String name;
    private boolean isActive;

    Rating(int id, String name, boolean isActive){
        this.id = id;
        this.name = name;
        this.isActive = isActive;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Calls stored procedure to read a rating
     * @param conn An open connection to the database
     * @param rating_id Genreated rating ID
     * @return Rating object
     */
    public static Rating read(Connection conn, int rating_id)
    {
        Rating temp = null;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Rating_Read(?)");
                stmt.setInt(1, rating_id);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    temp = new Rating(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getBoolean("isActive")
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

    /**
     * Calls stored procedure to read rating list
     * @param conn An open connection to the database
     * @param rating_isActive Filters for active/inactive ratings
     * @return Rating object array
     */
    public static Rating[] readList(Connection conn, boolean rating_isActive)
    {
        ArrayList<Rating> ratings = new ArrayList<>();

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Rating_Read_List(?)");
                stmt.setBoolean(1, rating_isActive);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    ratings.add(new Rating(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getBoolean("isActive")
                    ));
                }
                rs.close();
                stmt.close();

            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return ratings.toArray(new Rating[0]);
    }

    /**
     * Calls stored procedure to create a new rating
     * @param conn An open connection to the database
     * @param rating_name Rating's name
     * @return Boolean to check if a new rating was created
     */
    public static boolean create(Connection conn, String rating_name)
    {
        boolean isCreated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Rating_Create(?, ?)");
                stmt.setString(1, rating_name);
                stmt.registerOutParameter(2, Types.TINYINT);
                stmt.execute();
                isCreated = stmt.getBoolean(2);
                stmt.close();
            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return isCreated;
    }

    /**
     * Calls stored procedure to update a rating
     * @param conn An open connection to the database
     * @param rating_id Generated rating ID
     * @param rating_name Rating's name
     * @param rating_isActive Filters for active/inactive ratings
     * @return Boolean to check if a rating was updated
     */
    public static boolean update(Connection conn, int rating_id, String rating_name, boolean rating_isActive)
    {
        boolean isUpdated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Rating_Update(?, ?, ?, ?)");
                stmt.setInt(1, rating_id);
                stmt.setString(2, rating_name);
                stmt.setBoolean(3, rating_isActive);
                stmt.registerOutParameter(4, Types.TINYINT);
                stmt.execute();
                isUpdated = stmt.getBoolean(4);
                stmt.close();
            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return isUpdated;
    }
}
