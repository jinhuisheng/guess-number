import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author huisheng.jin
 * @date 2019/12/30.
 */
class GameTest {
    private Game game;

    @BeforeEach
    void setUp() {
        AnswerGenerator stubAnswerGenerator = Mockito.mock(AnswerGenerator.class);
        when(stubAnswerGenerator.generate()).thenReturn(new Answer("1 2 3 4"));

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
    void should_throw_exception_when_input_answer_number_not_enough() {
        Assertions.assertThrows(RuntimeException.class, () -> game.guess(new Answer("1 1")), "格式不正确:输入数字位数不够");
    }

    @Test
    void should_throw_exception_when_input_answer_number_beyond_scope() {
        Assertions.assertThrows(RuntimeException.class, () -> game.guess(new Answer("1 2 3 20")), "格式不正确:输入数字超出0-9范围");
    }

}
