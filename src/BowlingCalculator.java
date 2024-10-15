import java.util.ArrayList;
import java.util.List;

public class BowlingCalculator {

    private final List<Frame> frames = new ArrayList<>();
    private boolean isFirstRollInFrame = true;
    private int currentFrameIndex = 0;

    public void addRoll(int pins) {
        if (isFirstRollInFrame) {
            Frame currentFrame = new Frame(pins);
            frames.add(currentFrame);
        } else {
            frames.get(currentFrameIndex).setRoll2(pins);
        }
        updateParameters();
    }

    private void updateParameters() {
        if (!isFirstRollInFrame) {
            currentFrameIndex++;
            isFirstRollInFrame = true;
        } else if (frames.get(currentFrameIndex).isStrike()) {
            currentFrameIndex++;
        } else {
            isFirstRollInFrame = false;
        }
    }

    public int calculate() {
        int result = 0;
        for (int i = 0; i < frames.size() && i <= 9; i++) {
            Frame currentFrame = frames.get(i);
            result += currentFrame.getFrameSum();

            boolean isNextFrame = i + 1 < frames.size();
            if (currentFrame.isSpare() && isNextFrame) {
                Frame nextFrame = frames.get(i + 1);
                result += nextFrame.getRoll1();
            }
            if (currentFrame.isStrike() && isNextFrame) {
                Frame nextFrame = frames.get(i + 1);
                result += nextFrame.getFrameSum();

                boolean isSecondNextFrame = i + 2 < frames.size();
                if (nextFrame.isStrikeInFirstRoll() && isSecondNextFrame) {
                    Frame secondNextFrame = frames.get(i + 2);
                    result += secondNextFrame.getRoll1();
                }
            }
        }
        return result;
    }
}
