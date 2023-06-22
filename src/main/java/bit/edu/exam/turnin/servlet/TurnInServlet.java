package bit.edu.exam.turnin.servlet;

import bit.edu.exam.turnin.service.TurnInService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 반납을 하기위한 servlet
 *
 * @author : 유호철
 * @since 1.0
 */
public class TurnInServlet extends HttpServlet {
    private final TurnInService turnInService;

    public TurnInServlet() {
        turnInService = new TurnInService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        Integer bookSeq = Integer.valueOf(req.getParameter("bookSeq"));
        turnInService.turnIn(bookSeq, userId);
    }
}
