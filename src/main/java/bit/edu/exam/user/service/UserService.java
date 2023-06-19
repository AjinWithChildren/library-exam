package bit.edu.exam.user.service;

import bit.edu.exam.user.dto.UserInsertDTO;

/**
 * 유저가 이용하는 Service 입니다.
 *
 * @author : 유호철
 * @since 1.0
 */
public interface UserService {
    void signUp(UserInsertDTO userInsertDTO);
}
