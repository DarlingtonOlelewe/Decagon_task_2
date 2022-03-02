package exceptions;

public class NotEmployeeAtStore extends RuntimeException{
    public NotEmployeeAtStore(String message){
        super(message);
    }
}
