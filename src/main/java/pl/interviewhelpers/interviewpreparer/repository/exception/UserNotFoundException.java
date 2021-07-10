package pl.interviewhelpers.interviewpreparer.repository.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User does not exist.");
    }
}
