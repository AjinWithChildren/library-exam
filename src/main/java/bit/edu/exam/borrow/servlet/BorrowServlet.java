package bit.edu.exam.borrow.servlet;

import bit.edu.exam.borrow.dto.UserBookDTO;
import bit.edu.exam.borrow.service.BorrowService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BorrowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BorrowService borrowService = new BorrowService();

        String parameter = req.getParameter("userId");
        System.out.println(parameter);
        List<UserBookDTO> userBookList = borrowService.getUserBookList(parameter);
        System.out.println(userBookList);

        // localhost:8080/borrow?user-id=user14

        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();
        printWriter.write(userBookList.toString());

    }
}
