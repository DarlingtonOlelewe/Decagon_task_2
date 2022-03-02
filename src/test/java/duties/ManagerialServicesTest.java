package duties;

import enums.Gender;
import enums.Qualification;
import enums.Role;
import model.Applicant;
import model.Staff;
import model.Store;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ManagerialServicesTest {
    Store happyGoods;
    Staff manager;
    Applicant ebuka;
    ApplicationService applicationService;
    ManagerialServices managerialServices;

    @Before
    public void setUp(){
        manager = new Staff("Darlington", "Olelewe", Gender.MALE, "darlington.olelewe@decagon.com", Role.MANAGER);
        happyGoods = new Store("Happy_Goods",manager);
        ebuka = new Applicant("Emeka", "Ibori", Gender.MALE, "emeka@yahoo.com", Role.CASHIER, Qualification.OND);
        applicationService = new ApplicationService();
        managerialServices = new ManagerialServices();
    }

    @Test
    public void managerShouldHireFromStoreList() {
        applicationService.apply(ebuka, happyGoods);
        assertEquals(1, happyGoods.getApplicantList().size());

        assertEquals(1, happyGoods.getStaffList().size());

        managerialServices.hireFromList(happyGoods, manager);
        assertEquals(0, happyGoods.getApplicantList().size());
        assertEquals(2, happyGoods.getStaffList().size());
    }


    @Test
    public void addProductsToStore() throws IOException {
        managerialServices.addProductsToStore(manager,happyGoods);

        assertEquals(17, happyGoods.getProductsInStore().length);
    }
}