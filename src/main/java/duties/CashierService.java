package duties;

import dutiesInterface.CustomerCashierInterface;
import enums.Role;
import exceptions.IllegalHandlerException;
import exceptions.InsufficientFundException;
import exceptions.NotEmployeeAtStore;
import model.Customer;
import model.Product;
import model.Staff;
import model.Store;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

public class CashierService implements CustomerCashierInterface {
    public void sellProduct(Customer customer, Staff staff, Store store, double amount) throws IOException {

        if(staff.getRole().equals(Role.MANAGER)) throw new IllegalHandlerException("This is reserved for cashier");

        if(!store.getStoreName().equals(staff.getWorksAt()))throw new NotEmployeeAtStore("You are a Cashier but not in this store");

        if(getCartPrice(customer, store) <= amount){

             String purchaseDetail = checkOut(store.getProductsInStore(),customer.getCartMap(),getCartPrice(customer,store));
             printReciept(purchaseDetail);
             customer.getCartMap().clear();

        }else throw new InsufficientFundException("Insufficient amount Inputted");

    }

    private String checkOut(Product[] products, Map<String, Integer> cartMap, double cost) {
        String message = "Thanks for patronizing \n";

        for (Map.Entry<String, Integer> cartProducts : cartMap.entrySet()) {
            for (Product singleProductInStore : products){
                if(cartProducts.getKey().equals(singleProductInStore.getName())){

                    singleProductInStore.setQuantity(singleProductInStore.getQuantity() - cartProducts.getValue());
                    message += "name    : "+singleProductInStore.getName() +"\n" +
                               "Category: "+singleProductInStore.getCategory() +"\n" +
                               "Quantity: "+cartProducts.getValue()+"\n" +
                               "Price   : "+singleProductInStore.getPrice()+"\n" +
                               "Cost    : "+singleProductInStore.getPrice()*cartProducts.getValue()+"\n" +
                               "==============================\n";
                }

            }
        }
        message += "Total cost = "+cost;
        
        return message;

    }
    
    private void printReciept(String info) throws IOException {

        String reciept = "src/main/resources/receipts/Receipt"+(new Date().toInstant()+"").substring(15, 23)+"txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(reciept));
        writer.write(info);
        writer.close();
    }

}
