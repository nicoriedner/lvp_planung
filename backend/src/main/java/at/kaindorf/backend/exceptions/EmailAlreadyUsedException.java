package at.kaindorf.backend.exceptions;

public class EmailAlreadyUsedException extends RuntimeException {
    public EmailAlreadyUsedException() {
        super("Email ist bereits vergeben.");
    }
}
