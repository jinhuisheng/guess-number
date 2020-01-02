import java.util.List;

/**
 * @author huisheng.jin
 * @date 2020/1/2.
 */
public class GameApplicationService {

    private Game game;

    public GameApplicationService(AnswerGenerator answerGenerator) {
        this.game = new Game(answerGenerator);
    }

    public GuessResult guess(Answer answer) {
        String guessResult = game.guess(answer);
        String gameResult = game.getGameResult();
        List<GuessLog> history = game.getHistory();
        return new GuessResult(guessResult, gameResult, history);
    }
}
