package Classes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponsePackageTest {

    private String test_msg = "Test message";
    private int test_response = 10;
    private String test_data = "Test data";
    private ResponsePackage responsePackage;

    @BeforeEach
    void setUp() {responsePackage = new ResponsePackage();}

    @AfterEach
    void tearDown() {}

    @Test
    void getResponse() {assertEquals(test_response, responsePackage.getResponse());}

    @Test
    void setData() {responsePackage.setData(test_data);}

    @Test
    void setMsgResponse() {}

    @Test
    void setMsg() {responsePackage.setMsg(test_msg);}

    @Test
    void formatData() {}
}