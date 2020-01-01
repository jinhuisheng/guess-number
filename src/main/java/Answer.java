import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author huisheng.jin
 * @date 2019/12/31.
 */
public class Answer {
    private List<String> answerNumberList;

    public Answer(String answer) {
        answerNumberList = Arrays.asList(answer.split(" "));
    }

    Boolean checkValid() {
        if (answerNumberList.size() < Constant.ANSWER_NUMBER_LENGTH) {
            throw new RuntimeException("格式不正确:输入数字位数不够");
        }
        Set<String> collect = new HashSet<>(answerNumberList);
        if (collect.size() < Constant.ANSWER_NUMBER_LENGTH) {
            throw new RuntimeException("格式不正确:输入数字重复");
        }
        boolean beyondScope = answerNumberList.stream().anyMatch(number -> Integer.parseInt(number) >= 10);
        if (beyondScope) {
            throw new RuntimeException("格式不正确:输入数字超出0-9范围");
        }
        return true;
    }

    String compare(Answer guessAnswer) {
        int locationAndNumberRightCount = 0;
        int locationRightCount = 0;

        List<String> guessAnswerNumberList = guessAnswer.getAnswerNumberList();
        for (int i = 0; i < answerNumberList.size(); i++) {
            if (guessAnswerNumberList.get(i).equals(answerNumberList.get(i))) {
                locationAndNumberRightCount += 1;
                continue;
            }
            if (answerNumberList.contains(guessAnswerNumberList.get(i))) {
                locationRightCount += 1;
            }
        }
        return locationAndNumberRightCount + "A" + locationRightCount + "B";
    }

    private List<String> getAnswerNumberList() {
        return this.answerNumberList;
    }

    @Override
    public boolean equals(Object obj) {
        Answer compareAnswer = (Answer) obj;
        for (int i = 0; i < this.answerNumberList.size(); i++) {
            if (!this.answerNumberList.get(i).equals(compareAnswer.answerNumberList.get(i))) {
                return false;
            }
        }
        return true;
    }
}
