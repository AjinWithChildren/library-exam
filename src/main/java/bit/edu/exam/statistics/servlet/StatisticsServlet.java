package bit.edu.exam.statistics.servlet;

import bit.edu.exam.statistics.dao.StatisticsDao;
import bit.edu.exam.statistics.dto.service.StatisticsServiceDto;
import bit.edu.exam.statistics.service.StatisticsService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//  FIXME: 그냥 안돌아감 명관몬 해줘!
public class StatisticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StatisticsService statisticsService = new StatisticsService();

        String parameter = request.getParameter("user_id");
        StatisticsServiceDto statisticsDto = statisticsService.getUserStatistics(parameter);
        List<StatisticsServiceDto> statisticsDtoList = new ArrayList<>();
        statisticsDtoList.add(statisticsDto);
        System.out.println(statisticsDtoList);

        response.setCharacterEncoding("UTF-8");
        PrintWriter printWriter = response.getWriter();
        printWriter.write(statisticsDtoList.toString());
    }

}
