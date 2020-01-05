import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author huisheng.jin
 * @date 2020/1/5.
 */
class AnswerGeneratorTest {
    @Test
    void should_generate_answer_success() {
        AnswerGenerator answerGenerator = new AnswerGenerator();
        Answer answer = answerGenerator.generate();
        assertThat(answer.checkValid()).isEqualTo(true);

        Answer answer2 = answerGenerator.generate();
        assertThat(answer2.checkValid()).isEqualTo(true);
    }

}
