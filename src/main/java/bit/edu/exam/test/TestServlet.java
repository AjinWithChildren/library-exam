package bit.edu.exam.test;

import bit.edu.exam.borrow.dto.UserBookDTO;
import bit.edu.exam.borrow.service.BorrowService;
import bit.edu.exam.user.dto.UserInfoDTO;
import bit.edu.exam.user.dto.type.UserGrade;
import bit.edu.exam.user.dto.type.UserStatus;
import bit.edu.exam.util.ObjectMapperUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Properties;
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
//@WebServlet(name = "test", value = "/test")
public class TestServlet extends HttpServlet {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        resp.setContentType("text/html");

        UserBookDTO user1 = new UserBookDTO(
            1,
            "테스트 한글",
            "테스트 한글 제목",
            "테스트 한글 저자",
            LocalDate.now(),
            LocalDate.now().plusDays(13),
            null
        );

        printWriter.write(ObjectMapperUtil.getObjectMapper().writeValueAsString(user1));
    }
}
