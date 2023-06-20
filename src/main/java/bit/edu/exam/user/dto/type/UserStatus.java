package bit.edu.exam.user.dto.type;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @since 1.0
 */
public enum UserStatus {
    MEMBER("회원"), STOP("정지");
    private String value;

    UserStatus(String value) {
        this.value = value;
    }
}
