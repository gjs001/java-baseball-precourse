package baseball;

import nextstep.utils.Randoms;

public class NumberBaseballService {
    private final int GAME_LENGTH = 3;
    private StringBuilder answer;
    private boolean[] visited;

    public void setupNewGame() {
        visited = new boolean[10];
        makeAnswer(GAME_LENGTH);
    }

    public NumberBaseballResult testUserAnswer(String userAnswer) {
        if (answer == null || visited == null) {
            setupNewGame();
        }
        if (userAnswer.length() != GAME_LENGTH) {
            return new NumberBaseballResult(0, 0, false);
        }
        int strike = countStrike(userAnswer);
        int ball = countBall(userAnswer);
        return new NumberBaseballResult(strike, ball, strike == 3);
    }

    private void makeAnswer(int length) {
        answer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int newNumber = getNewNumber();
            answer.append(newNumber);
        }
    }

    private int getNewNumber() {
        int newNumber = Randoms.pickNumberInRange(1, 9);
        while (checkDuplicateNumber(newNumber)) {
            newNumber = Randoms.pickNumberInRange(1, 9);
        }
        visited[newNumber] = true;
        return newNumber;
    }

    private boolean checkDuplicateNumber(int newNumber) {
        return visited[newNumber];
    }

    private int countStrike(String userAnswer) {
        int strike = 0;
        for (int index = 0; index < GAME_LENGTH; index++) {
            strike += getAdditionalStrikeIfEqualDigit(userAnswer, index);
        }
        return strike;
    }

    private int getAdditionalStrikeIfEqualDigit(String userAnswer, int index) {
        if (userAnswer.charAt(index) == answer.charAt(index)) {
            return 1;
        }
        return 0;
    }

    private int countBall(String userAnswer) {
        int ball = 0;
        for (int index = 0; index < GAME_LENGTH; index++) {
            int digit = userAnswer.charAt(index) - '0';
            ball += getAdditionalBallIfNumberIsInUserAnswer(digit);
        }
        return ball;
    }

    private int getAdditionalBallIfNumberIsInUserAnswer(int digit) {
        if (digit <= 0 || digit >= 10) {
            return 0;
        }
        if (visited[digit]) {
            return 1;
        }
        return 0;
    }
}
