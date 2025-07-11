package at.kaindorf.backend.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("User mit ID " + id + " wurde nicht gefunden.");
    }
}
