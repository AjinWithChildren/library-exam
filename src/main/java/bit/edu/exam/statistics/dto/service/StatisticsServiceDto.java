package bit.edu.exam.statistics.dto.service;

/**
 * 도서관 회원의 대출과 반납 현황을 나타내는 DTO 입니다.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class StatisticsServiceDto {
    private int currentTotalBorrowCount;
    private int currentTurnInCount;
    private int nonReturnBook;
    private int currentBorrowBook;
    private int toBeReturnBook;

    public StatisticsServiceDto(int currentTotalBorrowCount,
                                int currentTurnInCount, int nonReturnBook,
                                int currentBorrowBook, int toBeReturnBook) {
        this.currentTotalBorrowCount = currentTotalBorrowCount;
        this.currentTurnInCount = currentTurnInCount;
        this.nonReturnBook = nonReturnBook;
        this.currentBorrowBook = currentBorrowBook;
        this.toBeReturnBook = toBeReturnBook;
    }

    public int getCurrentTotalBorrowCount() {
        return currentTotalBorrowCount;
    }

    public int getToBeReturnBook() {
        return toBeReturnBook;
    }

    public int getNonReturnBook() {
        return nonReturnBook;
    }

    public int getCurrentTurnInCount() {
        return currentTurnInCount;
    }

    public int getCurrentBorrowBook() {
        return currentBorrowBook;
    }
}
