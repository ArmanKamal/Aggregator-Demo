package system2.exception;

public class DiscountServiceException extends RuntimeException {

    public DiscountServiceException(String message, Throwable th) {
        super(message, th);
    }

    public DiscountServiceException(String message) {
        super(message);
    }
}
