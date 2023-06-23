package bit.edu.exam.borrow.service;

import bit.edu.exam.book.dto.NotBorrowBookDTO;
import bit.edu.exam.borrow.dao.BorrowDAO;
import bit.edu.exam.borrow.dto.BorrowDTO;
import bit.edu.exam.borrow.dto.UserBookDTO;
import bit.edu.exam.util.ConnectionManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The type Borrow service.
 */
public class BorrowService {

    private static final String BOOK_POSITION_PREFIX = "BS-";
    private static final String LIBRARY_BOOK_POSITION = "0001";
    private final BorrowDAO borrowDAO;
    /**
     * Instantiates a new Borrow service.
     */
    public BorrowService() {
        this.borrowDAO = new BorrowDAO();
    }

    /**
     * Borrow book.
     *
     * @param userId  the user id
     * @param bookSeq the book seq
     */
    public void borrowBook(String userId, int bookSeq) {
        Connection connection = ConnectionManager.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        BorrowDTO borrowDTO = new BorrowDTO(bookSeq, userId);
        // 1. 유저 service stop check         // 2. 해당 책이 대출 중인지 아닌지 체크
        if (checkBorrowCondition(connection, userId, bookSeq)) {
            modifyBookPositionByUser(bookSeq, userId);
            borrowDAO.bookBorrow(connection, borrowDTO);
            System.out.println("대출 완료");
        }

        try {
            connection.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkBorrowCondition(Connection connection, String userId, int bookSeq) {
        return (borrowDAO.userBorrowStateCheck(connection, userId)) &&
            (borrowDAO.bookBorrowStateCheck(connection, bookSeq));
    }

    /**
     * Gets user book list.
     *
     * @param userId the user id
     * @return the user book list
     */
    public List<UserBookDTO> getUserBookList(String userId) {
        return borrowDAO.userBookListByUserId(userId);
    }


    /**
     * 책 대출시에 book_position을 PREFIX + userId 로 변경하는 메서드 입니다.
     *
     * @param bookSeq 책 번호
     * @param userId  유저 아이디
     * @author 강명관
     */
    public void modifyBookPositionByUser(int bookSeq, String userId) {
        Connection connection = ConnectionManager.getConnection();
        String bookPosition = BOOK_POSITION_PREFIX.concat(userId);

        borrowDAO.updateBookPosition(connection, bookPosition, bookSeq);

    }

    /**
     * 책 반납시에 book_position을 도서관으로 변경하는 메서드 입니다.
     *
     * @param bookSeq 책 번호
     * @author 강명관
     */
    public void modifyBookPositionByLibrary(int bookSeq) {
        Connection connection = ConnectionManager.getConnection();
        String bookPosition = BOOK_POSITION_PREFIX.concat(LIBRARY_BOOK_POSITION);

        borrowDAO.updateBookPosition(connection, bookPosition, bookSeq);
    }

    /**
     * 미 대출된책을 조회하는 서비스
     *
     * @return the list
     */
    public List<NotBorrowBookDTO> findNotBorrowBooks() {
        return borrowDAO.findNotBorrowBooks();
    }
}
