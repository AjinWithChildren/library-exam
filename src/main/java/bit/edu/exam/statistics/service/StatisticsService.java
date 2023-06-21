package bit.edu.exam.statistics.service;

import bit.edu.exam.statistics.dao.StatisticsDao;
import bit.edu.exam.statistics.dto.admin.StatisticsAdminDto;
import bit.edu.exam.statistics.dto.service.StatisticsServiceDto;

/**
 * Some description here.
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class StatisticsService {
    private final StatisticsDao statisticsDao;

    public StatisticsService() {
        this.statisticsDao = new StatisticsDao();
    }

    private static final int DEFAULT_VALUE = 0;

    public StatisticsAdminDto getAdminStatistics() {
        return statisticsDao.findByAdminLibraryStatistics()
            .orElse(new StatisticsAdminDto(
                    DEFAULT_VALUE,
                    DEFAULT_VALUE,
                    DEFAULT_VALUE,
                    DEFAULT_VALUE
                )
            );
    }

    public StatisticsServiceDto getUserStatistics(String userId) {
        return statisticsDao.findByUserLibraryStatistics(userId)
            .orElse(new StatisticsServiceDto(
                    DEFAULT_VALUE,
                    DEFAULT_VALUE,
                    DEFAULT_VALUE
                )
            );
    }
}
