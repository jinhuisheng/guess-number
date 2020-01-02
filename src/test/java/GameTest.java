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
 * @date 2019/12/30.
 */
class GameTest {
    private Game game;
    private final Answer answer = new Answer("1 2 3 4");

    @BeforeEach
    void setUp() {
        AnswerGenerator stubAnswerGenerator = Mockito.mock(AnswerGenerator.class);
        when(stubAnswerGenerator.generate()).thenReturn(answer);
        game = new Game(stubAnswerGenerator);
    }

    @ParameterizedTest
    @CsvSource({
            "1 2 3 4,4A0B",
            "4 3 2 1,0A4B",
            "5 6 7 8,0A0B",
            "2 4 7 8,0A2B",
            "0 3 2 4,1A2B",
            "1 5 6 7,1A0B",
    })
    void should_return_result_when_input_answer(String guessAnswer, String expected) {
        String result = game.guess(new Answer(guessAnswer));
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void should_throw_exception_when_input_answer_number_repeat() {
        Assertions.assertThrows(RuntimeException.class, () -> game.guess(new Answer("1 1 2 3")), "格式不正确:输入数字重复");
    }

    @Test
    void should_get_history_right() {
        game.guess(new Answer("1 5 6 7"));

        List<GuessLog> list = game.getHistory();
        assertThat(list.size()).isEqualTo(1);

        GuessLog guessLog = list.get(0);
        assertThat(guessLog.getGuessResult()).isEqualTo("1A0B");
        assertThat(guessLog.getGuessAnswer()).isEqualTo(new Answer("1 5 6 7"));
    }

    @Test
    void should_get_history_right_with_multiple_times() {
        game.guess(new Answer("1 5 6 7"));
        game.guess(new Answer("1 2 3 5"));
        List<GuessLog> list = game.getHistory();
        assertThat(list.size()).isEqualTo(2);

        GuessLog guessLog = list.get(0);
        assertThat(guessLog.getGuessResult()).isEqualTo("1A0B");
        assertThat(guessLog.getGuessAnswer()).isEqualTo(new Answer("1 5 6 7"));

        GuessLog guessLog1 = list.get(1);
        assertThat(guessLog1.getGuessResult()).isEqualTo("3A0B");
        assertThat(guessLog1.getGuessAnswer()).isEqualTo(new Answer("1 2 3 5"));
    }

    @Test
    void should_return_win_when_guess_right() {
        game.guess(new Answer("1 2 3 4"));
        assertThat(game.getGameResult()).isEqualTo("win");
    }

    @Test
    void should_play_failure_when_guess_wrong_with_six_times() {
        guessSixTimes();

        assertThat(game.getGameResult()).isEqualTo("failure");
    }

    @Test
    void should_guess_over_when_guess_beyond_6_times_wrong() {
        guessSixTimes();

        Assertions.assertThrows(RuntimeException.class, () -> game.guess(new Answer("1 3 2 4"))
                , "超过6次，不能再猜测");
    }

    private void guessSixTimes() {
        game.guess(new Answer("1 3 2 4"));
        game.guess(new Answer("1 3 2 4"));
        game.guess(new Answer("1 3 2 4"));
        game.guess(new Answer("1 3 2 4"));
        game.guess(new Answer("1 3 2 4"));
        game.guess(new Answer("1 3 2 4"));
    }
}
