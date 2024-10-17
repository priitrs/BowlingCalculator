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

        assertEquals(15, calculator.calculateScore());
    }

    @Test
    public void calculate_Score_spare() {
        calculator.addRoll(6);
        calculator.addRoll(4);
        calculator.addRoll(1);
        calculator.addRoll(3);

        assertEquals(15, calculator.calculateScore());
    }

    @Test
    public void calculate_Score_strike() {
        calculator.addRoll(10);
        calculator.addRoll(1);
        calculator.addRoll(1);

        assertEquals(14, calculator.calculateScore());
    }

    @Test
    public void calculate_Score_strikesWithBonusRolls() {
        for (int i = 0; i < 12; i++) {
            calculator.addRoll(10);
        }

        assertEquals(300, calculator.calculateScore());
    }

    @Test
    public void calculate_Score_strikesWithoutBonusRolls() {
        for (int i = 0; i < 10; i++) {
            calculator.addRoll(10);
        }

        assertEquals(270, calculator.calculateScore());
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
    public void getDisplayableFrameResults() {
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
        calculator.calculateScore();

        String expected = """
                | 3 4 | X - | 3 1 | 3 / | 3 4 | 0 / | X - | X - | 1 2 | X - | X - | X - |
                |  7  |  21 |  25 |  38 |  45 |  65 |  86 |  99 | 102 | 132 |""";

        assertEquals(expected, calculator.getPrintableResults());
    }

    @Test
    public void getDisplayableFrameResults_normalRolls() {
        calculator.addRoll(3);
        calculator.addRoll(4);

        assertEquals("| 3 4 |", calculator.getDisplayableFrameResults());
    }

    @Test
    public void getDisplayableFrameResults_spare() {
        calculator.addRoll(3);
        calculator.addRoll(7);

        assertEquals("| 3 / |", calculator.getDisplayableFrameResults());
    }

    @Test
    public void getDisplayableFrameResults_strikeWithFirstRoll() {
        calculator.addRoll(10);

        assertEquals("| X - |", calculator.getDisplayableFrameResults());
    }

}