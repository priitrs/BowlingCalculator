import java.util.ArrayList;
import java.util.List;

public class BowlingCalculator {

    private final List<Frame> frames = new ArrayList<>();

    public void addRoll(int pins) {
        if (isFirstRollInFrame()) {
            var currentFrame = isLastFrame() ? new LastFrame(pins) : new Frame(pins);
            frames.add(currentFrame);
        } else {
            frames.getLast().addRoll(pins);
        }
    }

    public String getResults() {
        return ("""
                %s
                %s""".formatted(getFrameResults(), getScores()));
    }

    int getBonusIfFrameIsSpare(int index) {
        if (frames.get(index).isSpare() && isFramePresent(index, 1)) {
            return frames.get(index + 1).getRoll1();
        }
        return 0;
    }

    int getBonusIfFrameIsStrike(int index) {
        if (frames.get(index).isStrike() && isFramePresent(index, 1)) {
            Frame nextFrame = frames.get(index + 1);
            return (nextFrame.isStrike() && isFramePresent(index, 2))
                    ? nextFrame.getRoll1() + frames.get(index + 2).getRoll1()
                    : nextFrame.getRoll1() + nextFrame.getRoll2();
        }
        return 0;
    }

    private String getFrameResults() {
        return "| " + String.join(" | ", frames.stream().map(Frame::toString).toList()) + " |";
    }

    private String getScores() {
        StringBuilder result = new StringBuilder("|");
        int totalScore = 0;
        for (int i = 0; i < frames.size() && i <= 9; i++) {
            totalScore = getTotalScoreAfterFrame(i, totalScore);
            result.append(getFormattedFrameScore(totalScore));
        }
        return result.toString();
    }

    private int getTotalScoreAfterFrame(int i, int total) {
        return  total + getCurrentFrameScore(i);
    }

    private String getFormattedFrameScore(int totalScoreAfterFrame) {
        return " " + String.format("%-4s", totalScoreAfterFrame) + "|";
    }

    private boolean isFramePresent(int frameIndex, int step) {
        return frameIndex + step < frames.size();
    }

    private boolean isFirstRollInFrame() {
        return frames.isEmpty() || frames.getLast().isFramePassed();
    }

    private int getCurrentFrameScore(int i) {
        return frames.get(i).getFrameSum() + getBonusIfFrameIsSpare(i) + getBonusIfFrameIsStrike(i);
    }

    private boolean isLastFrame() {
        return frames.size() == 9 && frames.getLast().isFramePassed();
    }
}
