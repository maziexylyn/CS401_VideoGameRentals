package Classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class Platform {
    private int id;
    private String name;
    private boolean isActive;

    Platform(int id, String name, boolean isActive){
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
                            rs.getBoolean("isActive")
                    );
                }

            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return temp;

    }

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
                            rs.getBoolean("isActive")
                    ));
                }

            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return platforms.toArray(new Platform[0]);

    }

    public static boolean create(Connection conn, String platform_name) {
        boolean isCreated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Platform_Create(?, ?)");
                stmt.setString(1, platform_name);
                stmt.registerOutParameter(2, Types.TINYINT);
                stmt.execute();
                isCreated = stmt.getBoolean(2);

            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return isCreated;
    }

    public static boolean update(Connection conn, int platform_id, String platform_name, boolean platform_isActive) {
        boolean isUpdated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Platform_Update(?, ?, ?, ?)");
                stmt.setInt(1, platform_id);
                stmt.setString(2, platform_name);
                stmt.setBoolean(3, platform_isActive);
                stmt.registerOutParameter(4, Types.TINYINT);
                stmt.execute();
                isUpdated = stmt.getBoolean(4);
            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return isUpdated;
    }

}
