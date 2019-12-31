/**
 * @author huisheng.jin
 * @date 2019/12/31.
 */
public class Game {
    private Answer answer;

    public Game(AnswerGenerator answerGenerator) {
        this.answer = answerGenerator.generate();
    }

    public String guess(Answer guessAnswer) {
        guessAnswer.check();
        return this.answer.compare(guessAnswer);
    }

}
