import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author huisheng.jin
 * @date 2020/1/5.
 */
class AnswerTest {
    @Test
    void should_check_valid() {
        Answer answer = new Answer("1 2 3 4");
        assertThat(answer.checkValid()).isEqualTo(true);
    }

    @Test
    void should_throw_exception_when_input_answer_unValid() {
        Assertions.assertThrows(IllegalArgumentException.class, new Answer("1 2")::checkValid, "输入参数不合法");
        Assertions.assertThrows(IllegalArgumentException.class, new Answer("1 2 2 2")::checkValid, "输入参数不合法");
        Assertions.assertThrows(IllegalArgumentException.class, new Answer("1 2 2 i")::checkValid, "输入参数不合法");
    }
}
