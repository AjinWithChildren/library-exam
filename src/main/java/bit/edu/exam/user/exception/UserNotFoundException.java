package bit.edu.exam.user.exception;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @since 1.0
 */
public class UserNotFoundException extends RuntimeException {
    public static final String MESSAGE = "해당하는 유저를 찾을 수 없습니다.";
    public UserNotFoundException() {
        super(MESSAGE);
    }
}
