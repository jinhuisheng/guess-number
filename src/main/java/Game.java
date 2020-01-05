
import java.util.ArrayList;
import java.util.List;

/**
 * @author huisheng.jin
 * @date 2020/1/5.
 */
public class Game {
    private static final String RIGHT_GUESS_RESULT = "4A0B";
    private static final int MAX_GUESS_TIMES = 6;
    private Answer answer;
    private List<GuessLog> guessLogList;
    private String gameResult;

    public Game(AnswerGenerator answerGenerator) {
        this.answer = answerGenerator.generate();
        this.gameResult = "going";
        this.guessLogList = new ArrayList<>();
    }

    public String guess(Answer guessAnswer) {
        guessAnswer.checkValid();
        checkGuessTimes();
        String result = this.answer.compare(guessAnswer);
        saveLog(guessAnswer.getAnswer(), result);
        updateGameResult(result);
        return result;
    }

    private void checkGuessTimes() {
        if (guessLogList.size() == MAX_GUESS_TIMES) {
            throw new BeyondGameGuessTimesException("超过游戏次数限制");
        }
    }

    private void updateGameResult(String result) {
        if (RIGHT_GUESS_RESULT.equals(result)) {
            gameResult = "win";
        }
        if (guessLogList.size() == MAX_GUESS_TIMES) {
            gameResult = "lose";
        }
    }

    private void saveLog(String guessAnswer, String result) {
        GuessLog guessLog = new GuessLog(guessAnswer, result);
        guessLogList.add(guessLog);
    }

    public List<GuessLog> getHistory() {
        return guessLogList;
    }

    public String getGameResult() {
        return gameResult;
    }

}
