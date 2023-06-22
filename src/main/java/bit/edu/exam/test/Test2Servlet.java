package bit.edu.exam.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
@WebServlet(name = "test2", value = "/test2")
public class Test2Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        resp.sendRedirect("library_front.html");
    }
}
