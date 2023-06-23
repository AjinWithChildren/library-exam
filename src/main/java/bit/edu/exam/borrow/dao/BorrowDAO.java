package bit.edu.exam.borrow.dao;

import bit.edu.exam.book.dto.NotBorrowBookDTO;
import bit.edu.exam.borrow.dto.BorrowDTO;
import bit.edu.exam.borrow.dto.UserBookDTO;
import bit.edu.exam.util.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {

    private static final String UPDATE_BOOK_POSITION = "UPDATE book_copy SET book_position = ? WHERE book_seq = ?;";
    private static final String SELECT_BORROW_BOOKS = "select bc.book_seq AS book_no,\n" +
        "       bi.book_title AS book_title,\n" +
        "       bi.book_author AS book_autohr\n" +
        "from book_copy as bc\n" +
        "    left join book_info bi on bi.book_isbn = bc.book_isbn\n" +
        "    left outer join book_use_status bus\n" +
        "    on bc.book_seq = bus.book_seq\n" +
        "where return_date is not null || bus.book_seq is null";

    public boolean userBorrowStateCheck(Connection connection, String userId) {

        String sql = "select service_stop from book_user where user_id = ?";

        LocalDate serviceStopDate = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, userId);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Date resultDate = resultSet.getDate(1);
                if (resultDate == null) {
                    return true;
                }
                serviceStopDate = resultDate.toLocalDate();
            }
//            connection.close();
//            statement.close();
//            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return serviceStopDate.isBefore(LocalDate.now());
    }

    public boolean bookBorrowStateCheck(Connection connection, int bookSeq) {

        String sql = "select book_position from book_copy where book_seq = ?";

        String borrowCheck = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, bookSeq);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                borrowCheck = resultSet.getString(1);
            }
//            connection.close();
//            statement.close();
//            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return borrowCheck.equals("BS-0001");
    }

    public boolean bookBorrow(Connection connection, BorrowDTO borrowDTO) {
        boolean flag = false;

        String sql = "insert into book_use_status values(?, ?, ?, ?, null)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, borrowDTO.getBookSeq());
            statement.setString(2, borrowDTO.getUserId());
            statement.setDate(3, Date.valueOf(borrowDTO.getBorrowStart()));
            statement.setDate(4, Date.valueOf(borrowDTO.getBorrowEnd()));

            if (statement.executeUpdate() == 1) {
                flag = true;
            }
//            connection.close();
//            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return flag;
    }

    public List<UserBookDTO> userBookListByUserId(String userId) {
        Connection connection = ConnectionManager.getConnection();

        List<UserBookDTO> userBookDTOList = new ArrayList<>();

        String sql = "select book_copy.book_seq, book_user.user_id, book_info.book_title, book_info.book_author," +
            " book_use_status.borrow_start, book_use_status.borrow_end, book_use_status.return_date" +
            " from book_info" +
            " join book_copy on book_info.book_isbn = book_copy.book_isbn" +
            " join book_use_status on book_copy.book_seq = book_use_status.book_seq" +
            " join book_user on book_use_status.user_id = book_user.user_id" +
            " where book_user.user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, userId);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int bookSeq = resultSet.getInt(1);
                String userID = resultSet.getString(2);
                String bookTitle = resultSet.getString(3);
                String bookAuthor = resultSet.getString(4);
                LocalDate borrowStart = resultSet.getDate(5).toLocalDate();
                LocalDate borrowEnd = resultSet.getDate(6).toLocalDate();

                LocalDate returnDate = null;
                if (resultSet.getDate(7) != null) {
                    returnDate = resultSet.getDate(7).toLocalDate();
                }

                userBookDTOList.add(new UserBookDTO(bookSeq, userID, bookTitle, bookAuthor, borrowStart, borrowEnd, returnDate));
            }
            connection.close();
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBookDTOList;
    }

    public void updateBookPosition(Connection connection, String positionName, int bookSeq) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_POSITION);
            preparedStatement.setString(1, positionName);
            preparedStatement.setInt(2, bookSeq);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public List<NotBorrowBookDTO> findNotBorrowBooks() {
        Connection connection = ConnectionManager.getConnection();
        List<NotBorrowBookDTO> books = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_BORROW_BOOKS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                books.add(new NotBorrowBookDTO(resultSet.getInt("book_no"),
                    resultSet.getString("book_title"),
                    resultSet.getString("book_author")
                ));
            }

            connection.close();
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;

    }
}
