package bit.edu.exam.borrow.dao;

import bit.edu.exam.borrow.dto.BorrowDTO;
import bit.edu.exam.borrow.dto.UserBookDTO;
import bit.edu.exam.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO {

    public boolean userBorrowStateCheck(String userId){
        Connection connection = ConnectionManager.getConnection();

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
            connection.close();
            statement.close();
            resultSet.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return serviceStopDate.isBefore(LocalDate.now());
    }

    public boolean bookBorrowStateCheck(int bookSeq){
        Connection connection = ConnectionManager.getConnection();

        String sql = "select book_position from book_copy where book_seq = ?";

        String borrowCheck = null;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, bookSeq);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                borrowCheck = resultSet.getString(1);
            }
            connection.close();
            statement.close();
            resultSet.close();

        }catch (SQLException e){
            e.printStackTrace();
        }


        return borrowCheck.equals("BS-0001");
    }

    public boolean bookBorrow(BorrowDTO borrowDTO){
        boolean flag = false;

        Connection connection = ConnectionManager.getConnection();

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
            connection.close();
            statement.close();

        }catch (SQLException e){
        e.printStackTrace();
    }
        return flag;
    }

    public List<UserBookDTO> userBookListByUserId(String userId){
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

        }catch (SQLException e){
            e.printStackTrace();
        }
        return userBookDTOList;
    }

}