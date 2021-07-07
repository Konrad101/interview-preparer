package pl.interviewhelpers.interviewpreparer.repository;

import pl.interviewhelpers.interviewpreparer.repository.entity.User;

public interface UserRepository {
    boolean addUser(User newUser);
    boolean changePhoneNumber(String username, String newNumber);
    boolean changeEmail(String username, String newEmail);
    User getUserByUsername(String username);
}
