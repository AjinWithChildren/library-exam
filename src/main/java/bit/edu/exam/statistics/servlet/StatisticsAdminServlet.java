package bit.edu.exam.statistics.servlet;

import bit.edu.exam.statistics.dto.admin.StatisticsAdminDto;
import bit.edu.exam.statistics.service.StatisticsService;
import bit.edu.exam.util.ObjectMapperUtil;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "statisticsAdmin", urlPatterns = "/statistics-admin")
public class StatisticsAdminServlet extends HttpServlet {
    private final StatisticsService statisticsService;

    public StatisticsAdminServlet() {
        this.statisticsService = new StatisticsService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatisticsAdminDto statistics = statisticsService.getAdminStatistics();

        String value = ObjectMapperUtil.getObjectMapper().writeValueAsString(statistics);
        PrintWriter writer = resp.getWriter();
        writer.write(value);
    }
}
