package Servlets.Rating;

import Classes.ResponsePackage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadListTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readListRating() {
        ResponsePackage rp = ReadList.readListRating(true);
        assertEquals(ResponsePackage.Status.OK.getCode(), rp.getResponse());
    }
}