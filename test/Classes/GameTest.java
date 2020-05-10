package Classes;

import db.DB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private int test_id = 100;
    private String test_title = "Test Title";
    private String test_description = "Test Description";
    private String test_imagePath = "imagePath";
    private int test_publisher_id = 100;
    private int test_genre_id = 100;
    private int test_rating_id = 100;
    private float test_price = 100.0f;
    private boolean test_activity = true;
    private Game game;

    @BeforeEach
    void setUp() { game = new Game(test_id, test_title, test_description, test_imagePath, test_publisher_id, test_genre_id, test_rating_id, test_price, test_activity);   }

    @AfterEach
    void tearDown() {}

    @Test
    void getId() {assertEquals(test_id, game.getId());}

    @Test
    void setId() {assertEquals(test_id, game.getId());
        game.setId(test_id+1);
        assertEquals(test_id+1, game.getId());}

    @Test
    void getTitle() {assertEquals(test_title, game.getTitle());}

    @Test
    void setTitle() {String newTitle = "New Title";
        assertEquals(test_title, game.getTitle());
        game.setTitle(newTitle);
        assertEquals(newTitle, game.getTitle());}

    @Test
    void getDescription() {assertEquals(test_description, game.getDescription());}

    @Test
    void setDescription() {String newDescription = "New Description";
        assertEquals(test_description, game.getDescription());
        game.setDescription(newDescription);
        assertEquals(newDescription, game.getDescription());}

    @Test
    void getImagePath() {assertEquals(test_imagePath, game.getImagePath());}

    @Test
    void setImagePath() {assertEquals(test_imagePath, game.getImagePath());
        String newImage = "Hello";
        game.setImagePath(newImage);
        assertEquals(newImage, game.getImagePath());}

    @Test
    void getPrice() {assertEquals(test_price, game.getPrice());}

    @Test
    void setPrice() {assertEquals(test_price, game.getPrice());
        game.setPrice(test_price);
        assertEquals(test_price, game.getPrice());}

    @Test
    void getPublisher_id() {assertEquals(test_publisher_id, game.getPublisher_id());}

    @Test
    void setPublisher_id() {assertEquals(test_publisher_id, game.getPublisher_id());
        game.setPublisher_id(test_publisher_id);
        assertEquals(test_publisher_id, game.getPublisher_id());}

    @Test
    void getGenre_id() {assertEquals(test_genre_id, game.getGenre_id());}

    @Test
    void setGenre_id() {assertEquals(test_genre_id, game.getGenre_id());
        game.setGenre_id(test_genre_id);
        assertEquals(test_genre_id, game.getGenre_id());}

    @Test
    void getRating_id() {assertEquals(test_rating_id, game.getRating_id());}

    @Test
    void setRating_id() {assertEquals(test_rating_id, game.getRating_id());
        game.setRating_id(test_rating_id);
        assertEquals(test_rating_id, game.getRating_id());}

    @Test
    void isActive() {assertEquals(test_activity, game.isActive());}

    @Test
    void setActive() {assertEquals(test_activity, game.isActive());
        test_activity = true;
        game.setActive(test_activity);
        assertEquals(test_activity, game.isActive());}

    @Test
    void testCRUD(){
        System.out.println("Testing CRUD");
        try{
            DB db = new DB();
            if(db.openDB()){
                db.getConn().setAutoCommit(false); // MUST INCLUDE

                // Test Create
                System.out.println("Creating...");
                assertTrue(Game.create(db.getConn(), "New Title", "New Description", test_imagePath, test_publisher_id, test_genre_id, test_rating_id, test_price));
                assertFalse(Game.create(db.getConn(), "New Title", "New Description", test_imagePath, test_publisher_id, test_genre_id, test_rating_id, test_price));

                // Test Read
                System.out.println("Reading...");
                assertEquals(test_title, game.getTitle());
                game = Game.read(db.getConn(), 1);
                assertNotEquals(test_title, game.getTitle());

                // Test Update
                System.out.println("Updating...");

                assertTrue(Game.update(db.getConn(), 1, test_title, test_description, test_imagePath, test_publisher_id, test_genre_id, test_rating_id, test_price, test_activity));
                game = Game.read(db.getConn(), 1);
                assertEquals(test_title, game.getTitle());

                // Test ReadList
                System.out.println("Reading List...");
                Game[] game_array = Game.readList(db.getConn(), test_activity);
                assertTrue(game_array.length >= 2);

                boolean flag = true;
                for(Game temp: game_array){
                    if(temp.getTitle().equals(test_title)){
                        assertEquals(1, temp.getId());
                        assertEquals(test_title, temp.getTitle());
                        assertEquals(test_description, temp.getDescription());
                        assertEquals(test_imagePath, temp.getImagePath());
                        assertEquals(test_publisher_id, temp.getPublisher_id());
                        assertEquals(test_genre_id, temp.getGenre_id());
                        assertEquals(test_rating_id, temp.getRating_id());
                        assertEquals(test_price, temp.getPrice());
                        assertEquals(test_activity, temp.isActive());

                        flag = false;
                    }
                }

                if(flag){
                    fail();
                }

                db.getConn().rollback(); // MUST INCLUDE
                db.closeDB(); // MUST INCLUDE
            }
        }catch(Exception error){
            error.printStackTrace();
            fail();
        }
        System.out.println("Finished Testing CRUD");
    }
}