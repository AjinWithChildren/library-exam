package bit.edu.exam.statistics.dto.admin;

/**
 * 도서관 관리자용 현재 도서관의 대출과 반납 현황을 위한 DTO 입니다.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class StatisticsAdminDto {
    private int totalBookCount;
    private int totalBorrowCount;
    private int currentBorrowBook;
    private int totalToBeReturnBook;
    private int totalNonReturnBook;

    public StatisticsAdminDto(int totalBookCount, int totalBorrowCount,int currentBorrowBook,
                              int totalToBeReturnBook, int totalNonReturnBook) {
        this.totalBookCount = totalBookCount;
        this.totalBorrowCount = totalBorrowCount;
        this.currentBorrowBook = currentBorrowBook;
        this.totalToBeReturnBook = totalToBeReturnBook;
        this.totalNonReturnBook = totalNonReturnBook;
    }

    public int getTotalBookCount() {
        return totalBookCount;
    }

    public int getTotalBorrowCount() {
        return totalBorrowCount;
    }

    public int getTotalToBeReturnBook() {
        return totalToBeReturnBook;
    }

    public int getTotalNonReturnBook() {
        return totalNonReturnBook;
    }

    public int getCurrentBorrowBook() {
        return currentBorrowBook;
    }
}

