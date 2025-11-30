package at.kaindorf.backend.exceptions;

public class ConcreteCourseNotFoundException extends RuntimeException {
    public ConcreteCourseNotFoundException(Long id) {
        super("Schulungstermin mit ID " + id + " wurde nicht gefunden.");
    }
}
