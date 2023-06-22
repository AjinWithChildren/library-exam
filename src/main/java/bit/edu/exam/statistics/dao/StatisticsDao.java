package bit.edu.exam.statistics.dao;

import bit.edu.exam.statistics.dto.admin.StatisticsAdminDto;
import bit.edu.exam.statistics.dto.service.StatisticsServiceDto;
import bit.edu.exam.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * 도서관 통계를 처리하는 DAO 클래스 입니다..
 *
 * @author : 강명관
 * @since : 1.0
 **/
public class StatisticsDao {

    private static final String ADMIN_STATISTICS_SQL =
        "SELECT " +
        " DISTINCT((SELECT COUNT(*) FROM book_copy AS bc)) AS total_book_count, " +
        " (SELECT COUNT(*) FROM book_use_status AS bus WHERE bus.return_date IS NULL) AS total_borrow_count, " +
        " (SELECT COUNT(*) FROM book_use_status AS bus WHERE bus.return_date IS NULL AND bus.borrow_end > now()) AS total_to_be_return_book, " +
        " (SELECT COUNT(*) FROM book_use_status AS bus WHERE bus.return_date IS NULL AND bus.borrow_end <= now()) AS total_non_return_book " +
        "FROM book_use_status";

    private static final String USER_STATISTICS_SQL =
            "SELECT\n" +
                "    (SELECT COUNT(*) FROM book_use_status AS bus WHERE bus.user_id = bu.user_id AND bus.return_date IS NULL) AS current_total_borrow_count,\n" +
                "    (SELECT COUNT(*) FROM book_use_status AS bus WHERE bus.user_id = bu.user_id AND bus.return_date IS NOT NULL) AS current_turn_in_count,\n" +
                "    (SELECT COUNT(*) FROM book_use_status AS bus WHERE bus.user_id = bu.user_id AND bus.return_date IS NULL AND bus.borrow_end <= now()) AS non_return_book,\n" +
                "    (SELECT max_book - COUNT(case when bus.user_id = bu.user_id AND bus.return_date IS NULL AND bus.borrow_end <= now() then 1 end) FROM book_use_status as bus ) AS current_borrow_book,\n" +
                "    (SELECT COUNT(*) FROM book_use_status AS bus WHERE bus.user_id = bu.user_id AND bus.return_date IS NULL AND bus.borrow_end > now()) AS to_be_return_book,\n" +
                "    bu.user_status,\n" +
                "    bu.service_stop\n" +
                "FROM book_user AS bu\n" +
                "WHERE bu.user_id = ?";


    /**
     * 관리자 통계를 가져오는 메서드 입니다.
     *
     * @return Optional<StatisticsAdminDto>
     */
    public Optional<StatisticsAdminDto> findByAdminLibraryStatistics() {
        Connection connection = ConnectionManager.getConnection();

        StatisticsAdminDto statisticsAdminDto = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADMIN_STATISTICS_SQL);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                statisticsAdminDto = new StatisticsAdminDto(
                    resultSet.getInt("total_book_count"),
                    resultSet.getInt("total_borrow_count"),
                    resultSet.getInt("current_borrow_book"),
                    resultSet.getInt("total_to_be_return_book"),
                    resultSet.getInt("total_non_return_book")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return Optional.ofNullable(statisticsAdminDto);
    }

    /**
     * 회원의 통계를 가져오는 메서드 입니다.
     *
     * @param userId 회원 아이디
     * @return Optional<StatisticsServiceDto>
     */
    public Optional<StatisticsServiceDto> findByUserLibraryStatistics(String userId) {
        Connection connection = ConnectionManager.getConnection();

        StatisticsServiceDto statisticsServiceDto = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(USER_STATISTICS_SQL);
            preparedStatement.setString(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                statisticsServiceDto = new StatisticsServiceDto(
                    resultSet.getInt("current_total_borrow_count"),
                    resultSet.getInt("to_be_return_book"),
                    resultSet.getInt("non_return_book")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(statisticsServiceDto);
    }

}
