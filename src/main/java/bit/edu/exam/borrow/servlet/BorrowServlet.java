package bit.edu.exam.borrow.servlet;

import bit.edu.exam.borrow.dto.UserBookDTO;
import bit.edu.exam.borrow.service.BorrowService;
import bit.edu.exam.util.ObjectMapperUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import bit.edu.exam.util.ObjectMapperUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "borrow", urlPatterns = "/borrow")
public class BorrowServlet extends HttpServlet {

    private final BorrowService borrowService;

    public BorrowServlet() {
        this.borrowService = new BorrowService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");

        List<UserBookDTO> userBookList = borrowService.getUserBookList(userId);
        String value = ObjectMapperUtil.getObjectMapper().writeValueAsString(userBookList);

        PrintWriter writer = resp.getWriter();
        writer.write(value);
    }
}
