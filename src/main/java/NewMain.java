import duties.ApplicationService;
import duties.CashierService;
import duties.CustomerService;
import duties.ManagerialServices;
import enums.Gender;
import enums.Qualification;
import enums.Role;
import model.Applicant;
import model.Customer;
import model.Staff;
import model.Store;

import java.io.IOException;
import java.util.Arrays;

public class NewMain {
    public static void main(String[] args) throws IOException {

        Staff manager = new Staff("Mr. chinedu", "Nwoke", Gender.MALE, "nedu@gmail.com", Role.MANAGER);

        Store easyBuy = new Store("Easy Buy", manager);

        ApplicationService applicationService = new ApplicationService();
        Applicant ebuka = new Applicant("Emeka", "ibori", Gender.MALE, "emeka@yahoo.com", Role.CASHIER, Qualification.OND);

        applicationService.apply(ebuka,easyBuy);


        ManagerialServices managerialServices = new ManagerialServices();
        managerialServices.hireFromList(easyBuy,manager);
        Staff ebukaCashier = easyBuy.getStaffList().get(1);

        managerialServices.addProductsToStore(manager, easyBuy);
        System.out.println(Arrays.toString(easyBuy.getProductsInStore()));


        System.out.println(Arrays.toString(easyBuy.getProductsInStore()));
        System.out.println(easyBuy.getProductsInStore().length);

        Customer ibori = new Customer("James", "ibori", Gender.MALE, "james@gmail.com");

        CustomerService customerService = new CustomerService();


        System.out.println("\n\nBathing & Laundry");
        customerService.viewProductsByCategory(ibori,easyBuy,"Bathing & Laundry");

        // there is only 80 Pepsi in store
        customerService.addToCart(ibori, "Pepsi", 60, easyBuy);
//        customerService.addToCart(ibori, "Pepsi", 21, easyBuy);

        CashierService cashierService = new CashierService();
//
        cashierService.sellProduct(ibori,ebukaCashier,easyBuy, 2000000);
        System.out.println(ibori.getCartMap());
        System.out.println(Arrays.toString(easyBuy.getProductsInStore()));

//        cashierService.sellProduct(ibori,ebukaCashier,easyBuy, 500);










    }
}
