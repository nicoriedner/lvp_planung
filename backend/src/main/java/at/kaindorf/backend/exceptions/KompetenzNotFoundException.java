package at.kaindorf.backend.exceptions;

public class KompetenzNotFoundException extends RuntimeException {
    public KompetenzNotFoundException(Long id) {
        super("Kompetenz mit ID " + id + " wurde nicht gefunden.");
    }
}
