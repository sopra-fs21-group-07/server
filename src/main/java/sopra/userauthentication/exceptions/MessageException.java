package sopra.userauthentication.exceptions;

public class MessageException extends RuntimeException {
    public MessageException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public MessageException(String exMessage) {
        super(exMessage);
    }
}
