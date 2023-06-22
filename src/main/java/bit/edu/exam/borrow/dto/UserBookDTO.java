package bit.edu.exam.borrow.dto;

import java.time.LocalDate;

public class UserBookDTO {
    private int bookSeq;

    private String userId;
    private String bookTitle;
    private String bookAuthor;

    private LocalDate borrowStart;
    private LocalDate borrowEnd;
    private LocalDate returnDate;

    public UserBookDTO(int bookSeq, String userId, String bookTitle, String bookAuthor, LocalDate borrowStart, LocalDate borrowEnd, LocalDate returnDate) {
        this.bookSeq = bookSeq;
        this.userId = userId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.borrowStart = borrowStart;
        this.borrowEnd = borrowEnd;
        this.returnDate = returnDate;
    }

    public int getBookSeq() {
        return bookSeq;
    }

    public String getUserId(){
        return userId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public LocalDate getBorrowStart() {
        return borrowStart;
    }

    public LocalDate getBorrowEnd() {
        return borrowEnd;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"bookSeq\":" + bookSeq +
                ", \"userId\":" + "\"" + userId  + "\""  +
                ", \"bookTitle\":" + "\"" + bookTitle + "\"" +
                ", \"bookAuthor\":" + "\"" + bookAuthor + "\"" +
                ", \"borrowStart\":" + "\"" + borrowStart + "\"" +
                ", \"borrowEnd\":" + "\"" + borrowEnd + "\"" +
                ", \"returnDate\":" + "\"" + returnDate + "\"" +
                '}';
    }
}
