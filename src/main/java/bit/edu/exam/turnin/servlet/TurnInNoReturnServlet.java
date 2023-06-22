package bit.edu.exam.turnin.servlet;

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
 * @author : 유호철
 * @since 1.0
 */
@WebServlet(name = "turnInNoReturn", urlPatterns = "/turn-in/no-return")
public class TurnInNoReturnServlet extends HttpServlet {
    private final TurnInService turnInService;
    public TurnInNoReturnServlet() {
        turnInService = new TurnInService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");

        List<BookTurnInDTO> noReturnDate = turnInService.noReturnBooks(userId);
        String noReturnValues = ObjectMapperUtil.getObjectMapper().writeValueAsString(noReturnDate);

        PrintWriter writer = resp.getWriter();
        writer.write(noReturnValues);
    }
}
