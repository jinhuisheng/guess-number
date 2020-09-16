package guessnumber;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author huisheng.jin
 * @date 2020/9/16.
 */
public class Answer {
    private static final String NUMBER_SEPARATOR = " ";
    private String answer;

    private Answer(String answer) {
        this.answer = answer;
        this.validate();
    }

    public static Answer create(String guessNumber) {
        return new Answer(guessNumber);
    }

    public List<String> getAnswerNumberList() {
        return Arrays.asList(answer.split(" "));
    }

    public String compare(Answer guessAnswer) {
        List<String> guessNumberList = guessAnswer.getAnswerNumberList();
        List<String> answerNumberList = getAnswerNumberList();
        long rightCount = 0;
        long locationWrongCount = 0;
        for (int i = 0; i < guessNumberList.size(); i++) {
            if (guessNumberList.get(i).equals(answerNumberList.get(i))) {
                rightCount += 1;
            } else {
                if (isAnswerContains(guessNumberList.get(i))) {
                    locationWrongCount += 1;
                }
            }
        }
        return rightCount + "A" + locationWrongCount + "B";
    }

    public String getAnswer() {
        return answer;
    }

    private Boolean isAnswerContains(String currentNumber) {
        return getAnswerNumberList().stream().anyMatch(number -> number.equals(currentNumber));
    }

    private void validate() {
        List<String> guessNumberList = Arrays.asList(answer.split(NUMBER_SEPARATOR));
        if (guessNumberList.size() != 4) {
            throw new RuntimeException("Guess number length is not equals 4");
        }
        int size = new HashSet<>(guessNumberList).size();
        if (size < 4) {
            throw new RuntimeException("Guess number is duplicate");
        }

    }

}
