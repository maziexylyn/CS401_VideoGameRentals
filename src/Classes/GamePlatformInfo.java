package Classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * GamePlatformInfo class handles all information associated with a game.
 */
public class GamePlatformInfo {
    private int game_id;
    private String title;
    private String imagePath;
    private int genre_id;
    private int rating_id;
    private int publisher_id;
    private float currentPrice;
    private String platform_ids;

    public GamePlatformInfo(int game_id, String title, String imagePath, int genre_id, int rating_id, int publisher_id, float currentPrice, String platform_ids){
        this.game_id = game_id;
        this.title = title;
        this.imagePath = imagePath;
        this.genre_id = genre_id;
        this.rating_id = rating_id;
        this.publisher_id = publisher_id;
        this.currentPrice = currentPrice;
        this.platform_ids = platform_ids;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(float currentPrice) {
        this.currentPrice = currentPrice;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public int getRating_id() {
        return rating_id;
    }

    public void setRating_id(int rating_id) {
        this.rating_id = rating_id;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getPlatform_ids() {
        return platform_ids;
    }

    public void setPlatform_ids(String platform_ids) {
        this.platform_ids = platform_ids;
    }

    /**
     * Calls stored procedure to obtain information on currently active game platforms
     * @param conn An open connection to the database
     * @return GamePlatformInfo object array
     */
    public static GamePlatformInfo[] read(Connection conn)
    {
        ArrayList<GamePlatformInfo> gamePlatformInfos = new ArrayList<>();

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL GamePlatformInfo_Active_Read()");
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    gamePlatformInfos.add(new GamePlatformInfo(
                            rs.getInt("game_id"),
                            rs.getString("title"),
                            rs.getString("imagePath"),
                            rs.getInt("genre_id"),
                            rs.getInt("rating_id"),
                            rs.getInt("publisher_id"),
                            rs.getFloat("currentPrice"),
                            rs.getString("platform_ids")
                    ));
                }
                rs.close();
                stmt.close();

            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return gamePlatformInfos.toArray(new GamePlatformInfo[0]);
    }
}
