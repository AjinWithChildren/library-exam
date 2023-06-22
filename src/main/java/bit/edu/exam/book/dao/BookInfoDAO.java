package bit.edu.exam.book.dao;

import bit.edu.exam.book.dto.BookInfoCreateDTO;
import bit.edu.exam.book.dto.BookInfoDTO;
import bit.edu.exam.book.type.BookStatus;
import bit.edu.exam.util.ConnectionManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * BookInfo 를 접근하기위한 DAO 입니다.
 *
 * @author : 유호철
 * @since 1.0
 */
public class BookInfoDAO {
    private static final String INSERT_BOOK_INFO = "insert into book_info values (?,?,?,?)";
    private static final String SELECT_BOOK_INFO_BY_ISBN = "select * from book_info where book_isbn = ?";


    /**
     * BookInfo 를 생성하는 구문입니다.
     *
     * @param createDTO the create dto
     */
    public void createBookInfo(BookInfoCreateDTO createDTO) {
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_BOOK_INFO);
            statement.setString(1, createDTO.getIsbn());
            statement.setString(2, createDTO.getTitle());
            statement.setString(3, createDTO.getAuthor());
            statement.setDate(4, Date.valueOf(createDTO.getPublishedDate()));
            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * isbn 을 통해 책을 찾는 메서드입니다.
     *
     * @param isbn isbn 이 기입됩니다.
     * @return the optional
     */
    public Optional<BookInfoDTO> findByBookInfoByIsbn(String isbn) {
        BookInfoDTO bookInfoDTO = null;
        try {
            Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_INFO_BY_ISBN);
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                bookInfoDTO = new BookInfoDTO(
                    resultSet.getString("book_isbn"),
                    resultSet.getString("book_title"),
                    resultSet.getString("book_author"),
                    resultSet.getDate("book_published_date"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(bookInfoDTO);
    }

}
