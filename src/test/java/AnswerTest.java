import org.junit.jupiter.api.Assertions;
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

    @Test
    void should_throw_exception_when_input_answer_number_not_enough() {
        Assertions.assertThrows(RuntimeException.class, () -> new Answer("1 1").checkValid(), "格式不正确:输入数字位数不够");
    }

    @Test
    void should_throw_exception_when_input_answer_number_beyond_scope() {
        Assertions.assertThrows(RuntimeException.class, () -> new Answer("1 2 3 20").checkValid(), "格式不正确:输入数字超出0-9范围");
    }

}