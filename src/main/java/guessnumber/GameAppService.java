package guessnumber;

/**
 * @author huisheng.jin
 * @date 2020/9/16.
 */
public class GameAppService {
    private AnswerGenerator answerGenerator;
    private String answer;

    public GameAppService(AnswerGenerator answerGenerator) {
        this.answerGenerator = answerGenerator;
    }

    public void start() {
        answer = answerGenerator.generate();
    }

    public GameGuessResult play(String guessNumber) {
        Game game = new Game(Answer.create(answer));
        String result = game.guess(Answer.create(guessNumber));
        return new GameGuessResult(result, game.getHistory(), game.status());
    }
}
