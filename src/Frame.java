public class Frame {
    private int roll1;
    private int roll2 = -1;

    public Frame() {
    }

    public Frame(int roll1) {
        this.roll1 = roll1;
        if (isStrike()) {
            this.roll2 = 0;
        }
    }

    public Frame(int roll1, int roll2) {
        this.roll1 = roll1;
        this.roll2 = roll2;
    }

    public boolean isStrike() {
        return getRoll1() == 10;
    }

    public boolean isSpare() {
        return getRoll1() + getRoll2() == 10 && !isStrike();
    }

    public int getFrameSum() {
        return getRoll1() + getRoll2();
    }

    public boolean isFramePassed() {
        return roll2 >= 0;
    }

    public int getRoll1() {
        return roll1;
    }

    public int getRoll2() {
        return roll2;
    }

    public void setRoll1(int roll1) {
        this.roll1 = roll1;
    }

    public void setRoll2(int roll2) {
        this.roll2 = roll2;
    }

    public void addRoll(int roll) {
        this.roll2 = roll;
    }

    @Override
    public String toString() {
        if (isStrike()) return "X -";
        if (isSpare()) return "%s /".formatted(getRoll1());
        return "%s %s".formatted(getRoll1(), getRoll2());
    }
}
