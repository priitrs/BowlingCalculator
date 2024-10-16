import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BowlingCalculatorTest {

    BowlingCalculator calculator = new BowlingCalculator();

    @Test
    public void calculate_standard() {
        calculator.addRoll(3);
        calculator.addRoll(4);
        calculator.addRoll(1);
        calculator.addRoll(7);

        assertEquals(15, calculator.calculate());
    }
    @Test
    public void calculate_spare() {
        calculator.addRoll(6);
        calculator.addRoll(4);
        calculator.addRoll(1);
        calculator.addRoll(3);

        assertEquals(15, calculator.calculate());
    }

    @Test
    public void calculate_strike() {
        calculator.addRoll(10);
        calculator.addRoll(1);
        calculator.addRoll(1);

        assertEquals(14, calculator.calculate());
    }

    @Test
    public void calculate_strikesWithBonusRolls() {
        for (int i = 0; i < 12; i++) {
            calculator.addRoll(10);
        }

        assertEquals(300, calculator.calculate());
    }

    @Test
    public void calculate_strikesWithoutBonusRolls() {
        for (int i = 0; i < 10; i++) {
            calculator.addRoll(10);
        }

        assertEquals(270, calculator.calculate());
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
    public void getBonusIfFrameIsStrike() {
        calculator.addRoll(10);
        calculator.addRoll(2);
        calculator.addRoll(3);

        assertEquals(5, calculator.getBonusIfFrameIsStrike(0));
    }

    @Test
    public void getBonusIfStrike_firstBonusRollIsFrameIsStrike() {
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);

        assertEquals(20, calculator.getBonusIfFrameIsStrike(0));
    }
}