public class Frame {
    private int roll1;
    private int roll2 = -1;

    public Frame(int roll1) {
        this.roll1 = roll1;
        if (roll1 == 10) {
            this.roll2 = 0;
        }
    }

    public Frame(int roll1, int roll2) {
        this.roll1 = roll1;
        this.roll2 = roll2;
    }

    public boolean isStrike() {
        return roll1 == 10 || roll2 == 10;
    }

    public boolean isStrikeInFirstRoll() {
        return roll1 == 10;
    }

    public boolean isSpare() {
        return getFrameSum() == 10 && !isStrike();
    }

    public int getFrameSum() {
        return roll1 + roll2;
    }

    public int getRoll1() {
        return roll1;
    }

    public int getRoll2() {
        return roll2;
    }

    public void setRoll2(int roll2) {
        this.roll2 = roll2;
    }
}
