package bit.edu.exam.book.exception;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @since 1.0
 */
public class BookInfoAlreayCreatedException extends RuntimeException {
    public static final String MESSAGE = "이미 해당 ISBN 으로 책이 생성되어 있습니다.";

    public BookInfoAlreayCreatedException() {
        super(MESSAGE);
    }
}
