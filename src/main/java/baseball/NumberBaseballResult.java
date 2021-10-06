package baseball;

public class NumberBaseballResult {
    private int strike;
    private int ball;
    private boolean correct;
    private boolean error;

    public NumberBaseballResult(int strike, int ball, boolean correct, boolean error) {
        this.strike = strike;
        this.ball = ball;
        this.correct = correct;
        this.error = error;
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

    public boolean isError() {
        return error;
    }
}

