import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    void isStrike() {
        assertTrue(new Frame(10).isStrike());
        assertFalse(new Frame(0, 10).isStrike());
    }

    @Test
    void isSpare() {
        assertTrue(new Frame(3, 7).isSpare());
        assertFalse(new Frame(3, 6).isSpare());
    }

    @Test
    void getFrameSum() {
        assertEquals(10, new Frame(3, 7).getFrameSum());
    }

    @Test
    void tooString() {
        assertEquals("3 5", new Frame(3, 5).toString());
    }

    @Test
    void spareToString() {
        assertEquals("3 /", new Frame(3, 7).toString());
    }

    @Test
    void strikeToString() {
        assertEquals("X -", new Frame(10).toString());
    }
}