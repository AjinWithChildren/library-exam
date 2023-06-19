package bit.edu.exam.user.dto;

import bit.edu.exam.user.dto.type.UserGrade;
import bit.edu.exam.user.dto.type.UserStatus;
import java.util.Date;

/**
 * User의 정보를 받기위한 DTO 입니다.
 *
 * @author : 유호철
 * @since 1.0
 */
public class UserInfoDTO {
    private String userId;
    private String userPhoneNo;
    private UserStatus userStatus;
    private UserGrade userGrade;
    private Integer maxBook;
    private Date serviceStop;

    public UserInfoDTO(String userId, String userPhoneNo,
                       UserStatus userStatus, UserGrade userGrade,
                       Integer maxBook, Date serviceStop) {
        this.userId = userId;
        this.userPhoneNo = userPhoneNo;
        this.userStatus = userStatus;
        this.userGrade = userGrade;
        this.maxBook = maxBook;
        this.serviceStop = serviceStop;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserPhoneNo() {
        return userPhoneNo;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public UserGrade getUserGrade() {
        return userGrade;
    }

    public Integer getMaxBook() {
        return maxBook;
    }

    public Date getServiceStop() {
        return serviceStop;
    }
}
