package guessnumber;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huisheng.jin
 * @date 2020/9/16.
 */
public class Game {
    private static final int GUESS_TIMES = 6;
    private static final String RIGHT_RESULT = "4A0B";

    private final Answer answer;

    private List<GuessResult> resultList = new ArrayList<>();
    private String status = "going";

    public Game(Answer answer) {
        this.answer = answer;
    }

    public String guess(Answer guessAnswer) {
        checkGuessTimes();
        String result = answer.compare(guessAnswer);
        saveGuessResult(guessAnswer.getAnswer(), result);
        updateGameStatus(result);
        return result;
    }

    private void checkGuessTimes() {
        if (resultList.size() >= 6) {
            throw new RuntimeException("game is over");
        }
    }

    private void updateGameStatus(String result) {
        if (result.equals(RIGHT_RESULT)) {
            this.status = "win";
            return;
        }
        if (resultList.size() >= GUESS_TIMES) {
            this.status = "failure";
        }
    }

    private void saveGuessResult(String guessNumber, String result) {
        resultList.add(new GuessResult(guessNumber, result));
    }

    public List<GuessResult> getHistory() {
        return this.resultList;
    }

    public String status() {
        return this.status;
    }
}
