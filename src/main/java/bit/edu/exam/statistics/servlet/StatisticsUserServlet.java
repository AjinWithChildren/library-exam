package bit.edu.exam.statistics.servlet;

import bit.edu.exam.statistics.dto.service.StatisticsServiceDto;
import bit.edu.exam.statistics.service.StatisticsService;
import bit.edu.exam.util.ObjectMapperUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "statisticsUser", urlPatterns = "/statistics-user")
public class StatisticsUserServlet extends HttpServlet {
    private final StatisticsService statisticsService;

    public StatisticsUserServlet() {
        this.statisticsService = new StatisticsService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String parameter = request.getParameter("userId");
        StatisticsServiceDto statisticsDto = statisticsService.getUserStatistics(parameter);
        String value = ObjectMapperUtil.getObjectMapper().writeValueAsString(statisticsDto);

        PrintWriter writer = response.getWriter();
        writer.write(value);
    }

}
