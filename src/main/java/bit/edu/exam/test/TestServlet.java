package bit.edu.exam.test;

import bit.edu.exam.borrow.dto.UserBookDTO;
import bit.edu.exam.user.dto.UserInfoDTO;
import bit.edu.exam.user.dto.type.UserGrade;
import bit.edu.exam.user.dto.type.UserStatus;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
//@WebServlet(name = "test", value = "/test")
public class TestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");

        UserBookDTO user1 = new UserBookDTO(
            1,
            "user1",
            "testBookTitle",
            "testBookAuthor",
            LocalDate.now(),
            LocalDate.now().plusDays(13),
            null
        );
        printWriter.write(user1.toString());
    }
}
