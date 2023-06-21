package bit.edu.exam.book.service.impl;

import bit.edu.exam.book.dao.BookCopyDAO;
import bit.edu.exam.book.dao.BookInfoDAO;
import bit.edu.exam.book.dto.BookCopyCreateDTO;
import bit.edu.exam.book.dto.BookInfoCreateDTO;
import bit.edu.exam.book.dto.BookResponseDTO;
import bit.edu.exam.book.service.BookService;
import bit.edu.exam.book.type.BookStatus;
import bit.edu.exam.user.exception.BookInfoAlreayCreatedException;
import bit.edu.exam.user.exception.BookNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @since 1.0
 */
public class BookServiceImpl implements BookService {
    private final BookCopyDAO bookCopyDAO;
    private final BookInfoDAO bookInfoDAO;

    public BookServiceImpl(BookCopyDAO bookCopyDAO, BookInfoDAO bookInfoDAO) {
        this.bookCopyDAO = bookCopyDAO;
        this.bookInfoDAO = bookInfoDAO;
    }

    /**
     * BookInfo 를 생성한 메서드
     *
     * @param bookInfoDTO the book info dto
     */
    public void addBookInfo(BookInfoCreateDTO bookInfoDTO) {
        if (bookInfoDAO.findByBookInfoByIsbn(bookInfoDTO.getIsbn()).isPresent()) {
            throw new BookInfoAlreayCreatedException();
        }
        bookInfoDAO.createBookInfo(bookInfoDTO);
    }

    /**
     * BookInfo 와 BookCopy  둘다 생성하는로직
     *
     * @param bookInfoDTO   the book info dto
     * @param copyCreateDTO the copy create dto
     */
    public void addBookInfoAndBookCopy(BookInfoCreateDTO bookInfoDTO, BookCopyCreateDTO copyCreateDTO) {
        if (bookInfoDAO.findByBookInfoByIsbn(bookInfoDTO.getIsbn()).isPresent()) {
            throw new BookInfoAlreayCreatedException();
        }
        bookInfoDAO.createBookInfo(bookInfoDTO);
        bookCopyDAO.createBookCopy(copyCreateDTO);
    }

    /**
     * 상태값 손상으로 변경
     *
     * @param isbn the isbn
     */
    public void modifyBookCopyStatusDamage(String isbn) {
        bookInfoDAO.findByBookInfoByIsbn(isbn)
            .orElseThrow(BookNotFoundException::new);
        bookCopyDAO.modifyBookInfoStatus(BookStatus.DAMAGE, isbn);

    }

    /**
     * 상태값 복구로 변경
     *
     * @param isbn the isbn
     */
    public void modifyBookCopyStatusRecover(String isbn) {
        bookInfoDAO.findByBookInfoByIsbn(isbn)
            .orElseThrow(BookNotFoundException::new);
        bookCopyDAO.modifyBookInfoStatus(BookStatus.RECOVER, isbn);
    }

    /**
     * 책의 정보들 전부출력
     *
     * @return the books
     */
    public List<BookResponseDTO> getBooks() {
        return bookCopyDAO.findBooks();
    }

    /**
     * 책의 상태정보를 입력받아 출력
     *
     * @param status the status
     * @return the books by status
     */
    public List<BookResponseDTO> getBooksByStatus(String status) {
        BookStatus bookStatus = Arrays.stream(BookStatus.values())
            .filter(value -> Objects.equals(value.getValue(), status))
            .findFirst()
            .orElse(BookStatus.BASIC);

        return bookCopyDAO.findByStatus(bookStatus);
    }
}
