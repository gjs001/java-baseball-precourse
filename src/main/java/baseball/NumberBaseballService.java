package baseball;

import nextstep.utils.Randoms;

public class NumberBaseballService {
    private StringBuilder answer;

    public void setupNewGame() {

    }

    public NumberBaseballResult testUserAnswer(String userAnswer) {
        return null;
    }

    private String makeAnswer(int length) {
        boolean[] visited = new boolean[10];
        answer = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int newNumber = getNewNumber(visited);
            answer.append(newNumber);
        }
        return answer.toString();
    }

    private int getNewNumber(boolean[] visited) {
        int newNumber = Randoms.pickNumberInRange(1, 9);
        while (checkDuplicateNumber(visited, newNumber)) {
            newNumber = Randoms.pickNumberInRange(1, 9);
        }

        return newNumber;
    }

    private boolean checkDuplicateNumber(boolean[] visited, int newNumber) {
        if (visited[newNumber]) {
            return true;
        }
        return false;
    }
}
