package exceptions;

public class IllegalHandlerException extends RuntimeException {
    public IllegalHandlerException(String message){
        super(message);
    }
}
