package Servlets.Rating;

import Classes.ResponsePackage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReadTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void readRating() {
        ResponsePackage rp = Servlets.Rating.Read.readRating(1);
        assertEquals(ResponsePackage.Status.OK.getCode(), rp.getResponse());
        rp = Read.readRating(0);
        assertEquals(ResponsePackage.Status.NOT_FOUND.getCode(), rp.getResponse());
    }
}