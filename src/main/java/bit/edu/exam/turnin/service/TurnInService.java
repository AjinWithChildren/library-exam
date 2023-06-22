package bit.edu.exam.turnin.service;

import bit.edu.exam.turnin.dao.TurnInDAO;
import bit.edu.exam.turnin.dto.BookTurnInDTO;
import bit.edu.exam.user.dao.impl.UserDAO;
import bit.edu.exam.user.exception.UserNotFoundException;
import java.util.List;

public class TurnInService {

    private final TurnInDAO turnInDAO;
    private final UserDAO userDAO;

    public TurnInService() {
        this.userDAO = new UserDAO();
        this.turnInDAO = new TurnInDAO();
    }

    // 반납하기
    public void turnIn(Integer bookSeq, String userId) {
        userDAO.findByUserId(userId)
            .orElseThrow(UserNotFoundException::new);
        turnInDAO.turnIn(bookSeq, userId);
    }

    // 반납예정목록
    public List<BookTurnInDTO> returnUpcoming(String userId) {

        // 1. 아이디확인 -> user 담당이 한거 넣어지면 넣을께요 . . .
        return turnInDAO.returnUpComingList(userId);
    }

    // 전체 반납목록
    public List<BookTurnInDTO> returnBooks(String userId) {
        userDAO.findByUserId(userId)
            .orElseThrow(UserNotFoundException::new);
        return turnInDAO.returnBooks(userId);
    }

    // 미반납목록
    public List<BookTurnInDTO> noReturnBooks(String userId) {
        userDAO.findByUserId(userId)
            .orElseThrow(UserNotFoundException::new);
        return turnInDAO.noReturnBooks(userId);
    }


}
