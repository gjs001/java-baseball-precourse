package baseball;

public class NumberBaseballResult {
    private int strike;
    private int ball;
    private boolean correct;

    public NumberBaseballResult(int strike, int ball, boolean correct) {
        this.strike = strike;
        this.ball = ball;
        this.correct = correct;
    }

    public int getStrike() {
        return strike;
    }

    public int getBall() {
        return ball;
    }

    public boolean isCorrect() {
        return correct;
    }
}

