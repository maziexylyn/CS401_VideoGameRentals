package Classes;

import db.DB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenreTest {

    private int test_id = 100;
    private String test_name = "Test Name";
    private boolean test_activity = true;
    private Genre genre;

    @BeforeEach
    void setUp() {genre = new Genre(test_id, test_name, test_activity); }

    @AfterEach
    void tearDown() { }

    @Test
    void getId() {assertEquals(test_id, genre.getId());}

    @Test
    void setId() {assertEquals(test_id, genre.getId());
        genre.setId(test_id+1);
        assertEquals(test_id+1, genre.getId()); }

    @Test
    void getName() {assertEquals(test_name, genre.getName());}

    @Test
    void setName() {String newName = "New Name";
        assertEquals(test_name, genre.getName());
        genre.setName(newName);
        assertEquals(newName, genre.getName());}

    @Test
    void isActive() {assertEquals(test_activity, genre.isActive());}

    @Test
    void setActive() {assertEquals(test_activity, genre.isActive());
        test_activity = true;
        genre.setActive(test_activity);
        assertEquals(test_activity, genre.isActive()); }

    @Test
    void testCRUD(){
        System.out.println("Testing CRUD");
        try{
            DB db = new DB();
            if(db.openDB()){
                db.getConn().setAutoCommit(false); // MUST INCLUDE

                // Test Create
                System.out.println("Creating...");
                assertTrue(Genre.create(db.getConn(), "New Item"));
                assertFalse(Genre.create(db.getConn(), "New Item"));

                // Test Read
                System.out.println("Reading...");
                assertEquals(test_name, genre.getName());
                genre = Genre.read(db.getConn(), 1);
                assertNotEquals(test_name, genre.getName());

                // Test Update
                System.out.println("Updating...");

                assertTrue(Genre.update(db.getConn(),1, test_name, test_activity));
                genre = Genre.read(db.getConn(), 1);
                assertEquals(test_name, genre.getName());

                // Test ReadList
                System.out.println("Reading List...");
                Genre[] genre_array = Genre.readList(db.getConn(), test_activity);
                assertTrue(genre_array.length >= 2);

                boolean flag = true;
                for(Genre temp: genre_array){
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