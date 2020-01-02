import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author huisheng.jin
 * @date 2020/1/2.
 */
public class GameApplicationTest {
    private final Answer answer = new Answer("1 2 3 4");
    private GameApplicationService gameApplicationService;

    @BeforeEach
    void setUp() {
        AnswerGenerator stubAnswerGenerator = Mockito.mock(AnswerGenerator.class);
        when(stubAnswerGenerator.generate()).thenReturn(answer);
        gameApplicationService = new GameApplicationService(stubAnswerGenerator);
    }

    @Test
    void should_return_whole_message_when_guess() {

        GuessResult guess = gameApplicationService.guess(new Answer("1 2 3 4"));

        assertThat(guess.getGuessResult()).isEqualTo("4A0B");
        assertThat(guess.getGameResult()).isEqualTo("win");

        List<GuessLog> history = guess.getHistory();
        assertThat(history.size()).isEqualTo(1);
        GuessLog guessLog = history.get(0);
        assertThat(guessLog.getGuessResult()).isEqualTo("4A0B");
        assertThat(guessLog.getGuessAnswer()).isEqualTo(new Answer("1 2 3 4"));

    }
}
