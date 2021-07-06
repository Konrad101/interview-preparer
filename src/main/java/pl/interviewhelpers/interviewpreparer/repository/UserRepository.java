package pl.interviewhelpers.interviewpreparer.repository;

import org.springframework.stereotype.Component;
import pl.interviewhelpers.interviewpreparer.repository.entity.User;

@Component
public interface UserRepository {
    boolean addUser(User newUser);
    boolean changePhoneNumber(String username, String newNumber);
    boolean changeEmail(String username, String newEmail);
}
