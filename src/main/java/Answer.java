import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.*;

/**
 * @author huisheng.jin
 * @date 2020/1/5.
 */
public class Answer {
    private String[] numberArray;

    public Answer(String answerInput) {
        numberArray = answerInput.split(" ");
    }

    public boolean checkValid() {
        boolean checkResult = isLengthValid() && isNumberNotRepeat() && isNumeric();
        if (!checkResult) {
            throw new IllegalArgumentException("输入参数不合法");
        }
        return true;

    }

    private Boolean isNumberNotRepeat() {
        return Arrays.stream(numberArray).collect(Collectors.toSet()).size() == 4;
    }

    private boolean isLengthValid() {
        return numberArray.length == 4;
    }

    private boolean isNumeric() {
        Pattern pattern = compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(String.join("", numberArray)).matches();
    }

    String getAnswer() {
        return String.join(" ", numberArray);
    }

    String compare(Answer guessAnswerObj) {
        String[] guessAnswerArray = guessAnswerObj.getNumberArray();

        int rightNumberCount = getRightNumberCount(guessAnswerArray);
        int wrongLocationCount = getWrongLocationCount(guessAnswerArray, rightNumberCount);

        return rightNumberCount + "A" + wrongLocationCount + "B";
    }

    private String[] getNumberArray() {
        return numberArray;
    }

    private int getWrongLocationCount(String[] guessAnswerArray, int rightNumberCount) {
        int containsNumber = (int) Arrays.stream(guessAnswerArray)
                .filter(number -> Arrays.asList(numberArray).contains(number))
                .count();

        return containsNumber - rightNumberCount;
    }

    private int getRightNumberCount(String[] guessAnswerArray) {
        int rightNumberCount = 0;
        for (int i = 0; i < this.numberArray.length; i++) {
            if (this.numberArray[i].equals(guessAnswerArray[i])) {
                rightNumberCount++;
            }
        }
        return rightNumberCount;
    }
}
