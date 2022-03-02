package exceptions;

public class NotOwnersException extends RuntimeException{
    public NotOwnersException(String message){
        super(message);
    }
}
