package at.kaindorf.backend.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String username) {
        super("Username " + username + " existiert bereits.");
    }
}
