package baseball;

public class GameController {

    private final int GAME_UNKNOWN = 0;
    private final int GAME_END = 1;
    private final int GAME_RETRY = 2;

    private GameView gameView;
    private NumberBaseballService numberBaseballService;

    public void setup() {
        gameView = new CliGameView();
        numberBaseballService = new NumberBaseballService();
    }

    public void start() {
        if (gameView == null || numberBaseballService == null) {
            setup();
        }

        int gameState = GAME_UNKNOWN;
        while (gameState != GAME_END) {
            gameState = playGame();
        }
    }

    private int playGame() {
        numberBaseballService.setupNewGame();
        boolean isCorrectAnswer = false;
        while (!isCorrectAnswer) {
            isCorrectAnswer = doUserInteraction();
        }

        int gameState = gameView.showGameEndMessage();
        return gameState;
    }

    private boolean doUserInteraction() {
        String userAnswer = gameView.askQuestion();
        NumberBaseballResult result = numberBaseballService.testUserAnswer(userAnswer);
        gameView.showAnswer(result);
        return result.isCorrect();
    }
}
