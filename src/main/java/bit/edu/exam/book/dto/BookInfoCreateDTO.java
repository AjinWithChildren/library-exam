package bit.edu.exam.book.dto;

import java.time.LocalDate;

/**
 * BookInfo 를 생성하위한 DTO 입니다.
 *
 * @author : 유호철
 * @since 1.0
 */
public class BookInfoCreateDTO {
    private String isbn;
    private String title;
    private String author;
    private LocalDate publishedDate;

    public BookInfoCreateDTO(String isbn, String title,
                             String author, LocalDate publishedDate) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }
}
