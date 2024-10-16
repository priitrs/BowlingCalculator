import java.util.ArrayList;
import java.util.List;

public class BowlingCalculator {

    private final List<Frame> frames = new ArrayList<>();
    private final int[] scores = new int[10];

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
            scores[i] = result;
        }
        return result;
    }

    public void printResults() {
        System.out.println(getDisplayableFrameResults());
        System.out.println(getDisplayableScores());
    }

    String getDisplayableFrameResults() {
        return frames.stream()
                .reduce(new StringBuilder("|"), StringBuilder::append, StringBuilder::append)
                .toString();
    }

    String getDisplayableScores() {
        StringBuilder result = new StringBuilder("|");
        for (int i = 0; i < frames.size(); i++) {
            int scoreAfterFrame = scores[i];
            if (scoreAfterFrame < 10) result.append("  %s  |".formatted(scoreAfterFrame));
            else if (scoreAfterFrame > 99) result.append(" %s |".formatted(scoreAfterFrame));
            else result.append("  %s |".formatted(scoreAfterFrame));
        }
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
            if (nextFrame.isStrike() && isSecondNextFrame) {
                Frame secondNextFrame = frames.get(i + 2);
                bonusFromStrike += secondNextFrame.getRoll1();
            }
        }
        return bonusFromStrike;
    }

    private boolean isFirstRollInFrame() {
        return frames.isEmpty() || frames.getLast().isStrike() || frames.getLast().isFramePassed();
    }

    private int getSingleFrameScore(int i) {
        return frames.get(i).getFrameSum() + getBonusIfFrameIsSpare(i) + getBonusIfFrameIsStrike(i);
    }
}
