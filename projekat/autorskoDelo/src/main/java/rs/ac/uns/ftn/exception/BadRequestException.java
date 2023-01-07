package rs.ac.uns.ftn.exception;

public class BadRequestException extends AppException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, int errorField) {
        super(message, errorField);
    }
}
