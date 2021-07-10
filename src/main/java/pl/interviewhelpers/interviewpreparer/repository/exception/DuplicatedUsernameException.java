package pl.interviewhelpers.interviewpreparer.repository.exception;

public class DuplicatedUsernameException extends RuntimeException {
    public DuplicatedUsernameException() {
        super("User with provided username already exist.");
    }
}
