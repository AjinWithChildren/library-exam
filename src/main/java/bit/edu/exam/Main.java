package bit.edu.exam;

import bit.edu.exam.borrow.dto.UserBookDTO;
import bit.edu.exam.borrow.service.BorrowService;
import com.mysql.cj.xdevapi.JsonArray;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class Main {
    public static void main(String[] args) throws SQLException {
        BorrowService borrowService = new BorrowService();
        borrowService.getUserBookList("user1");
        System.out.println(borrowService.getUserBookList("user1"));

    }

    public void bookListToJson(String userId) throws SQLException {
        BorrowService borrowService = new BorrowService();
        List<UserBookDTO> userBookDTOList = borrowService.getUserBookList(userId);

    }
}