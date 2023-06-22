package bit.edu.exam;

import bit.edu.exam.borrow.dto.UserBookDTO;
import bit.edu.exam.borrow.service.BorrowService;
import bit.edu.exam.util.FileUtils;

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
    public static void main(String[] args) {


    }

    public void bookListToJson(String userId) {
        BorrowService borrowService = new BorrowService();
        List<UserBookDTO> userBookDTOList = borrowService.getUserBookList(userId);
        FileUtils.writeJsonFile(userBookDTOList, "borrowBookList");

    }
}