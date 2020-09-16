package guessnumber;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author huisheng.jin
 * @date 2020/9/16.
 */
class AnswerGeneratorTest {

    @Test
    void generateSuccess() {
        AnswerGenerator answerGenerator = new AnswerGenerator();
        checkAnswer(answerGenerator.generate());
        checkAnswer(answerGenerator.generate());
        checkAnswer(answerGenerator.generate());
        checkAnswer(answerGenerator.generate());
    }

    private void checkAnswer(String answer) {
        String[] answerArray = answer.split(" ");

        assertThat(answerArray.length).isEqualTo(4);
        int size = new HashSet<>(Arrays.asList(answerArray)).size();
        assertThat(size).isEqualTo(4);
        System.out.println(answer);

    }
}