package Classes;

import db.DB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PublisherTest {

    private int test_id = 100;
    private String test_name = "Test Name";
    private boolean test_activity = true;
    private Publisher publisher;

    @BeforeEach
    void setUp() { publisher = new Publisher(test_id, test_name, test_activity);}

    @AfterEach
    void tearDown() { }

    @Test
    void getId() {assertEquals(test_id, publisher.getId());}

    @Test
    void setId() {assertEquals(test_id, publisher.getId());
        publisher.setId(test_id+1);
        assertEquals(test_id+1, publisher.getId()); }

    @Test
    void getName() {assertEquals(test_name, publisher.getName()); }

    @Test
    void setName() {String newName = "New Name";
        assertEquals(test_name, publisher.getName());
        publisher.setName(newName);
        assertEquals(newName, publisher.getName()); }

    @Test
    void isActive() {assertEquals(test_activity, publisher.isActive()); }

    @Test
    void setActive() {  assertEquals(test_activity, publisher.isActive());
        test_activity = true;
        publisher.setActive(test_activity);
        assertEquals(test_activity, publisher.isActive());
    }

    @Test
    void testCRUD(){
        System.out.println("Testing CRUD");
        try{
            DB db = new DB();
            if(db.openDB()){
                db.getConn().setAutoCommit(false); // MUST INCLUDE

                // Test Create
                System.out.println("Creating...");
                assertTrue(Publisher.create(db.getConn(), "New Item"));
                assertFalse(Publisher.create(db.getConn(), "New Item"));

                // Test Read
                System.out.println("Reading...");
                assertEquals(test_name, publisher.getName());
                publisher = Publisher.read(db.getConn(), 1);
                assertNotEquals(test_name, publisher.getName());

                // Test Update
                System.out.println("Updating...");

                assertTrue(Publisher.update(db.getConn(),1, test_name, test_activity));
                publisher = Publisher.read(db.getConn(), 1);
                assertEquals(test_name, publisher.getName());

                // Test ReadList
                System.out.println("Reading List...");
                Publisher[] publisher_array = Publisher.readList(db.getConn(), test_activity);
                assertTrue(publisher_array.length >= 2);

                boolean flag = true;
                for(Publisher temp: publisher_array){
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
