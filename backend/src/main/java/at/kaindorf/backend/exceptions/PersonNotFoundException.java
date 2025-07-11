package at.kaindorf.backend.exceptions;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(Long id) {
        super("Person mit ID " + id + " wurde nicht gefunden.");
    }
}
