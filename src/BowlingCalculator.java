import java.util.ArrayList;
import java.util.List;

public class BowlingCalculator {

    private final List<Frame> frames = new ArrayList<>();

    public void addRoll(int pins) {
        if (isFirstRollInFrame()) {
            Frame currentFrame = new Frame(pins);
            frames.add(currentFrame);
        } else {
            frames.getLast().setRoll2(pins);
        }
    }

    public int calculateScore() {
        int result = 0;
        for (int i = 0; i < frames.size() && i <= 9; i++) {
            result += getSingleFrameScore(i);
        }
        return result;
    }

    public void print() {
        System.out.println(getDisplayableResults());
        System.out.println(getDisplayableScores());
    }

    String getDisplayableResults() {
        StringBuilder result = new StringBuilder("|");
        for (Frame frame : frames) {
            if (frame.isStrike()) {
                result.append(frame.isStrikeInFirstRoll() ? " X - |" : " 0 X |");
            } else if (frame.isSpare()) {
                result.append(" %s / |".formatted(frame.getRoll1()));
            } else {
                result.append(" %s %s |".formatted(frame.getRoll1(), frame.getRoll2()));
            }
        }
        return result.toString();
    }

    String getDisplayableScores() {
        StringBuilder result = new StringBuilder("|");
        for (int i = 0; i < frames.size(); i++) {
            int singleFrameScore = getSingleFrameScore(i);
            if (singleFrameScore < 10) result.append("  %s  |".formatted(singleFrameScore));
            else if (singleFrameScore > 99) result.append(" %s |".formatted(singleFrameScore));
            else result.append("  %s |".formatted(singleFrameScore));
        }
        result.append(" TOTAL: %s".formatted(calculateScore()));
        return result.toString();
    }

    int getBonusIfFrameIsSpare(int i) {
        boolean isNextFrame = i + 1 < frames.size();
        if (frames.get(i).isSpare() && isNextFrame) {
            Frame nextFrame = frames.get(i + 1);
            return nextFrame.getRoll1();
        }
        return 0;
    }

    int getBonusIfFrameIsStrike(int i) {
        boolean isNextFrame = i + 1 < frames.size();
        int bonusFromStrike = 0;
        if (frames.get(i).isStrike() && isNextFrame) {
            Frame nextFrame = frames.get(i + 1);
            bonusFromStrike = nextFrame.getFrameSum();

            boolean isSecondNextFrame = i + 2 < frames.size();
            if (nextFrame.isStrikeInFirstRoll() && isSecondNextFrame) {
                Frame secondNextFrame = frames.get(i + 2);
                bonusFromStrike += secondNextFrame.getRoll1();
            }
        }        return bonusFromStrike;
    }

    private boolean isFirstRollInFrame() {
        return frames.isEmpty() || frames.getLast().isStrike() || frames.getLast().isFramePassed();
    }

    private int getSingleFrameScore(int i) {
        return frames.get(i).getFrameSum() + getBonusIfFrameIsSpare(i) + getBonusIfFrameIsStrike(i);
    }
}
