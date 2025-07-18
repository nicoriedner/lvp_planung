package at.kaindorf.backend.exceptions;

public class ClassroomNotFoundException extends RuntimeException {
    public ClassroomNotFoundException(Long id) {
        super("Lehrsaal mit ID " + id + " wurde nicht gefunden.");
    }
}
