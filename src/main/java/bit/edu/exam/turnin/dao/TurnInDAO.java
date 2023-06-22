package bit.edu.exam.turnin.dao;

import bit.edu.exam.turnin.dto.BookTurnInDTO;
import bit.edu.exam.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnInDAO {
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    // 반납하기 (update)
    public void turnIn(Integer bookSeq, String userId) {
        connection = ConnectionManager.getConnection();
        String sql = "update book_use_status\n" +
                "set return_date = now()\n" +
                "where book_seq = ? and user_id = ?";
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, bookSeq);
            statement.setString(2, userId);
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // 반납예정목록 (select)
    public List<BookTurnInDTO> noReturnDate(String userId) {
        connection = ConnectionManager.getConnection();
        List<BookTurnInDTO> list = new ArrayList<>();

        String sql = "SELECT bc.book_seq, bu.user_id, bi.book_title, bi.book_author, bu.borrow_start, bu.borrow_end, bu.return_date\n" +
                "FROM book_copy bc\n" +
                "JOIN book_info bi\n" +
                "on bc.book_isbn = bi.book_isbn\n" +
                "JOIN book_use_status bu\n" +
                "on bc.book_seq = bu.book_seq\n" +
                "WHERE bu.user_id = 'user1'\n" +
                "and bu.return_date is null\n" +
                "and bu.borrow_end >= now();";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            resultSet = statement.executeQuery();

            while (resultSet.next()){

                list.add(new BookTurnInDTO(resultSet.getInt("bookSeq"),
                        resultSet.getString("userId"),
                        resultSet.getString("bookTitle"),
                        resultSet.getString("bookAuthor"),
                        resultSet.getDate("borrowStart"),
                        resultSet.getDate("borrowEnd"),
                        resultSet.getDate("returnDate")));
            }

            resultSet.close();
            statement.close();
            connection.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    // 반납완료목록
    public List<BookTurnInDTO> returnBook(String userId) {
        connection = ConnectionManager.getConnection();
        List<BookTurnInDTO> list = new ArrayList<>();

        String sql = "select bc.book_seq, bu.user_id, bi.book_title, bi.book_author, bu.borrow_start, bu.borrow_end, bu.return_date\n" +
                "from book_copy bc \n" +
                "join book_info bi\n" +
                "\ton bc.book_isbn = bi.book_isbn\n" +
                "join book_use_status bu\n" +
                "\ton bc.book_seq = bu.book_seq\n" +
                "where bu.user_id = ?\n" +
                "\tand bu.return_date is not null;";
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            resultSet = statement.executeQuery();

            while (resultSet.next()){
                int bookSeq = resultSet.getInt(1);
                String userID = resultSet.getString(2);
                String bookTitle = resultSet.getString(3);
                String bookAuthor = resultSet.getString(4);
                Date borrowStart = resultSet.getDate(5);
                Date borrowEnd = resultSet.getDate(6);
                Date returnDate = resultSet.getDate(7);

                list.add(new BookTurnInDTO(bookSeq,userID,bookTitle,bookAuthor,borrowStart,borrowEnd,returnDate));
            }

            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // 미반납도서
    public List<BookTurnInDTO> noReturnBook(String userId) {
        connection = ConnectionManager.getConnection();
        List<BookTurnInDTO> list = new ArrayList<>();

        String sql = "select bc.book_seq, bu.user_id, bi.book_title, bi.book_author, bu.borrow_start, bu.borrow_end, bu.return_date\n" +
                "from book_copy bc \n" +
                "join book_info bi\n" +
                "\ton bc.book_isbn = bi.book_isbn\n" +
                "join book_use_status bu\n" +
                "\ton bc.book_seq = bu.book_seq\n" +
                "where user_id = ? \n" +
                "and bu.return_date is null \n" +
                "and bu.borrow_end < now();";

        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, userId);
            statement.executeQuery();

            while (resultSet.next()){

                list.add(new BookTurnInDTO(resultSet.getInt("bookSeq"),
                        resultSet.getString("userId"),
                        resultSet.getString("bookTitle"),
                        resultSet.getString("bookAuthor"),
                        resultSet.getDate("borrowStart"),
                        resultSet.getDate("borrowEnd"),
                        resultSet.getDate("returnDate")));
            }



            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }


}


