import java.util.*;

/**
 * @author huisheng.jin
 * @date 2019/12/31.
 */
public class AnswerGenerator {

    private static List<String> list = Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9");

    public Answer generate() {
        List<String> chosenList = generateAnswerNumberList();
        return new Answer(String.join(" ", chosenList));
    }

    private List<String> generateAnswerNumberList() {
        List<String> chosenList = new ArrayList<>();
        for (int i = 0; i < Constant.ANSWER_NUMBER_LENGTH; i++) {
            Collections.shuffle(list);
            String number = choseNumber(chosenList);
            chosenList.add(number);
        }
        return chosenList;
    }

    private String choseNumber(List<String> chosenList) {
        return list.stream()
                .filter(listNum -> isNotExistInChosen(chosenList, listNum))
                .findFirst()
                .get();
    }

    private boolean isNotExistInChosen(List<String> chosenList, String listNum) {
        return chosenList.stream()
                .noneMatch(num -> num.equals(listNum));
    }
}
