package bit.edu.exam.borrow.servlet;

import bit.edu.exam.book.dto.NotBorrowBookDTO;
import bit.edu.exam.borrow.service.BorrowService;
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

@WebServlet(value = "notBorrow",urlPatterns = "/borrow-not")
public class NotBorrowServlet extends HttpServlet {
    private final BorrowService borrowService;

    public NotBorrowServlet() {
        this.borrowService = new BorrowService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<NotBorrowBookDTO> notBorrowBooks = borrowService.findNotBorrowBooks();

        String values = ObjectMapperUtil.getObjectMapper().writeValueAsString(notBorrowBooks);
        PrintWriter writer = resp.getWriter();
        writer.println(values);
    }
}
