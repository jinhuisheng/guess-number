import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author huisheng.jin
 * @date 2019/12/31.
 */
class AnswerTest {

    @Test
    void should_return_true_when_answer_same() {
        boolean result = new Answer("1 2 3 4").equals(new Answer("1 2 3 4"));
        assertThat(result).isEqualTo(true);
    }
}