public class LastFrame extends Frame {
    private int roll3 = -1;

    public LastFrame(int roll1) {
        super();
        setRoll1(roll1);
    }

    @Override
    public void addRoll(int roll) {
        if (getRoll2() < 0) {
            setRoll2(roll);
            if (isFramePassed()) {
                setRoll3(0);
            }
        } else {
            setRoll3(roll);
        }
    }

    @Override
    public int getFrameSum() {
        return super.getFrameSum() + getRoll3();
    }

    @Override
    public boolean isFramePassed() {
        return (!isStrike() && !isSpare() && getRoll2() > -1) || (getRoll3() > -1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isStrike() ? "X" : getRoll1());
        sb.append(isSpare() ? "/" : getRoll2() == 10 ? "X" : getRoll2());
        sb.append(isBonusRoll() ? getRoll3() == 10 ? "X" : getRoll3() : "");
        return String.format("%-3s", sb);
    }

    public int getRoll3() {
        return roll3;
    }

    private boolean isBonusRoll() {
        return isStrike() || isSpare();
    }

    private void setRoll3(int roll3) {
        this.roll3 = roll3;
    }
}
