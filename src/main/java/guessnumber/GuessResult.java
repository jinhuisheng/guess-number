package guessnumber;

/**
 * @author huisheng.jin
 * @date 2020/9/16.
 */
public class GuessResult {
    private String guessNumber;
    private String result;

    public GuessResult(String guessNumber, String result) {
        this.guessNumber = guessNumber;
        this.result = result;
    }

    public String getGuessNumber() {
        return guessNumber;
    }

    public String getResult() {
        return result;
    }
}
