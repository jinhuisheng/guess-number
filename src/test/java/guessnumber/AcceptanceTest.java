package guessnumber;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author huisheng.jin
 * @date 2020/9/14.
 */
public class AcceptanceTest {

    private AnswerGenerator answerGenerator;
    private GameAppService gameAppService;

    @BeforeEach
    void setUp() {
        answerGenerator = Mockito.mock(AnswerGenerator.class);
        when(answerGenerator.generate()).thenReturn("1 2 3 4");
    }

    @Test
    void startGame() {
        start();
        verify(answerGenerator, times(1)).generate();
    }

    @Test
    void play() {
        start();
        GameGuessResult gameResult = gameAppService.play("1 2 3 4");

        assertThat(gameResult.getResult()).isEqualTo("4A0B");
        assertThat(gameResult.getStatus()).isEqualTo("win");

        List<GuessResult> guestHistory = gameResult.getHistory();
        assertThat(guestHistory.size()).isEqualTo(1);

        GuessResult guestResult = guestHistory.get(0);
        assertThat(guestResult.getGuessNumber()).isEqualTo("1 2 3 4");
        assertThat(guestResult.getResult()).isEqualTo("4A0B");
    }

    private void start() {
        gameAppService = new GameAppService(answerGenerator);
        gameAppService.start();
    }
}
