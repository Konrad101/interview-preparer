package pl.interviewhelpers.interviewpreparer.service;

import org.springframework.stereotype.Component;
import pl.interviewhelpers.interviewpreparer.controller.dto.user.UserRequest;
import pl.interviewhelpers.interviewpreparer.controller.dto.user.UserResponse;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;

@Component
public class UserMapper {
    public UserResponse map(User user) {
        return UserResponse
                .builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .build();
    }

    public User map(UserRequest userRequest) {
        return User
                .builder()
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .build();
    }
}
