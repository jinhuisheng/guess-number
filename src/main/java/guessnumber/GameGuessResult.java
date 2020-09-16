package guessnumber;

import java.util.List;

/**
 * @author huisheng.jin
 * @date 2020/9/16.
 */
public class GameGuessResult {
    private final String result;
    private final List<GuessResult> history;
    private final String gameStatus;

    public GameGuessResult(String result, List<GuessResult> history, String gameStatus) {
        this.result = result;
        this.history = history;
        this.gameStatus = gameStatus;
    }

    public String getResult() {
        return result;
    }

    public List<GuessResult> getHistory() {
        return history;
    }

    public String getStatus() {
        return gameStatus;
    }
}
