package pl.karol.common.application.exception;

public class NotFoundElementRuntimeException extends RuntimeException {

    private static final String MESSAGE = "Not found element %s id: %s";

    public NotFoundElementRuntimeException(Class aClass, String id) {
        super(String.format(MESSAGE, aClass, id));
    }
}
