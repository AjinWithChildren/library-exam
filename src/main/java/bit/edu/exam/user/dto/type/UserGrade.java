package bit.edu.exam.user.dto.type;

/**
 * 사용자의 등급을 정하기위한 ENUM 클래스
 *
 * @author : 유호철
 * @since 1.0
 */
public enum UserGrade {
    GOLD(15), SLIVER(10), BRONZE(5);

    private Integer maxBookValue;

    UserGrade(Integer gradeValue) {
        this.maxBookValue = gradeValue;
    }

    public Integer getMaxBookValue() {
        return maxBookValue;
    }
}
