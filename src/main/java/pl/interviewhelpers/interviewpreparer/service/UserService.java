package pl.interviewhelpers.interviewpreparer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.interviewhelpers.interviewpreparer.controller.dto.user.UserRequest;
import pl.interviewhelpers.interviewpreparer.controller.dto.user.UserResponse;
import pl.interviewhelpers.interviewpreparer.repository.DuplicatedUsernameException;
import pl.interviewhelpers.interviewpreparer.repository.UserRepository;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;
import pl.interviewhelpers.interviewpreparer.repository.hibernate.annotation.HibernateUserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(
            @HibernateUserRepository UserRepository userRepository,
            UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public boolean createUser(UserRequest request) {
        final User user = userMapper.map(request);
        try {
            return userRepository.addUser(user);
        } catch (DuplicatedUsernameException ex) {
            return false;
        }
    }

    public boolean updatePhoneNumber(String username, String phoneNumber) {
        return userRepository.changePhoneNumber(username, phoneNumber);
    }

    public boolean updateEmail(String username, String email) {
        return userRepository.changeEmail(username, email);
    }

    public UserResponse getUserByUsername(String username) {
        final User userByUsername = userRepository.getUserByUsername(username);
        return userByUsername != null ?
                userMapper.map(userByUsername) :
                null;
    }
}
