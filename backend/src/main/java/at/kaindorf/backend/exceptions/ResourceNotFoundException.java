package at.kaindorf.backend.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super("Ressource mit ID " + id + " wurde nicht gefunden.");
    }
}
