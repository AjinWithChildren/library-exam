package bit.edu.exam.user.exception;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class BookNotFoundException extends RuntimeException {
    public static String MESSAGE = "해당 책을 찾을 수 없습니다.";

    public BookNotFoundException() {
        super(MESSAGE);
    }
}
