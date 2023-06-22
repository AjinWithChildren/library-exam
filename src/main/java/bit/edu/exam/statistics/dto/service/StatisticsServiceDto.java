package bit.edu.exam.statistics.dto.service;

import java.util.Date;

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
    private int maxBookCount;
    private int toBeReturnBook;
    private String userStatus;
    private Date userServiceStop;

    public StatisticsServiceDto(int currentTotalBorrowCount, int currentTurnInCount, int nonReturnBook, int maxBookCount,
                                int toBeReturnBook, String userStatus, Date userServiceStop) {
        this.currentTotalBorrowCount = currentTotalBorrowCount;
        this.currentTurnInCount = currentTurnInCount;
        this.nonReturnBook = nonReturnBook;
        this.maxBookCount = maxBookCount;
        this.toBeReturnBook = toBeReturnBook;
        this.userStatus = userStatus;
        this.userServiceStop = userServiceStop;
    }

    public int getCurrentTotalBorrowCount() {
        return currentTotalBorrowCount;
    }

    public int getCurrentTurnInCount() {
        return currentTurnInCount;
    }

    public int getNonReturnBook() {
        return nonReturnBook;
    }

    public int getMaxBookCount() {
        return maxBookCount;
    }

    public int getToBeReturnBook() {
        return toBeReturnBook;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public Date getUserServiceStop() {
        return userServiceStop;
    }
}
