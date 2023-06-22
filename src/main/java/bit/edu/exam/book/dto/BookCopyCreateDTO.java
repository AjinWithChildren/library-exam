package bit.edu.exam.book.dto;

import bit.edu.exam.book.type.BookStatus;

/**
 * BookCopy 를 생성하기위한 정보가 담긴 DTO 입니다.
 *
 * @author : 유호철
 * @since 1.0
 */
public class BookCopyCreateDTO {
    private String position;
    private BookStatus status;
    private String isbn;

    public BookCopyCreateDTO(String position, String isbn) {
        this.position = position;
        this.status = BookStatus.BASIC;
        this.isbn = isbn;
    }

    public String getPosition() {
        return position;
    }

    public BookStatus getStatus() {
        return status;
    }

    public String getIsbn() {
        return isbn;
    }
}
