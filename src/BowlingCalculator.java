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
            if (currentFrame.isStrike()) {
                currentFrameIndex++;
            } else {
                isFirstRollInFrame = false;
            }
        } else {
            frames.get(currentFrameIndex).setRoll2(pins);
            currentFrameIndex++;
            isFirstRollInFrame = true;
        }

    }

    public int calculate() {
        int result = 0;
        for (int i = 0; i < frames.size(); i++) {
            if (i <= 9) {
                Frame currentFrame = frames.get(i);
                result += currentFrame.getFrameSum();

                if (currentFrame.isSpare()) {
                    result += frames.get(i + 1).getRoll1();
                }
                if (currentFrame.isStrike()) {
                    Frame nextFrame = frames.get(i + 1);
                    result += nextFrame.getFrameSum();
                    if (nextFrame.getRoll1() == 10) {
                        result += frames.get(i + 2).getRoll1();
                    }
                }
            }
        }
        return result;
    }
}
