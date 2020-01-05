/**
 * @author huisheng.jin
 * @date 2020/1/5.
 */
public class BeyondGameGuessTimesException extends RuntimeException {
    private String msg;

    BeyondGameGuessTimesException(String msg) {

        this.msg = msg;
    }
}
