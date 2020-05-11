package Classes;

import db.DB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamePlatformInfoTest {
    private int test_game_id = 10;
    private String test_title = "Test Title";
    private String test_imagePath = "Test Image";
    private int test_genre_id = 10;
    private int test_rating_id = 10;
    private int test_publisher_id = 10;
    private float test_currentPrice = 50.00f;
    private String test_platform_ids = "Test Platform ID";
    private GamePlatformInfo gameplatforminfo;

    @BeforeEach
    void setUp() {gameplatforminfo = new GamePlatformInfo(test_game_id,test_title, test_imagePath, test_genre_id, test_rating_id, test_publisher_id, test_currentPrice, test_platform_ids);}

    @AfterEach
    void tearDown() {}

    @Test
    void getCurrentPrice() {}

    @Test
    void setCurrentPrice() {
    }

    @Test
    void getGame_id() {assertEquals(test_game_id, gameplatforminfo.getGame_id());}

    @Test
    void setGame_id() {assertEquals(test_game_id, gameplatforminfo.getGame_id());
        gameplatforminfo.setGame_id(test_game_id);
        assertEquals(test_game_id, gameplatforminfo.getGame_id());}

    @Test
    void getTitle() {assertEquals(test_title, gameplatforminfo.getTitle());}

    @Test
    void setTitle() {String newTitle = "New Title";
        assertEquals(test_title, gameplatforminfo.getTitle());
        gameplatforminfo.setTitle(newTitle);
        assertEquals(newTitle, gameplatforminfo.getTitle()); }

    @Test
    void getImagePath() {assertEquals(test_imagePath, gameplatforminfo.getImagePath());}

    @Test
    void setImagePath() {assertEquals(test_imagePath, gameplatforminfo.getImagePath());
        String newImage = "Hello";
        gameplatforminfo.setImagePath(newImage);
        assertEquals(newImage, gameplatforminfo.getImagePath());}

    @Test
    void getGenre_id() {assertEquals(test_genre_id, gameplatforminfo.getGenre_id()); }

    @Test
    void setGenre_id() {assertEquals(test_genre_id, gameplatforminfo.getGenre_id());
        gameplatforminfo.setGenre_id(test_genre_id);
        assertEquals(test_genre_id, gameplatforminfo.getGenre_id());}

    @Test
    void getRating_id() {assertEquals(test_rating_id, gameplatforminfo.getRating_id());}

    @Test
    void setRating_id() {assertEquals(test_rating_id, gameplatforminfo.getRating_id());
        gameplatforminfo.setRating_id(test_rating_id);
        assertEquals(test_rating_id, gameplatforminfo.getRating_id());}

    @Test
    void getPublisher_id() {assertEquals(test_publisher_id, gameplatforminfo.getPublisher_id());}

    @Test
    void setPublisher_id() {assertEquals(test_publisher_id, gameplatforminfo.getPublisher_id());
        gameplatforminfo.setPublisher_id(test_publisher_id);
        assertEquals(test_publisher_id, gameplatforminfo.getPublisher_id());}

    @Test
    void getPlatform_ids() {assertEquals(test_platform_ids, gameplatforminfo.getPlatform_ids());}

    @Test
    void setPlatform_ids() {assertEquals(test_platform_ids, gameplatforminfo.getPlatform_ids());
        gameplatforminfo.setPlatform_ids(test_platform_ids);
        assertEquals(test_platform_ids, gameplatforminfo.getPlatform_ids());}


    //I'm not sure how to format this cause of the way the procedure is x.x
    //@Test
    /*void read() {
        try{
            DB db = new DB();
            if(db.openDB()){
                db.getConn().setAutoCommit(false); // MUST INCLUDE

                // Test Read
                System.out.println("Reading...");
                assertEquals(test_title, gameplatforminfo.getTitle());
                gameplatforminfo = GamePlatformInfo[].read(db.getConn());
                assertNotEquals(test_title, gameplatforminfo.getTitle());
                db.getConn().rollback(); // MUST INCLUDE
                db.closeDB(); // MUST INCLUDE
            }

        }catch(Exception error){
            error.printStackTrace();
            fail();
        }
        System.out.println("Finished Testing Read");
    }*/
}