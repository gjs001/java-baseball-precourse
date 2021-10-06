package baseball;

import nextstep.utils.Randoms;

public class NumberBaseballService {
    private final int GAME_LENGTH = 3;
    private StringBuilder answer;
    private boolean[] visited;
    private boolean isAllowUnexpectedInput;

    public void setupNewGame(boolean isAllowUnexpectedInput) {
        visited = new boolean[10];
        this.isAllowUnexpectedInput = isAllowUnexpectedInput;
        makeAnswer(GAME_LENGTH);
    }

    public NumberBaseballResult testUserAnswer(String userAnswer) {
        if (answer == null || visited == null) {
            return new NumberBaseballResult(0, 0, false, true);
        }
        if (checkInputError(userAnswer)) {
            return new NumberBaseballResult(0, 0, false, true);
        }
        int strike = countStrike(userAnswer);
        int ball = countBall(userAnswer);
        return new NumberBaseballResult(strike, ball, strike == 3, false);
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

    private boolean checkInputError(String userAnswer) {
        if (userAnswer.length() != GAME_LENGTH) {
            return true;
        }
        if (!isAllowUnexpectedInput && hasWrongChar(userAnswer)) {
            return true;
        }
        return !isAllowUnexpectedInput && hasSameNumber(userAnswer);
    }

    private boolean hasWrongChar(String userAnswer) {
        boolean result = false;
        for (int ch = 0; ch < '1'; ch++) {
            result |= checkWrongChar(userAnswer, ch);
        }
        for (int ch = '9' + 1; ch < 256; ch++) {
            result |= checkWrongChar(userAnswer, ch);
        }
        return result;
    }

    private boolean checkWrongChar(String userAnswer, int ch) {
        int index = userAnswer.indexOf(ch);
        return index != -1;
    }

    private boolean hasSameNumber(String userAnswer) {
        boolean result = false;
        for (int ch = '1'; ch <= '9'; ch++) {
            result |= countSameNumber(userAnswer, ch);
        }
        return result;
    }

    private boolean countSameNumber(String userAnswer, int ch) {
        boolean result = false;
        int[] countMap = new int[10];
        for (int index = 0; index < GAME_LENGTH; index++) {
            countMap[userAnswer.charAt(index) - '0']++;
        }

        for (int count : countMap) {
            result |= checkOverTwo(count);
        }
        return result;
    }

    private boolean checkOverTwo(int count) {
        return count > 1;
    }
}
