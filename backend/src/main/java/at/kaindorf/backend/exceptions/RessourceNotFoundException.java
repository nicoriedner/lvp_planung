package at.kaindorf.backend.exceptions;

public class RessourceNotFoundException extends RuntimeException {
    public RessourceNotFoundException(Long id) {
        super("Ressource mit ID " + id + " wurde nicht gefunden.");
    }
}
