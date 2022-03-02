package model;

import duties.ApplicationService;
import duties.CashierService;
import duties.CustomerService;
import duties.ManagerialServices;
import enums.Gender;
import enums.Qualification;
import enums.Role;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {
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
    public void getCartMap() {
        customerService.addToCart(Joy,"Pepsi", 5, happyGoods);
        customerService.addToCart(Joy,"Pepsi", 7, happyGoods);

        assertEquals(1,Joy.getCartMap().size());

    }


    @Test
    public void shouldClearTheCartToZero(){
        customerService.addToCart(Joy,"Pepsi", 5, happyGoods);
        customerService.addToCart(Joy,"Pepsi", 7, happyGoods);

        assertEquals(1,Joy.getCartMap().size());

        customerService.clearCart(Joy);
        assertEquals(0,Joy.getCartMap().size());

    }
}