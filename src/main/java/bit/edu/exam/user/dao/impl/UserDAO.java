package bit.edu.exam.user.dao.impl;

import bit.edu.exam.user.dto.UserInfoDTO;
import bit.edu.exam.user.dto.UserInsertDTO;
import bit.edu.exam.user.dto.type.UserGrade;
import bit.edu.exam.user.dto.type.UserStatus;
import bit.edu.exam.util.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * User 가 DB 와 접근하기위한 DAO 입니다.
 *
 * @author : 유호철
 * @since 1.0
 */
public class UserDAO {
    private static final String INSERT_USER = "insert into book_user\n" +
        "values (null, ?, ?, ?, ?, ?, ?, null);\n";
    private static final String SELECT_USER_BY_ID = "select bu.user_id,\n" +
        "       bu.user_phone_number,\n" +
        "       bu.user_status,\n" +
        "       bu.user_grade,\n" +
        "       bu.max_book,\n" +
        "       bu.service_stop\n" +
        "from book_user as bu\n" +
        "where user_id = ?;";

    /**
     * 유저를 생성하기위한 메서드입니다.
     *
     * @param user user를 생성하기위한 정보가 기입됩니다.
     */
    public void insertUser(UserInsertDTO user) {

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_USER);
        ) {
            statement.setString(1, user.getUserId());
            statement.setString(2, user.getUserPwd());
            statement.setString(3, user.getPhoneNo());
            statement.setString(4, user.getUserStatus().toString());
            statement.setString(5, user.getUserGrade().toString());
            statement.setInt(6, user.getUserGrade().getMaxBookValue());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 회원의 정보가 존재하는지 확인하기위한 메서드입니다.
     *
     * @param userId 유저의 아이디 기입
     * @return Optional 로 감싸진 User 의 정보가 반환됩니다.
     */
    public Optional<UserInfoDTO> findByUserId(String userId) {
        UserInfoDTO userInfoDTO = null;

        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String userID = resultSet.getString(1);
                String userPhone = resultSet.getString(2);
                UserStatus userStatus = UserStatus.valueOf(resultSet.getString(3));
                UserGrade userGrade = UserGrade.valueOf(resultSet.getString(4));
                int maxBook = resultSet.getInt(5);
                Date serviceStopDate = resultSet.getDate(6);

                userInfoDTO = new UserInfoDTO(userID, userPhone, userStatus, userGrade,
                    maxBook, serviceStopDate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(userInfoDTO);
    }

}
