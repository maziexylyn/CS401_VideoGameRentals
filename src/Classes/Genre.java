package Classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class Genre {
    private int id;
    private String name;
    private boolean isActive;

    Genre(int id, String name, boolean isActive){
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

    public static Genre read(Connection conn, int genre_id)
    {
        Genre temp = null;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Genre_Read(?)");
                stmt.setInt(1, genre_id);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    temp = new Genre(
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

    public static Genre[] readList(Connection conn, boolean genre_isActive)
    {
        ArrayList<Genre> genres = new ArrayList<>();

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Genre_Read_List(?)");
                stmt.setBoolean(1, genre_isActive);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    genres.add(new Genre(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getBoolean("isActive")
                    ));
                }

            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return genres.toArray(new Genre[0]);
    }


    public static boolean create(Connection conn, String genre_name)
    {
        boolean isCreated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Genre_Create(?, ?)");
                stmt.setString(1, genre_name);
                stmt.registerOutParameter(2, Types.TINYINT);
                stmt.execute();
                isCreated = stmt.getBoolean(2);
            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return isCreated;
    }

    public static boolean update(Connection conn, int genre_id, String genre_name, boolean genre_isActive)
    {
        boolean isUpdated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Genre_Update(?, ?, ?, ?)");
                stmt.setInt(1, genre_id);
                stmt.setString(2, genre_name);
                stmt.setBoolean(3, genre_isActive);
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

