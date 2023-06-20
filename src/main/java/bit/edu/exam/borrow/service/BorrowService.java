package bit.edu.exam.borrow.service;

import bit.edu.exam.borrow.dao.BorrowDAO;
import bit.edu.exam.borrow.dto.BorrowDTO;
import bit.edu.exam.borrow.dto.UserBookDTO;

import java.sql.SQLException;
import java.util.List;

public class BorrowService {

    private final BorrowDAO borrowDAO;
    public BorrowService(){
        this.borrowDAO = new BorrowDAO();
    }


    public void borrowBook(String userId, int bookSeq) throws SQLException {
        BorrowDTO borrowDTO = new BorrowDTO(bookSeq, userId);
        // 1. 유저 service stop check         // 2. 해당 책이 대출 중인지 아닌지 체크
        if ((borrowDAO.userBorrowStateCheck(userId)) && (borrowDAO.bookBorrowStateCheck(bookSeq))){
            borrowDAO.bookBorrow(borrowDTO);
            System.out.println("대출 완료");
        }
    }

    public List<UserBookDTO> getUserBookList(String userId) throws SQLException {
         return borrowDAO.userBookListByUserId(userId);
    }
}
