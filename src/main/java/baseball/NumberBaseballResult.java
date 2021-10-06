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

    public void setStrike(int strike) {
        this.strike = strike;
    }

    public void setBall(int ball) {
        this.ball = ball;
    }

    public void setCorrect(boolean isCorrect) {
        this.correct = isCorrect;
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

