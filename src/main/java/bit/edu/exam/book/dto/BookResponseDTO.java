package bit.edu.exam.book.dto;

/**
 * 책 반환시에 정보를 담고있는 DTO 입니다.
 *
 * @author : 유호철
 * @since 1.0
 */
public class BookResponseDTO {
    private String isbn;
    private String title;
    private String author;
    private String position;
    private String status;

    public BookResponseDTO(String isbn, String title, String author,
                           String position, String status) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.position = position;
        this.status = status;
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

    public String getPosition() {
        return position;
    }

    public String getStatus() {
        return status;
    }
}
