package exceptions;

public class AlreadyAppliedException extends RuntimeException {
    public AlreadyAppliedException(String message){
    super(message);
    }
}
