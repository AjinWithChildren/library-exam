package bit.edu.exam.book.dto;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @see
 * @since 1.0
 */
public class NotBorrowBookDTO {
    private int bookNo;
    private String bookTitle;
    private String bookAuthor;

    public NotBorrowBookDTO(int bookNo, String bookTitle, String bookAuthor) {
        this.bookNo = bookNo;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
    }

    public int getBookNo() {
        return bookNo;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }
}
