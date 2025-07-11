package at.kaindorf.backend.exceptions;

public class LehrgangNotFoundException extends RuntimeException {
    public LehrgangNotFoundException(Long id) {
        super("Lehrgang mit ID " + id + " wurde nicht gefunden.");
    }
}
