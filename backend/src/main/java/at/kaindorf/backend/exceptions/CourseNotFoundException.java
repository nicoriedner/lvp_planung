package at.kaindorf.backend.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id) {
        super("Lehrgang mit ID " + id + " wurde nicht gefunden.");
    }
}
