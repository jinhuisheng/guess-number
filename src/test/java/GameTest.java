import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author huisheng.jin
 * @date 2020/1/5.
 */
class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        AnswerGenerator answerGenerator = Mockito.mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn(new Answer("1 2 3 4"));
        game = new Game(answerGenerator);

    }

    @ParameterizedTest
    @CsvSource({
            "5 6 7 8,0A0B",
            "1 2 3 4,4A0B",
            "4 3 2 1,0A4B",
            "1 5 6 7,1A0B",
            "1 5 6 7,1A0B",
            "2 4 7 8,0A2B",
            "0 3 2 4,1A2B",
    })
    void should_return_expected_when_input_guess_answer(String guessAnswer, String expected) {
        assertThat(game.guess(new Answer(guessAnswer))).isEqualTo(expected);
    }

    @Test
    void should_throw_exception_when_input_unValid() {
       Assertions.assertThrows(IllegalArgumentException.class, () -> game.guess(new Answer("1 2")), "输入参数不合法");
    }

    @Test
    void should_return_history_right() {
        game.guess(new Answer("0 3 2 4"));
        List<GuessLog> guessHistory = game.getHistory();
        GuessLog guessLog = guessHistory.get(0);
        assertThat(guessLog.getGuessAnswer()).isEqualTo("0 3 2 4");
        assertThat(guessLog.getGuessResult()).isEqualTo("1A2B");
    }

    @Test
    void should_win_when_guess_right_one_times() {
        game.guess(new Answer("1 2 3 4"));

        assertThat(game.getGameResult()).isEqualTo("win");
    }

    @Test
    void should_lose_when_guess_wrong_six_times() {
        game.guess(new Answer("3 2 6 4"));
        game.guess(new Answer("3 2 6 4"));
        game.guess(new Answer("3 2 6 4"));
        game.guess(new Answer("3 2 6 4"));
        game.guess(new Answer("3 2 6 4"));
        game.guess(new Answer("3 2 6 4"));

        assertThat(game.getGameResult()).isEqualTo("lose");
    }

    @Test
    void game_is_over_when_guess_beyond_six_times() {
        game.guess(new Answer("3 2 6 4"));
        game.guess(new Answer("3 2 6 4"));
        game.guess(new Answer("3 2 6 4"));
        game.guess(new Answer("3 2 6 4"));
        game.guess(new Answer("3 2 6 4"));
        game.guess(new Answer("3 2 6 4"));

        Assertions.assertThrows(BeyondGameGuessTimesException.class, () -> game.guess(new Answer("3 2 5 4")), "超过游戏次数限制");
    }

}
