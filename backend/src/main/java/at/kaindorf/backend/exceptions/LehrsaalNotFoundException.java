package at.kaindorf.backend.exceptions;

public class LehrsaalNotFoundException extends RuntimeException {
    public LehrsaalNotFoundException(Long id) {
        super("Lehrsaal mit ID " + id + " wurde nicht gefunden.");
    }
}
