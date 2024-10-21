import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BowlingCalculatorTest {

    BowlingCalculator calculator = new BowlingCalculator();

    @Test
    public void calculate_Score_standard() {
        calculator.addRoll(3);
        calculator.addRoll(4);
        calculator.addRoll(1);
        calculator.addRoll(7);

        String expected = """
                | 3 4 | 1 7 |
                | 7   | 15  |""";

        assertEquals(expected, calculator.getResults());
    }

    @Test
    public void calculate_Score_spare() {
        calculator.addRoll(6);
        calculator.addRoll(4);
        calculator.addRoll(1);
        calculator.addRoll(3);

        String expected = """
                | 6 / | 1 3 |
                | 11  | 15  |""";

        assertEquals(expected, calculator.getResults());
    }

    @Test
    public void calculate_Score_strike() {
        calculator.addRoll(10);
        calculator.addRoll(1);
        calculator.addRoll(1);

        String expected = """
                | X - | 1 1 |
                | 12  | 14  |""";

        assertEquals(expected, calculator.getResults());
    }

    @Test
    public void calculate_Score_strikesWithBonusRolls() {
        for (int i = 0; i < 12; i++) {
            calculator.addRoll(10);
        }


        String expected = """
                | X - | X - | X - | X - | X - | X - | X - | X - | X - | XXX |
                | 30  | 60  | 90  | 120 | 150 | 180 | 210 | 240 | 270 | 300 |""";

        assertEquals(expected, calculator.getResults());
    }

    @Test
    public void getBonusIfFrameIsSpare() {
        calculator.addRoll(6);
        calculator.addRoll(4);
        calculator.addRoll(1);
        calculator.addRoll(3);

        assertEquals(1, calculator.getBonusIfFrameIsSpare(0));
    }

    @Test
    public void getBonusIfFrameIsSpare_bonusRollIsMissing() {
        calculator.addRoll(6);
        calculator.addRoll(4);

        assertEquals(0, calculator.getBonusIfFrameIsSpare(0));
    }

    @Test
    public void getBonusIfFrameIsStrike() {
        calculator.addRoll(10);
        calculator.addRoll(2);
        calculator.addRoll(3);
        calculator.addRoll(5);

        assertEquals(5, calculator.getBonusIfFrameIsStrike(0));
    }

    @Test
    public void getBonusIfFrameIsStrike_nextFrameIsStrike() {
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);

        assertEquals(20, calculator.getBonusIfFrameIsStrike(0));
    }

    @Test
    public void getBonusIfFrameIsStrike_nextFrameIsStrikeButSecondNextFrameIsMissing() {
        calculator.addRoll(10);
        calculator.addRoll(10);

        assertEquals(10, calculator.getBonusIfFrameIsStrike(0));
    }

    @Test
    public void getFrameResults_strikeInEnd() {
        calculator.addRoll(3);
        calculator.addRoll(4);
        calculator.addRoll(10);
        calculator.addRoll(3);
        calculator.addRoll(1);
        calculator.addRoll(3);
        calculator.addRoll(7);
        calculator.addRoll(3);
        calculator.addRoll(4);
        calculator.addRoll(0);
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(1);
        calculator.addRoll(2);
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);

        String expected = """
                | 3 4 | X - | 3 1 | 3 / | 3 4 | 0 / | X - | X - | 1 2 | XXX |
                | 7   | 21  | 25  | 38  | 45  | 65  | 86  | 99  | 102 | 132 |""";

        assertEquals(expected, calculator.getResults());
    }

    @Test
    public void getFrameResults_spareInEnd() {
        for (int i = 0; i < 18; i++) {
            calculator.addRoll(0);
        }
        calculator.addRoll(6);
        calculator.addRoll(4);
        calculator.addRoll(7);

        String expected = """
                | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 6/7 |
                | 0   | 0   | 0   | 0   | 0   | 0   | 0   | 0   | 0   | 17  |""";

        assertEquals(expected, calculator.getResults());
    }

    @Test
    public void getFrameResults_normalEnd() {
        for (int i = 0; i < 18; i++) {
            calculator.addRoll(0);
        }
        calculator.addRoll(6);
        calculator.addRoll(2);

        String expected = """
                | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 0 0 | 62  |
                | 0   | 0   | 0   | 0   | 0   | 0   | 0   | 0   | 0   | 8   |""";

        assertEquals(expected, calculator.getResults());
    }
}