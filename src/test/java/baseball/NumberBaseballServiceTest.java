package baseball;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberBaseballServiceTest {

    private NumberBaseballService numberBaseballService;

    @BeforeEach
    void setUp() {
        numberBaseballService = new NumberBaseballService();
    }

    @Test
    void setupNewGame_AfterSetup() {
        numberBaseballService.setupNewGame();
        assertThat(numberBaseballService)
                .as("setup operation fail")
                .hasNoNullFieldsOrProperties();
    }

    @Test
    void setupNewGame_BeforeSetup() {
        assertThat(numberBaseballService)
                .as("setup operation already did, visited")
                .extracting("visited")
                .isNull();

        assertThat(numberBaseballService)
                .as("setup operation already did, answer")
                .extracting("answer")
                .isNull();
    }

    @Test
    void testUserAnswer_UnexpectedNumberUserAnswer() {
        String userAnswer = "0#!";
        numberBaseballService.setupNewGame();
        NumberBaseballResult result = numberBaseballService.testUserAnswer(userAnswer);
        assertThat(result.getStrike())
                .as("check strike, %d", result.getStrike())
                .isEqualTo(0);

        assertThat(result.getBall())
                .as("check ball, %d", result.getBall())
                .isEqualTo(0);

        assertThat(result.isCorrect())
                .as("check correct, %b", result.isCorrect())
                .isEqualTo(false);
    }

    @Test
    void testUserAnswer_NormalOperation() {
        String userAnswer1 = "123";
        String userAnswer2 = "456";
        String userAnswer3 = "789";
        numberBaseballService.setupNewGame();

        NumberBaseballResult result1 = numberBaseballService.testUserAnswer(userAnswer1);
        NumberBaseballResult result2 = numberBaseballService.testUserAnswer(userAnswer2);
        NumberBaseballResult result3 = numberBaseballService.testUserAnswer(userAnswer3);

        int totalBall = result1.getBall() + result2.getBall() + result3.getBall();
        assertThat(totalBall).as("totalBall is not 3").isEqualTo(3);
    }

    @Test
    void testUserAnswer_CanFindAnswer() {
        StringBuilder userAnswer = new StringBuilder("000");
        numberBaseballService.setupNewGame();
        for (int i = 0; i < 3; i++) {
            findCorrectAnswerAtIndex(userAnswer, i);
        }
        NumberBaseballResult result = numberBaseballService.testUserAnswer(userAnswer.toString());
        assertThat(result.isCorrect())
                .as("result is not answer")
                .isEqualTo(true);

        assertThat(result.getStrike())
                .as("strike is not 3")
                .isEqualTo(3);

        assertThat(result.getBall())
                .as("ball is not 3")
                .isEqualTo(3);
    }

    private void findCorrectAnswerAtIndex(StringBuilder userAnswer, int index) {
        boolean isFind = false;
        for (int number = 1; number < 10 && !isFind; number++) {
            userAnswer.setCharAt(index, (char) (number + '0'));
            isFind = checkNumberIsNewStrike(userAnswer.toString(), index);
        }
    }

    private boolean checkNumberIsNewStrike(String userAnswer, int index) {
        if (numberBaseballService.testUserAnswer(userAnswer.toString()).getStrike() == index + 1) {
            return true;
        }
        return false;
    }

    @AfterEach
    void tearDown() {
    }

}