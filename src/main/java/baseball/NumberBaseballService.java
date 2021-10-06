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
        int strike = countStrike(userAnswer);
        int ball = countBall(userAnswer);
        NumberBaseballResult result = new NumberBaseballResult(strike, ball, strike == 3);
        return result;
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

        return newNumber;
    }

    private boolean checkDuplicateNumber(int newNumber) {
        if (visited[newNumber]) {
            return true;
        }
        return false;
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
            int digit = userAnswer.charAt(index);
            ball += getAdditionalBallIfNumberIsInUserAnswer(digit);
        }
        return ball;
    }

    private int getAdditionalBallIfNumberIsInUserAnswer(int digit) {
        if (visited[digit]) {
            return 1;
        }
        return 0;
    }
}
