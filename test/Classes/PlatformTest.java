package Classes;

import db.DB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlatformTest {

    private int test_id = 100;
    private String test_name = "Test Name";
    private boolean test_activity = true;
    private Platform platform;

    @BeforeEach
    void setUp() {
        platform = new Platform(test_id,test_name,test_activity);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getId() {
        assertEquals(test_id, platform.getId());
    }

    @Test
    void setId() {
        assertEquals(test_id, platform.getId());
        platform.setId(test_id+1);
        assertEquals(test_id+1, platform.getId());
    }

    @Test
    void getName() {
        assertEquals(test_name, platform.getName());
    }

    @Test
    void setName() {
        String newName = "New Name";
        assertEquals(test_name, platform.getName());
        platform.setName(newName);
        assertEquals(newName, platform.getName());
    }

    @Test
    void isActive() {
        assertEquals(test_activity, platform.isActive());
    }

    @Test
    void setActive() {
        assertEquals(test_activity, platform.isActive());
        test_activity = true;
        platform.setActive(test_activity);
        assertEquals(test_activity, platform.isActive());
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
                assertTrue(Platform.create(db.getConn(), "New Item"));
                assertFalse(Platform.create(db.getConn(), "New Item"));

                // Test Read
                System.out.println("Reading...");
                assertEquals(test_name, platform.getName());
                platform = Platform.read(db.getConn(), 1);
                assertNotEquals(test_name, platform.getName());

                // Test Update
                System.out.println("Updating...");

                assertTrue(Platform.update(db.getConn(),1, test_name, test_activity));
                platform = Platform.read(db.getConn(), 1);
                assertEquals(test_name, platform.getName());

                // Test ReadList
                System.out.println("Reading List...");
                Platform[] platform_array = Platform.readList(db.getConn(), test_activity);
                assertTrue(platform_array.length >= 2);

                boolean flag = true;
                for(Platform temp: platform_array){
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