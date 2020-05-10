package Classes;

import db.DB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RatingTest {

    private int test_id = 100;
    private String test_name = "Test Name";
    private boolean test_activity = true;
    private Rating rating;

    @BeforeEach
    void setUp() {rating = new Rating(test_id,test_name,test_activity);}

    @AfterEach
    void tearDown() {}

    @Test
    void getId() {assertEquals(test_id, rating.getId());}

    @Test
    void setId() {assertEquals(test_id, rating.getId());
            rating.setId(test_id+1);
            assertEquals(test_id+1, rating.getId()); }

    @Test
    void getName() {assertEquals(test_name, rating.getName()); }

    @Test
    void setName() {String newName = "New Name";
        assertEquals(test_name, rating.getName());
        rating.setName(newName);
        assertEquals(newName, rating.getName());}

    @Test
    void isActive() {assertEquals(test_activity, rating.isActive());}

    @Test
    void setActive() {assertEquals(test_activity, rating.isActive());
        test_activity = true;
        rating.setActive(test_activity);
        assertEquals(test_activity, rating.isActive());}

    @Test
    void testCRUD(){
        System.out.println("Testing CRUD");
        try{
            DB db = new DB();
            if(db.openDB()){
                db.getConn().setAutoCommit(false); // MUST INCLUDE

                // Test Create
                System.out.println("Creating...");
                assertTrue(Rating.create(db.getConn(), "New Item"));
                assertFalse(Rating.create(db.getConn(), "New Item"));

                // Test Read
                System.out.println("Reading...");
                assertEquals(test_name, rating.getName());
                rating = Rating.read(db.getConn(), 1);
                assertNotEquals(test_name, rating.getName());

                // Test Update
                System.out.println("Updating...");

                assertTrue(Rating.update(db.getConn(),1, test_name, test_activity));
                rating = Rating.read(db.getConn(), 1);
                assertEquals(test_name, rating.getName());

                // Test ReadList
                System.out.println("Reading List...");
                Rating[] rating_array = Rating.readList(db.getConn(), test_activity);
                assertTrue(rating_array.length >= 2);

                boolean flag = true;
                for(Rating temp: rating_array){
                    if(temp.getName().equals(test_name)){
                        assertEquals(1, temp.getId());
                        assertEquals(test_name, temp.getName());
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