package at.kaindorf.backend.exceptions;

public class SchulungsterminNotFoundException extends RuntimeException {
    public SchulungsterminNotFoundException(Long id) {
        super("Schulungstermin mit ID " + id + " wurde nicht gefunden.");
    }
}
