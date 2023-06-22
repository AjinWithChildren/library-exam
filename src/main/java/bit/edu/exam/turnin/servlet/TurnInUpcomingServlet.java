package bit.edu.exam.turnin.servlet;

import bit.edu.exam.borrow.service.BorrowService;
import bit.edu.exam.turnin.dto.BookTurnInDTO;
import bit.edu.exam.turnin.service.TurnInService;
import bit.edu.exam.util.ObjectMapperUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @since 1.0
 */
@WebServlet(name = "turnInUpComing", urlPatterns = "/turin-in/upcoming")
public class TurnInUpcomingServlet extends HttpServlet {
    private final TurnInService turnInService;

    public TurnInUpcomingServlet() {
        this.turnInService = new TurnInService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");

        List<BookTurnInDTO> upcomingBooks = turnInService.returnUpcoming(userId);
        String values = ObjectMapperUtil.getObjectMapper().writeValueAsString(upcomingBooks);

        PrintWriter writer = resp.getWriter();
        writer.write(values);
    }
}
