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
        StringBuilder message = new StringBuilder();
        int strike = numberBaseballResult.getStrike();
        int ball = numberBaseballResult.getBall() - strike;
        if (numberBaseballResult.isError()) {
            showWrongInputMessage(numberBaseballResult.isError());
            return;
        }
        makeMessage(message, strike, ball);
        System.out.println(message);
    }

    @Override
    public int showGameEndMessage() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 끝");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        String response = Console.readLine();
        int result = 0;
        try {
            result = Integer.parseInt(response);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void showWrongInputMessage(boolean isError) {
        if (isError) {
           System.out.println("[ERROR] 잘못된 입력입니다.");
        }
    }

    private void makeMessage(StringBuilder message, int strike, int ball) {
        if (strike > 0) {
            message.append(strike + "스트라이크 ");
        }
        if (ball > 0) {
            message.append(ball + "볼 ");
        }
        if (strike == 0 && ball == 0) {
            message.append("낫싱");
        }
    }
}
