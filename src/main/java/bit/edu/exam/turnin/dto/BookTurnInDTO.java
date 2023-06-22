package bit.edu.exam.turnin.dto;

import java.util.Date;

public class BookTurnInDTO {

    private Integer bookSeq;
    private String userId;
    private String bookTitle;
    private String bookAuthor;
    private Date borrowStart;
    private Date borrowEnd;
    private Date returnDate;

    public BookTurnInDTO(Integer bookSeq, String userId, String bookTitle, String bookAuthor, Date borrowStart, Date borrowEnd, Date returnDate) {
        this.bookSeq = bookSeq;
        this.userId = userId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.borrowStart = borrowStart;
        this.borrowEnd = borrowEnd;
        this.returnDate = returnDate;
    }

    public Integer getBookSeq() {
        return bookSeq;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public Date getBorrowStart() {
        return borrowStart;
    }

    public Date getBorrowEnd() {
        return borrowEnd;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "BookTurnInDTO{" +
            "bookSeq=" + bookSeq +
            ", userId='" + userId + '\'' +
            ", bookTitle='" + bookTitle + '\'' +
            ", bookAuthor='" + bookAuthor + '\'' +
            ", borrowStart=" + borrowStart +
            ", borrowEnd=" + borrowEnd +
            ", returnDate=" + returnDate +
            '}';
    }

    public Integer getBookSeq() {
        return bookSeq;
    }

    public String getUserId() {
        return userId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public Date getBorrowStart() {
        return borrowStart;
    }

    public Date getBorrowEnd() {
        return borrowEnd;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}
