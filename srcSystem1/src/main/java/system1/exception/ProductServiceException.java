package system1.exception;

public class ProductServiceException extends RuntimeException {

    public ProductServiceException(String message, Throwable th) {
        super(message, th);
    }

    public ProductServiceException(String message) {
        super(message);
    }
}
