package exceptions;

public class ProductCategoryDoesNotExist extends RuntimeException{
    public ProductCategoryDoesNotExist(String message){
        super(message);
    }
}
