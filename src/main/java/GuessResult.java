import java.util.List;

/**
 * @author huisheng.jin
 * @date 2020/1/2.
 */
public class GuessResult {
    private final String guessResult;
    private final String gameResult;
    private final List<GuessLog> history;

    GuessResult(String guessResult, String gameResult, List<GuessLog> history) {
        this.guessResult = guessResult;
        this.gameResult = gameResult;
        this.history = history;
    }

    public List<GuessLog> getHistory() {
        return history;
    }

    public String getGuessResult() {
        return guessResult;
    }

    public String getGameResult() {
        return gameResult;
    }
}
