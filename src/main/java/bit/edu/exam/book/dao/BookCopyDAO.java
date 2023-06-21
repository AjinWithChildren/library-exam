package bit.edu.exam.book.dao;

import bit.edu.exam.book.dto.BookCopyCreateDTO;
import bit.edu.exam.book.dto.BookResponseDTO;
import bit.edu.exam.book.type.BookStatus;
import bit.edu.exam.util.ConnectionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * BookCopy 를 접근하기위한 DAO 입니다.
 *
 * @author : 유호철
 * @since 1.0
 */
public class BookCopyDAO {
    private static final String INSERT_BOOK_COPY = "insert into book_copy values (null,?,?,?);";
    private static final String UPDATE_BOOK_COPY_STATUS = "update book_copy set book_status = ? where book_isbn = ?";
    private static final String SELECT_BOOK = "select distinct bc.book_isbn, book_title, book_author, book_position, book_status from book_info inner join book_copy bc on book_info.book_isbn = bc.book_isbn";
    private static final String SELECT_BOOK_BY_STATUS = "select distinct bc.book_isbn, book_title, book_author, book_position, book_status from book_info inner join book_copy bc on book_info.book_isbn = bc.book_isbn where book_status = ?";

    public void createBookCopy(BookCopyCreateDTO copyCreateDTO) {
        try {
            Connection connection = ConnectionManager.getConnection();

            PreparedStatement statement = connection.prepareStatement(INSERT_BOOK_COPY);
            statement.setString(1, copyCreateDTO.getPosition());
            statement.setString(2, copyCreateDTO.getStatus().getValue());
            statement.setString(3, copyCreateDTO.getPosition());
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void modifyBookInfoStatus(BookStatus status, String isbn) {
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK_COPY_STATUS);
            statement.setString(1, status.getValue());
            statement.setString(1, isbn);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<BookResponseDTO> findBooks() {
        List<BookResponseDTO> list = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BOOK);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String isbn = resultSet.getString("bc.book_isbn");
                String title = resultSet.getString("book_title");
                String author = resultSet.getString("book_author");
                String position = resultSet.getString("book_position");
                String status = resultSet.getString("book_status");

                list.add(new BookResponseDTO(isbn, title, author, position, status));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public List<BookResponseDTO> findByStatus(BookStatus bookStatus) {
        List<BookResponseDTO> list = new ArrayList<>();
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_BY_STATUS);
            statement.setString(1, bookStatus.getValue());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String isbn = resultSet.getString("bc.book_isbn");
                String title = resultSet.getString("book_title");
                String author = resultSet.getString("book_author");
                String position = resultSet.getString("book_position");
                String status = resultSet.getString("book_status");

                list.add(new BookResponseDTO(isbn, title, author, position, status));
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
