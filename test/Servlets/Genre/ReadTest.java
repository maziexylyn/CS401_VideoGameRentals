package Servlets.Genre;

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
    void readGenre() {
        ResponsePackage rp = Servlets.Genre.Read.readGenre(1);
        assertEquals(ResponsePackage.Status.OK.getCode(), rp.getResponse());
        rp = Read.readGenre(0);
        assertEquals(ResponsePackage.Status.NOT_FOUND.getCode(), rp.getResponse());
    }
}