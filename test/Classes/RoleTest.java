package Classes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleTest {

    private int test_id = 100;
    private String test_name = "Bob";
    private String test_description = "Test Description";
    private Role role;

    @BeforeEach
    void setUp() {role = new Role(test_id, test_name, test_description);}

    @AfterEach
    void tearDown() {}

    @Test
    void getId() {assertEquals(test_id, role.getId());}

    @Test
    void setId() {assertEquals(test_id, role.getId());
        role.setId(test_id+1);
        assertEquals(test_id+1, role.getId());}

    @Test
    void getName() {assertEquals(test_name, role.getName());}

    @Test
    void setName() {String newName = "New Name";
        assertEquals(test_name, role.getName());
        role.setName(newName);
        assertEquals(newName, role.getName()); }

    @Test
    void getDescription() {assertEquals(test_description, role.getDescription());}

    @Test
    void setDescription() {String newDescription = "New Description";
        assertEquals(test_description, role.getDescription());
        role.setDescription(newDescription);
        assertEquals(newDescription, role.getDescription());}
}