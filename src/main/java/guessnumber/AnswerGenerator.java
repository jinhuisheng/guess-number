package guessnumber;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author huisheng.jin
 * @date 2020/9/16.
 */
public class AnswerGenerator {

    private final static String[] NUMBER_ARRAY = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public String generate() {
        List<String> list = new ArrayList<>(Arrays.asList(NUMBER_ARRAY));
        return IntStream.range(0, 4).boxed().map(i -> random(list)).collect(Collectors.joining(" "));
    }

    private String random(List<String> list) {
        int index = new Random().nextInt(list.size());
        String result = list.get(index);
        list.remove(index);
        return result;
    }
}
