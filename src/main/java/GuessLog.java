/**
 * @author huisheng.jin
 * @date 2020/1/5.
 */
public class GuessLog {
    private String guessAnswer;
    private String guessResult;

    GuessLog(String guessAnswer, String guessResult) {

        this.guessAnswer = guessAnswer;
        this.guessResult = guessResult;
    }

    public String getGuessAnswer() {
        return guessAnswer;
    }

    public String getGuessResult() {
        return guessResult;
    }
}
