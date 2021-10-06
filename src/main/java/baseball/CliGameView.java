package baseball;

import nextstep.utils.Console;


public class CliGameView implements GameView {

    @Override
    public String askQuestion() {
        String userAnswer;
        System.out.println("숫자를 입력해주세요 : ");
        userAnswer = Console.readLine();
        return userAnswer;
    }

    @Override
    public void showAnswer(NumberBaseballResult numberBaseballResult) {
    }

    @Override
    public int showGameEndMessage() {
        return 0;
    }
}
