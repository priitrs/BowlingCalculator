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
    public void calculate_strike300() {
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);
        calculator.addRoll(10);

        assertEquals(300, calculator.calculate());
    }

}