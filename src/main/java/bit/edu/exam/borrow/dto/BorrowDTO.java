package bit.edu.exam.borrow.dto;

import java.sql.Date;
import java.time.LocalDate;

public class BorrowDTO {
    private int bookSeq;
    private String userId;
    private LocalDate borrowStart;
    private LocalDate borrowEnd;
    private LocalDate returnDate;

    public BorrowDTO(int bookSeq, String userId) {
        this.bookSeq = bookSeq;
        this.userId = userId;
        this.borrowStart = LocalDate.now();
        this.borrowEnd = LocalDate.now().plusDays(13);
        this.returnDate = null;
    }

    public int getBookSeq() {
        return bookSeq;
    }

    public String getUserId() {
        return userId;
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
}
