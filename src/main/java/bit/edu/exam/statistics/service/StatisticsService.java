package bit.edu.exam.statistics.service;

import bit.edu.exam.statistics.dao.StatisticsDao;
import bit.edu.exam.statistics.dto.admin.StatisticsAdminDto;
import bit.edu.exam.statistics.dto.service.StatisticsServiceDto;

/**
 * 도서관의 통계에 대한 서비스 클래스 입니다.
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


    /**
     * 관리자 통계를 가져오는 메서드 입니다.
     *
     * @return 도서관 관리자의 현재 총 대출, 반납 현황
     */
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

    /**
     * 회원의 통계를 가져오는 메서드 입니다.
     *
     * @param userId 유저 아이디
     * @return 도서관 회원의 현재 대출, 반납 현황
     */
    public StatisticsServiceDto getUserStatistics(String userId) {
        return statisticsDao.findByUserLibraryStatistics(userId)
            .orElse(new StatisticsServiceDto(
                    DEFAULT_VALUE,
                    DEFAULT_VALUE,
                    DEFAULT_VALUE,
                    DEFAULT_VALUE,
                    DEFAULT_VALUE
                )
            );
    }
}
