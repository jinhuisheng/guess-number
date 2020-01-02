import java.util.ArrayList;
import java.util.List;

/**
 * @author huisheng.jin
 * @date 2019/12/31.
 */
public class Game {
    private Answer answer;
    private List<GuessLog> history;
    private String gameResult;
    private Integer guessTimes;

    public Game(AnswerGenerator answerGenerator) {
        this.answer = answerGenerator.generate();
        this.history = new ArrayList<>();
        this.gameResult = "pending";
        this.guessTimes = 0;
    }

    public String guess(Answer guessAnswer) {
        checkTimes();
        guessAnswer.checkValid();
        String guessResult = compare(guessAnswer);
        saveLog(guessAnswer, guessResult);
        saveGuessTimes();
        setGameResult(guessResult);
        return guessResult;
    }

    private String compare(Answer guessAnswer) {
        return answer.compare(guessAnswer);
    }

    private void saveGuessTimes() {
        guessTimes += 1;
    }

    private void checkTimes() {
        if (isReachMaxGuessTime()) {
            throw new RuntimeException("超过6次，不能再猜测");
        }
    }

    private void setGameResult(String result) {
        if (isWin(result)) {
            this.gameResult = "win";
            return;
        }
        if (isReachMaxGuessTime()) {
            this.gameResult = "failure";
        }
    }

    private boolean isReachMaxGuessTime() {
        return guessTimes >= Constant.MAX_GUESS_TIMES;
    }

    private boolean isWin(String result) {
        return result.equals(Constant.RIGHT_GUESS_RESULT);
    }

    private void saveLog(Answer guessAnswer, String result) {
        history.add(new GuessLog(guessAnswer, result));
    }

    public List<GuessLog> getHistory() {
        return history;
    }

    public String getGameResult() {
        return gameResult;
    }
}
