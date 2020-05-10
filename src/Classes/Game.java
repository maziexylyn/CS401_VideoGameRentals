package Classes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

public class Game {
    private int id;
    private String title;
    private String description;
    private String imagePath;
    private int publisher_id;
    private int genre_id;
    private int rating_id;
    private float price;
    private boolean isActive;

    Game(int id, String title, String description, String imagePath, int publisher_id, int genre_id, int rating_id, float price, boolean isActive){
        this.id = id;
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.publisher_id = publisher_id;
        this.genre_id = genre_id;
        this.rating_id = rating_id;
        this.price = price;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String image) {
        this.imagePath = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public static Game read(Connection conn, int game_id)
    {
        Game temp = null;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Game_Read(?)");
                stmt.setInt(1, game_id);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    temp = new Game(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("imagePath"),
                            rs.getInt("publisher_id"),
                            rs.getInt("genre_id"),
                            rs.getInt("rating_id"),
                            rs.getFloat("currentPrice"),
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


    public static Game[] readList(Connection conn, boolean game_isActive)
    {
        ArrayList<Game> games = new ArrayList<>();

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Game_Read_List(?)");
                stmt.setBoolean(1, game_isActive);
                ResultSet rs = stmt.executeQuery();

                while(rs.next()){
                    games.add(new Game(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getString("imagePath"),
                            rs.getInt("publisher_id"),
                            rs.getInt("genre_id"),
                            rs.getInt("rating_id"),
                            rs.getFloat("currentPrice"),
                            rs.getBoolean("isActive")
                    ));
                }
                rs.close();
                stmt.close();

            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return games.toArray(new Game[0]);
    }


    public static boolean create(Connection conn, String game_title, String game_description, String game_image, int publisher_id, int genre_id, int rating_id, float game_price)
    {
        boolean isCreated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Game_Create(?, ?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, game_title);
                stmt.setString(2, game_description);
                stmt.setString(3, game_image);
                stmt.setInt(4, publisher_id);
                stmt.setInt(5, genre_id);
                stmt.setInt(6, rating_id);
                stmt.setFloat(7, game_price);
                stmt.registerOutParameter(8, Types.TINYINT);
                stmt.execute();
                isCreated = stmt.getBoolean(8);
                stmt.close();
            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return isCreated;
    }


    public static boolean update(Connection conn, String game_title, String game_description, String game_image, int publisher_id, int genre_id, int rating_id, float game_price, boolean game_isActive)
    {
        boolean isUpdated = false;

        try{
            if(conn != null){
                CallableStatement stmt = conn.prepareCall("CALL Game_Update(?, ?, ?, ?, ?, ?, ?, ?)");
                stmt.setString(1, game_title);
                stmt.setString(2, game_description);
                stmt.setString(3, game_image);
                stmt.setInt(4, publisher_id);
                stmt.setInt(5, genre_id);
                stmt.setInt(6, rating_id);
                stmt.setFloat(7, game_price);
                stmt.registerOutParameter(8, Types.TINYINT);
                stmt.execute();
                isUpdated = stmt.getBoolean(8);
                stmt.close();
            }
        }catch(Exception error){
            error.printStackTrace();
        }

        return isUpdated;
    }
}
