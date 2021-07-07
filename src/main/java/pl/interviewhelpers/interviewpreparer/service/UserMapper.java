package pl.interviewhelpers.interviewpreparer.service;

import org.springframework.stereotype.Component;
import pl.interviewhelpers.interviewpreparer.controller.dto.user.UserRequest;
import pl.interviewhelpers.interviewpreparer.controller.dto.user.UserResponse;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;

@Component
public class UserMapper {
    public UserResponse map(User user) {
        return null;
    }

    public User map(UserRequest userRequest) {
        return null;
    }
}
