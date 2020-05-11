package Classes;

import java.sql.*;
import java.util.ArrayList;

/**
 * Platform class handles all information associated with a game's platform.
 */
public class Platform {
    private int id;
    private String name;
    private boolean isActive;
    private String imagePath;

    Platform(int id, String name, boolean isActive, String imagePath){
        this.id = id;
        this.name = name;
        this.isActive = isActive;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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
     * Calls stored procedure to create a new platform
     * @param conn An open connection to the database
     * @param platform_name Platform's name
     * @param platform_imagePath Platform's image path
     * @return Boolean to check if the platform was created
     */
    public static boolean create(Connection conn, String platform_name, String platform_imagePath) {
        boolean isCreated = false;

        try{
            if(conn != null){

                CallableStatement stmt = conn.prepareCall("CALL Platform_Create(?, ?, ?)");
                stmt.setString(1, platform_name);
                stmt.setString(2, platform_imagePath);
                stmt.registerOutParameter(3, Types.TINYINT);
                stmt.execute();
                isCreated = stmt.getBoolean(3);
                stmt.close();
            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return isCreated;
    }

    /**
     * Calls stored procedure to read a platform
     * @param conn An open connection to the database
     * @param platform_id Generated platform ID
     * @return Platform object
     */
    public static Platform read(Connection conn, int platform_id) {
        Platform temp = null;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Platform_Read(?)");
                stmt.setInt(1, platform_id);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    temp = new Platform(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getBoolean("isActive"),
                            rs.getString("imagePath")
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
     * Calls stored procedure to read platform list
     * @param conn An open connection to the database
     * @param platform_isActive Filters for active/inactive platforms
     * @return Platform object array
     */
    public static Platform[] readList(Connection conn, boolean platform_isActive) {
        ArrayList<Platform> platforms = new ArrayList<>();

        try{
            if(conn != null){

                CallableStatement stmt = conn.prepareCall("CALL Platform_Read_List(?)");
                stmt.setBoolean(1, platform_isActive);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    platforms.add(new Platform(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getBoolean("isActive"),
                            rs.getString("imagePath")
                    ));
                }
                rs.close();
                stmt.close();

            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return platforms.toArray(new Platform[0]);

    }

    /**
     * Calls stored procedure to update platform
     * @param conn An open connection to the database
     * @param platform_id Generated platform ID
     * @param platform_name Platform's name
     * @param platform_isActive Filters for active/inactive platforms
     * @param platform_imagePath Platform's image path
     * @return Boolean to check if the platform was updated
     */
    public static boolean update(Connection conn, int platform_id, String platform_name, boolean platform_isActive, String platform_imagePath) {
        boolean isUpdated = false;

        try{
            if(conn != null){

                CallableStatement stmt = conn.prepareCall("CALL Platform_Update(?, ?, ?, ?, ?)");
                stmt.setInt(1, platform_id);
                stmt.setString(2, platform_name);
                stmt.setBoolean(3, platform_isActive);
                stmt.setString(4, platform_imagePath);
                stmt.registerOutParameter(5, Types.TINYINT);
                stmt.execute();
                isUpdated = stmt.getBoolean(5);
                stmt.close();
            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return isUpdated;
    }

}
