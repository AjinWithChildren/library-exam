package bit.edu.exam.user.service.impl;

import bit.edu.exam.user.dao.impl.UserDAO;
import bit.edu.exam.user.dto.UserInsertDTO;
import bit.edu.exam.user.exception.UserIdAlreadyUsedException;
import bit.edu.exam.user.service.UserService;

/**
 * UserService 를 위한 구현 클래스.
 *
 * @author : 유호철
 * @since 1.0
 */
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl() {
        this.userDAO = new UserDAO();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void signUp(UserInsertDTO userInsertDTO) {
        userDAO.findByUserId(userInsertDTO.getUserId())
            .orElseThrow(UserIdAlreadyUsedException::new);
        userDAO.insertUser(userInsertDTO);
    }
}
