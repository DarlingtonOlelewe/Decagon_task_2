package duties;

import dutiesInterface.CustomerCashierInterface;
import exceptions.OutOfStockException;
import exceptions.ProductCategoryDoesNotExist;
import model.Customer;
import model.Product;
import model.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerService implements CustomerCashierInterface {
    public void addToCart(Customer customer,String productName, int unit, Store store )  {

        for(int i = 0; i < store.getProductsInStore().length; i++ ){


            if(store.getProductsInStore()[i].getName().equalsIgnoreCase(productName) &&
                    store.getProductsInStore()[i].getQuantity() == 0)throw new OutOfStockException(store.getProductsInStore()[i].getName() +" is out of Stock");

            if(store.getProductsInStore()[i].getName().equalsIgnoreCase(productName) &&
                    store.getProductsInStore()[i].getQuantity() < unit)throw new OutOfStockException(store.getProductsInStore()[i].getName() +" is in store but not enough");

            if(store.getProductsInStore()[i].getName().equalsIgnoreCase(productName) &&
                    store.getProductsInStore()[i].getQuantity() >= unit){





                customer.getCartMap().merge(store.getProductsInStore()[i].getName(),
                        unit, Integer::sum );
                if(customer.getCartMap().get(store.getProductsInStore()[i].getName()) > store.getProductsInStore()[i].getQuantity())throw new OutOfStockException(store.getProductsInStore()[i].getName()+ " is not up to this unit in store");




            }
        }
    }


    public Map<String, Integer> clearCart(Customer customer){
        customer.getCartMap().clear();
        return customer.getCartMap();
    }

    public Map<String, Integer> viewCart(Customer customer){
        return customer.getCartMap();
    }



}
