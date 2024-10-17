import java.util.ArrayList;
import java.util.List;

public class BowlingCalculator {

    private final List<Frame> frames = new ArrayList<>();
    private final int[] scores = new int[10];

    public int[] getScores() {
        return scores;
    }

    public void addRoll(int pins) {
        if (isFirstRollInFrame()) {
            Frame currentFrame = new Frame(pins);
            frames.add(currentFrame);
        } else {
            frames.getLast().setRoll2(pins);
        }
    }

    public void calculateScore() {
        for (int i = 0; i < frames.size() && i <= 9; i++) {
            if (i == 0) scores[i] = getSingleFrameScore(i);
            else scores[i] = scores[i - 1] + getSingleFrameScore(i);
        }
    }

    public String getPrintableResults() {
        return ("""
                %s
                %s""".formatted(getDisplayableFrameResults(), getDisplayableScores()));
    }

    int getBonusIfFrameIsSpare(int i) {
        if (frames.get(i).isSpare() && isFramePresent(i, 1)) {
            return frames.get(i + 1).getRoll1();
        }
        return 0;
    }

    int getBonusIfFrameIsStrike(int frameIndex) {
        if (frames.get(frameIndex).isStrike() && isFramePresent(frameIndex, 1)) {
            Frame nextFrame = frames.get(frameIndex + 1);
            return (nextFrame.isStrike() && isFramePresent(frameIndex, 2))
                    ? nextFrame.getRoll1() + frames.get(frameIndex + 2).getRoll1()
                    : nextFrame.getFrameSum();

        }
        return 0;
    }

    private String getDisplayableFrameResults() {
        return "| " + String.join(" | ", frames.stream().map(Frame::toString).toList()) + " |";
    }

    private String getDisplayableScores() {
        StringBuilder result = new StringBuilder("|");
        for (int i = 0; i < frames.size() && i <= 9; i++) {
            int totalScoreAfterFrame = scores[i];
            if (totalScoreAfterFrame < 10) result.append("  %s  |".formatted(totalScoreAfterFrame));
            else if (totalScoreAfterFrame > 99) result.append(" %s |".formatted(totalScoreAfterFrame));
            else result.append("  %s |".formatted(totalScoreAfterFrame));
        }
        return result.toString();
    }

    private boolean isFramePresent(int frameIndex, int step) {
        return frameIndex + step < frames.size();
    }

    private boolean isFirstRollInFrame() {
        return frames.isEmpty() || frames.getLast().isStrike() || frames.getLast().isFramePassed();
    }

    private int getSingleFrameScore(int i) {
        return frames.get(i).getFrameSum() + getBonusIfFrameIsSpare(i) + getBonusIfFrameIsStrike(i);
    }
}
