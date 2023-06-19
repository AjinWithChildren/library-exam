package bit.edu.exam.user.exception;

/**
 * 사용자의 아이디가 이미존재할 경우의 에러입니다.
 *
 * @author : 유호철
 * @since 1.0
 */
public class UserIdAlreadyUsedException extends RuntimeException {
    public static final String MESSAGE = "이미 사용중인 아이디 입니다.";
    public UserIdAlreadyUsedException() {
        super(MESSAGE);
    }
}
