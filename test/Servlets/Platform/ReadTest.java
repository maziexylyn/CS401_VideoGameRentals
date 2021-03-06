package Servlets.Platform;

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
    void readPlatform() {
        ResponsePackage rp = Read.readPlatform(1);
        assertEquals(ResponsePackage.Status.OK.getCode(), rp.getResponse());
        rp = Read.readPlatform(0);
        assertEquals(ResponsePackage.Status.NOT_FOUND.getCode(), rp.getResponse());
    }
}