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
 * Some description here.
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
        "SELECT " +
        "  (SELECT COUNT(*) FROM book_use_status AS bus WHERE bus.user_id = bu.user_id AND bus.return_date IS NULL) AS current_total_borrow_count, " +
        "  (SELECT COUNT(*) FROM book_use_status AS bus WHERE bus.user_id = bu.user_id AND bus.return_date IS NULL AND bus.borrow_end > now()) AS to_be_return_book, " +
        "  (SELECT COUNT(*) FROM book_use_status AS bus WHERE bus.user_id = bu.user_id AND bus.return_date IS NULL AND bus.borrow_end <= now()) AS non_return_book " +
        "FROM book_user AS bu " +
        "WHERE bu.user_id = ?";

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
                    resultSet.getInt("total_to_be_return_book"),
                    resultSet.getInt("total_non_return_book")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return Optional.ofNullable(statisticsAdminDto);
    }

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
