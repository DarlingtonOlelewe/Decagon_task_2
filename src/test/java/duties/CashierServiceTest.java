package duties;

import enums.Gender;
import enums.Qualification;
import enums.Role;
import exceptions.IllegalHandlerException;
import exceptions.NotEmployeeAtStore;
import model.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

public class CashierServiceTest {
    ApplicationService applicationService;
    Applicant ebuka;
    Customer Joy;
    CustomerService customerService;
    Staff manager;
    ManagerialServices managerialServices;
    Store happyGoods;
    CashierService cashierService;

    Staff ebukaCashier;
    @Before
    public void setUp() throws Exception {
        Joy = new Customer("Joy", "Ibezim", Gender.FEMALE, "ibezim@gmail.com");
        customerService = new CustomerService();
        manager = new Staff("Darlington", "Olelewe", Gender.MALE, "darlington.olelewe@decagon.com", Role.MANAGER);
        happyGoods = new Store("Happy_Goods",manager);
        managerialServices = new ManagerialServices();

        managerialServices.addProductsToStore(manager,happyGoods);
        ebuka = new Applicant("Emeka", "Ibori", Gender.MALE, "emeka@yahoo.com", Role.CASHIER, Qualification.OND);
        applicationService = new ApplicationService();

        cashierService = new CashierService();

        applicationService.apply(ebuka,happyGoods);
        managerialServices.hireFromList(happyGoods,manager);
        ebukaCashier = happyGoods.getStaffList().get(1);
    }

    @Test
    public void cashiershouldRecieveMoneyAndClearCustomersGoodsInCart() throws IOException {

        customerService.addToCart(Joy,"Pepsi", 30, happyGoods);
        assertEquals(1,Joy.getCartMap().size());


        cashierService.sellProduct(Joy,ebukaCashier,happyGoods,50000);
        assertEquals(0,Joy.getCartMap().size());

        customerService.addToCart(Joy,"Pepsi", 5, happyGoods);
        customerService.addToCart(Joy,"Pepsi", 7, happyGoods);

        assertEquals(1,Joy.getCartMap().size());

        int unit = 0;
        for(Map.Entry<String,Integer> cartEntry: Joy.getCartMap().entrySet()){
            unit += cartEntry.getValue();
        }

        assertEquals(12,unit);


        cashierService.sellProduct(Joy,ebukaCashier,happyGoods,50000);
        assertEquals(0,Joy.getCartMap().size());

    }

    @Test
    public void shouldThrowIllegalHandlerExceptionWhenMangerTriesToSell() throws IOException {
        customerService.addToCart(Joy,"Pepsi", 30, happyGoods);
        assertEquals(1,Joy.getCartMap().size());
        cashierService.sellProduct(Joy,ebukaCashier,happyGoods,50000);
        assertEquals(0,Joy.getCartMap().size());

        customerService.addToCart(Joy,"Pepsi", 5, happyGoods);
        customerService.addToCart(Joy,"Pepsi", 7, happyGoods);

        assertThrows(IllegalHandlerException.class, ()-> cashierService.sellProduct(Joy,manager,happyGoods,5000));

    }
    @Test
    public void shouldThrowNotEmployeeAtStoreWhenAStaffFromDifferentStoreTriesToSell() throws IOException {
        customerService.addToCart(Joy,"Pepsi", 30, happyGoods);
        assertEquals(1,Joy.getCartMap().size());
        cashierService.sellProduct(Joy,ebukaCashier,happyGoods,50000);
        assertEquals(0,Joy.getCartMap().size());

        customerService.addToCart(Joy,"Pepsi", 5, happyGoods);
        customerService.addToCart(Joy,"Pepsi", 7, happyGoods);

        Staff newCashier = new Staff("Eme", "Olayinka", Gender.FEMALE,"olayinka@gmail.com",Role.CASHIER);

        assertThrows(NotEmployeeAtStore.class, ()-> cashierService.sellProduct(Joy,newCashier,happyGoods,5000));

    }

    @Test
    public void shouldTrackChangesInStoresProducts() throws IOException {

        int totalUnitOfProductInStore = 0;

        for (Product singleProductInStore: happyGoods.getProductsInStore()){

            totalUnitOfProductInStore += singleProductInStore.getQuantity();
        }

        customerService.addToCart(Joy,"Pepsi", 30, happyGoods);
        cashierService.sellProduct(Joy,ebukaCashier,happyGoods,50000);
        customerService.addToCart(Joy,"Pepsi", 5, happyGoods);
        customerService.addToCart(Joy,"Pepsi", 7, happyGoods);

        int newUnitOfProductInStore = 0;
        for (Product singleProductInStore: happyGoods.getProductsInStore()){

            newUnitOfProductInStore += singleProductInStore.getQuantity();
        }



        System.out.println("Old Quantity in Store ="+totalUnitOfProductInStore);
        System.out.println("New Quantity in Store ="+newUnitOfProductInStore);


        assertTrue(totalUnitOfProductInStore != newUnitOfProductInStore);


    }

    @Test
    public void getPriceOfGoodsInCart() {
        customerService.addToCart(Joy,"Cake", 3, happyGoods);
        customerService.addToCart(Joy,"Cake", 7, happyGoods);

        assertEquals(50000,(int)customerService.getCartPrice(Joy,happyGoods));

        customerService.addToCart(Joy,"Pepsi", 1, happyGoods);
        assertEquals(50250,(int)customerService.getCartPrice(Joy,happyGoods));

    }

    @Test
    public void shouldGiveSevenAsSerialNumberAndTenAsTotalGoodsBought(){

        customerService.addToCart(Joy,"Cake", 3, happyGoods);
        customerService.addToCart(Joy,"Cake", 7, happyGoods);

        String productName = "";
        int unitBought = 0;

        for(Map.Entry<String,Integer> firstProduct: customerService.viewCart(Joy).entrySet()){
            productName = firstProduct.getKey();
            unitBought = firstProduct.getValue();
        }

        assertEquals("Cake", productName);
        assertEquals(10, unitBought);

    }

}