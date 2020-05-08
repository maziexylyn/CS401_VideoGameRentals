package Classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class GamePlatform {
    private int game_id;
    private int platform_id;
    private int times_rented;
    private String last_rent_date;

    GamePlatform(int game_id, int platform_id, int times_rented, String last_rent_date) {
        this.game_id = game_id;
        this.platform_id = platform_id;
        this.times_rented = times_rented;
        this.last_rent_date = last_rent_date;
    }


    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }

    public int getTimes_rented() {
        return times_rented;
    }

    public void setTimes_rented(int times_rented) {
        this.times_rented = times_rented;
    }

    public String getLast_rent_date() {
        return last_rent_date;
    }

    public void setLast_rent_date(String last_rent_date) {
        this.last_rent_date = last_rent_date;
    }


    public static GamePlatform read(Connection conn, int game_id, int platform_id)
    {
        GamePlatform temp = null;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL GamePlatform_Read(?, ?)");
                stmt.setInt(1, game_id);
                stmt.setInt(2, platform_id);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    temp = new GamePlatform(
                            rs.getInt("game_id"),
                            rs.getInt("platform_id"),
                            rs.getInt("times_rented"),
                            rs.getString("last_rent_date")
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


    public static GamePlatform[] readList(Connection conn)
    {
        ArrayList<GamePlatform> gameplatforms = new ArrayList<>();

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL GamePlatform_Read_List()");
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    gameplatforms.add(new GamePlatform(
                            rs.getInt("game_id"),
                            rs.getInt("platform_id"),
                            rs.getInt("times_rented"),
                            rs.getString("last_rent_date")
                    ));
                }
                rs.close();
                stmt.close();

            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return gameplatforms.toArray(new GamePlatform[0]);
    }

    public static boolean create(Connection conn, int game_id, int platform_id)
    {
        boolean isCreated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL GamePlatform_Create(?, ?, ?)");
                stmt.setInt(1, game_id);
                stmt.setInt(2, platform_id);
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


    public static boolean update(Connection conn, int game_id, int platform_id, int add_times_rented)
    {
        boolean isUpdated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL GamePlatform_Update(?, ?, ?, ?)");
                stmt.setInt(1, game_id);
                stmt.setInt(2, platform_id);
                stmt.setInt(3, add_times_rented);
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
