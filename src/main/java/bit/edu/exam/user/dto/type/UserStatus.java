package bit.edu.exam.user.dto.type;

/**
 * 설명작성란
 *
 * @author : 유호철
 * @since 1.0
 */
public enum UserStatus {
    M("회원"), S("정지");
    private String value;

    UserStatus(String value) {
        this.value = value;
    }
}
