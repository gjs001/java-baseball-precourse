package baseball;

public interface GameView {
    default String askQuestion() {
        return "";
    }

    default void showAnswer(NumberBaseballResult numberBasballResult) {
    }

    default int showGameEndMessage() {
        return 0;
    }
}
