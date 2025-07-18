package at.kaindorf.backend.exceptions;

public class TrainingdateNotFoundException extends RuntimeException {
    public TrainingdateNotFoundException(Long id) {
        super("Schulungstermin mit ID " + id + " wurde nicht gefunden.");
    }
}
