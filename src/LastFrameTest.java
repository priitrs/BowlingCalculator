import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LastFrameTest {

    @Test
    void addRoll() {
        LastFrame lastFrame = new LastFrame(1);
        lastFrame.addRoll(2);

        assertEquals(new LastFrame(1, 2, 0), lastFrame);

        LastFrame lastFrame2 = new LastFrame(1);
        lastFrame2.addRoll(9);
        lastFrame2.addRoll(8);

        assertEquals(new LastFrame(1, 9, 8), lastFrame2);
    }

    @Test
    void getFrameSum() {
        assertEquals(18, new LastFrame(1, 9, 8).getFrameSum());
    }

    @Test
    void isFramePassed() {
        assertTrue(new LastFrame(1, 2, -1).isFramePassed());
        assertTrue(new LastFrame(1, 9, 8).isFramePassed());
        assertTrue(new LastFrame(10, 9, 8).isFramePassed());
        assertFalse(new LastFrame(1, -1, -1).isFramePassed());
        assertFalse(new LastFrame(10, -1, -1).isFramePassed());
        assertFalse(new LastFrame(10, 1, -1).isFramePassed());
        assertFalse(new LastFrame(1, 9, -1).isFramePassed());
    }

    @Test
    void testToString() {
        assertEquals("1/8", new LastFrame(1, 9, 8).toString());
        assertEquals("X98", new LastFrame(10, 9, 8).toString());
        assertEquals("12 ", new LastFrame(1, 2, -1).toString());
    }
}