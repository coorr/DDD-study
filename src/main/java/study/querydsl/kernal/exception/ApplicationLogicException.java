package study.querydsl.kernal.exception;

public class ApplicationLogicException extends RuntimeException {
    public ApplicationLogicException(String message) {
        super(message);
    }

    public ApplicationLogicException(String message, Throwable cause) {
        super(message, cause);
    }


}
