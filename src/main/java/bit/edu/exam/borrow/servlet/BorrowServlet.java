package bit.edu.exam.borrow.servlet;

import bit.edu.exam.borrow.dto.UserBookDTO;
import bit.edu.exam.borrow.service.BorrowService;

import bit.edu.exam.util.ObjectMapperUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        List<UserBookDTO> userBookList = borrowService.getUserBookList(parameter);

        resp.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = resp.getWriter();

        ObjectMapper objectMapper = ObjectMapperUtil.getObjectMapper();
        String result = objectMapper.writeValueAsString(userBookList);
        printWriter.write(result);

    }
}
