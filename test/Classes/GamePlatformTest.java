package Classes;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GamePlatformTest {

    private int test_game_id = 100;
    private int test_platform_id = 100;
    private int test_times_rented = 10;
    private String test_last_rent_date = "2020-04-12";
    private GamePlatform gameplatform;

    @BeforeEach
    void setUp() {gameplatform = new GamePlatform(test_game_id,test_platform_id, test_times_rented, test_last_rent_date);}

    @AfterEach
    void tearDown() {}

    @Test
    void getGame_id() {assertEquals(test_game_id, gameplatform.getGame_id());}

    @Test
    void setGame_id() {assertEquals(test_game_id, gameplatform.getGame_id());
        gameplatform.setGame_id(test_game_id);
        assertEquals(test_game_id, gameplatform.getGame_id());}

    @Test
    void getPlatform_id() {assertEquals(test_platform_id, gameplatform.getPlatform_id());}

    @Test
    void setPlatform_id() {assertEquals(test_platform_id, gameplatform.getPlatform_id());
        gameplatform.setPlatform_id(test_platform_id);
        assertEquals(test_platform_id, gameplatform.getPlatform_id());}

    @Test
    void getTimes_rented() {assertEquals(test_times_rented, gameplatform.getTimes_rented());}

    @Test
    void setTimes_rented() {assertEquals(test_times_rented, gameplatform.getTimes_rented());
        gameplatform.setTimes_rented(test_times_rented);
        assertEquals(test_times_rented, gameplatform.getTimes_rented());}

    @Test
    void getLast_rent_date() {assertEquals(test_last_rent_date, gameplatform.getLast_rent_date());}

    @Test
    void setLast_rent_date() {String newDate = "2020-05-01";
        assertEquals(test_last_rent_date, gameplatform.getLast_rent_date());
        gameplatform.setLast_rent_date(newDate);
        assertEquals(newDate, gameplatform.getLast_rent_date());
    }

}