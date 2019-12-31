import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author huisheng.jin
 * @date 2019/12/31.
 */
public class AnswerGeneratorTest {
    @Test
    void generate_right() {
        Answer answer = new AnswerGenerator().generate();
        Boolean result = answer.check();
        assertThat(result).isEqualTo(true);
    }
}
