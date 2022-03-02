package dutiesInterface;

import exceptions.ProductCategoryDoesNotExist;
import model.Customer;
import model.Product;
import model.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CustomerCashierInterface {

    default double getCartPrice(Customer customer, Store store){
        double priceOfGoods = 0;
        for(Map.Entry<String, Integer> customerEntry: customer.getCartMap().entrySet()){
            for (int i=0; i < store.getProductsInStore().length; i++){
                if(customerEntry.getKey() == store.getProductsInStore()[i].getName()){
                    priceOfGoods += (customerEntry.getValue() * store.getProductsInStore()[i].getPrice());
                }
            }
        }
        return priceOfGoods;
    }

    default List<Product> viewProductsByCategory(Customer customer, Store store, String categoryName){
        List<Product> productsBySearchedCategory = new ArrayList<>();

        int count = 0;
        for(Product productByCategory: store.getProductsInStore()){
            if(productByCategory.getCategory().equalsIgnoreCase(categoryName)){
                System.out.println("Name :"+productByCategory.getName()+" Category : "+productByCategory.getCategory()+" Price: "+productByCategory.getPrice());
                count++;
                productsBySearchedCategory.add(productByCategory);
            }
        }

        if(count == 0)throw new ProductCategoryDoesNotExist("No category named "+categoryName+" exists" );
        return productsBySearchedCategory;
    }
}
