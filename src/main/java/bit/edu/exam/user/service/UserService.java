package bit.edu.exam.user.service;

import bit.edu.exam.user.dto.UserInsertDTO;

/**
 * 유저가 이용하는 Service 입니다.
 *
 * @author : 유호철
 * @since 1.0
 */
public interface UserService {
    /**
     * 회원가입을 위한 메서드입니다.
     *
     * @param userInsertDTO dto 에 유저의 정보가 기입됩니다.
     */
    void signUp(UserInsertDTO userInsertDTO);
}
