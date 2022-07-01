package study.querydsl.kernal.exception;

public class NotFoundException extends ApplicationLogicException{
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }


}
