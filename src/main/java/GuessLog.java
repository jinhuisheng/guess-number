/**
 * @author huisheng.jin
 * @date 2019/12/31.
 */
public class GuessLog {
    private String guessResult;
    private Answer guessAnswer;

    GuessLog(Answer guessAnswer, String guessResult) {
        this.guessAnswer = guessAnswer;
        this.guessResult = guessResult;
    }

    public String getGuessResult() {
        return guessResult;
    }

    public Answer getGuessAnswer() {
        return guessAnswer;
    }
}
