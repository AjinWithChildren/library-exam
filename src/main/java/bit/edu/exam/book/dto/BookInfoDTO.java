package bit.edu.exam.book.dto;

import java.util.Date;

/**
 * BookInfo 정보를 조회할때 쓰이는 DTO 입니다.
 *
 * @author : 유호철
 * @since 1.0
 */
public class BookInfoDTO {
    private String isbn;
    private String title;
    private String author;
    private Date publishedDate;

    public BookInfoDTO(String isbn, String title, String author, Date publishedDate) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
    }
}
