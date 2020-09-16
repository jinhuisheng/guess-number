package guessnumber;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author huisheng.jin
 * @date 2020/9/16.
 */
class GameTest {

    private Game game;

    @BeforeEach
    void setUp() {
        game = new Game(Answer.create("1 2 3 4"));
    }

    @ParameterizedTest
    @CsvSource({
            "1 5 6 7,1A0B",
            "2 4 7 8,0A2B",
            "0 3 2 4,1A2B",
            "5 6 7 8,0A0B",
            "4 3 2 1,0A4B",
            "1 2 3 4,4A0B",
    })
    void should_return_right_result(String guessNumber, String guessResult) {
        String guess = game.guess(Answer.create(guessNumber));
        assertThat(guess).isEqualTo(guessResult);
    }


    @Test
    void should_throw_exception_when_input_illegal_with_length_not_equals_4() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> game.guess(Answer.create("1 1")));
        assertThat(exception.getMessage()).isEqualTo("Guess number length is not equals 4");
    }

    @Test
    void should_throw_exception_when_input_illegal_with_number_duplicate() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> game.guess(Answer.create("1 1 2 4")));
        assertThat(exception.getMessage()).isEqualTo("Guess number is duplicate");
    }

    @Test
    void should_record_every_guest_result() {
        game.guess(Answer.create("2 4 7 8"));
        game.guess(Answer.create("0 3 2 4"));

        List<GuessResult> history = game.getHistory();
        GuessResult guessResult1 = history.get(0);
        assertThat(guessResult1.getGuessNumber()).isEqualTo("2 4 7 8");
        assertThat(guessResult1.getResult()).isEqualTo("0A2B");

        GuessResult guessResult2 = history.get(1);
        assertThat(guessResult2.getGuessNumber()).isEqualTo("0 3 2 4");
        assertThat(guessResult2.getResult()).isEqualTo("1A2B");
    }

    @Test
    void should_on_going_game_when_guess_wrong_less_than_or_equals_6_times() {
        game.guess(Answer.create("1 6 3 4"));
        assertThat(game.status()).isEqualTo("going");
    }

    @Test
    void should_win_game_when_guess_right_less_than_or_equals_6_times() {
        game.guess(Answer.create("1 2 3 4"));
        assertThat(game.status()).isEqualTo("win");
    }

    @Test
    void should_fail_game_when_guess_wrong_6_times() {
        game.guess(Answer.create("1 9 3 4"));
        game.guess(Answer.create("1 9 3 4"));
        game.guess(Answer.create("1 9 3 4"));
        game.guess(Answer.create("1 9 3 4"));
        game.guess(Answer.create("1 9 3 4"));
        game.guess(Answer.create("1 9 3 4"));
        assertThat(game.status()).isEqualTo("failure");
    }

    @Test
    void should_throw_exception_when_guess_times_more_than_6() {
        game.guess(Answer.create("1 9 3 4"));
        game.guess(Answer.create("1 9 3 4"));
        game.guess(Answer.create("1 9 3 4"));
        game.guess(Answer.create("1 9 3 4"));
        game.guess(Answer.create("1 9 3 4"));
        game.guess(Answer.create("1 9 3 4"));
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> game.guess(Answer.create("1 3 2 4")));
        assertThat(exception.getMessage()).isEqualTo("game is over");
    }
}