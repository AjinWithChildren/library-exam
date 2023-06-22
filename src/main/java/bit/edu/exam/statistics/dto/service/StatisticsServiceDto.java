package bit.edu.exam.statistics.dto.service;

/**
 * 도서관 회원의 대출과 반납 현황을 나타내는 DTO 입니다.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class StatisticsServiceDto {
    private int currentTotalBorrowCount;
    private int toBeReturnBook;
    private int nonReturnBook;

    public StatisticsServiceDto(int currentTotalBorrowCount, int toBeReturnBook,
                                int nonReturnBook) {
        this.currentTotalBorrowCount = currentTotalBorrowCount;
        this.toBeReturnBook = toBeReturnBook;
        this.nonReturnBook = nonReturnBook;
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
}
