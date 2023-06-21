package bit.edu.exam.book.type;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @since 1.0
 */
public enum BookStatus {
    DAMAGE("손상"),
    RECOVER("복구"),
    BASIC("기본");
    final String value;
    BookStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
