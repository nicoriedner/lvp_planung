package at.kaindorf.backend.exceptions;

public class CompetenceNotFoundException extends RuntimeException {
    public CompetenceNotFoundException(Long id) {
        super("Kompetenz mit ID " + id + " wurde nicht gefunden.");
    }
}
