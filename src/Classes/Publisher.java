package Classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Publisher class handles all information associated with a game's publisher.
 */
public class Publisher {
    private int id;
    private String name;
    private boolean isActive;

    Publisher(int id, String name, boolean isActive){
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
     * Calls stored procedure to read a publisher
     * @param conn An open connection to the database
     * @param publisher_id Generated publisher ID
     * @return Publisher object
     */
    public static Publisher read(Connection conn, int publisher_id) {
        Publisher temp = null;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Publisher_Read(?)");
                stmt.setInt(1, publisher_id);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    temp = new Publisher(
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
     * Calls stored procedure to read publisher list
     * @param conn An open connection to the database
     * @param publisher_isActive Filters for active/inactive publishers
     * @return Publisher object array
     */
    public static Publisher[] readList(Connection conn, boolean publisher_isActive)
    {
        ArrayList<Publisher> publishers = new ArrayList<>();

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Publisher_Read_List(?)");
                stmt.setBoolean(1, publisher_isActive);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    publishers.add(new Publisher(
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

        return publishers.toArray(new Publisher[0]);
    }

    /**
     * Calls stored procedure to create a new publisher
     * @param conn An open connection to the database
     * @param publisher_name Publisher's name
     * @return Boolean to check if a new publisher was created
     */
    public static boolean create(Connection conn, String publisher_name)
    {
        boolean isCreated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Publisher_Create(?, ?)");
                stmt.setString(1, publisher_name);
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
     * Calls stored procedure to update a publisher
     * @param conn An open connection to the database
     * @param publisher_id Generated publisher ID
     * @param publisher_name Publisher's name
     * @param publisher_isActive Filters for active/inactive publishers
     * @return Boolean to check if a publisher was updated
     */
    public static boolean update(Connection conn, int publisher_id, String publisher_name, boolean publisher_isActive)
    {
        boolean isUpdated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Publisher_Update(?, ?, ?, ?)");
                stmt.setInt(1, publisher_id);
                stmt.setString(2, publisher_name);
                stmt.setBoolean(3, publisher_isActive);
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
