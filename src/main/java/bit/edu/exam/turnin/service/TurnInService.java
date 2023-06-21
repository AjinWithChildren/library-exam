package bit.edu.exam.turnin.service;

import bit.edu.exam.turnin.dao.TurnInDAO;
import bit.edu.exam.turnin.dto.BookTurnInDTO;

import java.util.ArrayList;
import java.util.List;

public class TurnInService {

    private TurnInDAO turnInDAO;

    public TurnInService(TurnInDAO turnInDAO) {

        this.turnInDAO = turnInDAO;
    }

    // 반납하기
    public void turnIn(Integer bookSeq, String userId){
        // 1. 아이디확인 -> user 담당이 한거 넣어지면 넣을께요 . . .
        // 2. 책 등록여부가 없지만 책 존재유무 확인 . . .
        // 3. 책을 반납하기
        turnInDAO.turnIn(bookSeq, userId);
    }

    // 반납예정목록
    public List<BookTurnInDTO> noReturnDate(String userId){

        // 1. 아이디확인 -> user 담당이 한거 넣어지면 넣을께요 . . .
        return turnInDAO.noReturnDate(userId);
    }

    // 반납완료목록
    public List<BookTurnInDTO> returnBook(String userId){

        // 1. 아이디확인 -> user 담당이 한거 넣어지면 넣을께요 . . .
        return turnInDAO.returnBook(userId);
    }


}
