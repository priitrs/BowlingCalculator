import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FrameTest {

    @Test
    void isStrike() {
        Frame strikeOnFirstRoll = new Frame(10);
        Frame strikeOnSecondRoll = new Frame(0, 10);

        assertTrue(strikeOnFirstRoll.isStrike());
        assertTrue(strikeOnSecondRoll.isStrike());
    }

    @Test
    void isStrikeInFirstRoll() {
        Frame strikeOnFirstRoll = new Frame(10);
        Frame strikeOnSecondRoll = new Frame(0, 10);

        assertTrue(strikeOnFirstRoll.isStrikeInFirstRoll());
        assertFalse(strikeOnSecondRoll.isStrikeInFirstRoll());
    }

    @Test
    void isSpare() {
        Frame frame = new Frame(3, 7);
        Frame frame2 = new Frame(3, 6);

        assertTrue(frame.isSpare());
        assertFalse(frame2.isSpare());
    }

    @Test
    void getFrameSum() {
        Frame frame = new Frame(3, 7);

        assertEquals(10, frame.getFrameSum());
    }
}