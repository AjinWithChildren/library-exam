package bit.edu.exam.user.dto;

import bit.edu.exam.user.dto.type.UserGrade;
import bit.edu.exam.user.dto.type.UserStatus;

/**
 * User 를 생성하기위한 DTO 클래스
 *
 * @author : 유호철
 * @since 1.0
 */
public class UserInsertDTO {
    private String userId;
    private String userPwd;
    private String phoneNo;
    private UserGrade userGrade;
    private UserStatus userStatus;

    public UserInsertDTO(String userId, String userPwd,
                         String phoneNo, UserGrade userStatus,
                         UserStatus userType) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.phoneNo = phoneNo;
        this.userGrade = userStatus;
        this.userStatus = userType;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public UserGrade getUserGrade() {
        return userGrade;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }
}
