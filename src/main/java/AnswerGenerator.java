import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author huisheng.jin
 * @date 2020/1/5.
 */
public class AnswerGenerator {

    private List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    public Answer generate() {
        Collections.shuffle(list);
        String result = list.stream()
                .limit(4)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
        return new Answer(result);
    }
}
