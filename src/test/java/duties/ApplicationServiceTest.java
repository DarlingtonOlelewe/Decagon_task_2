package duties;

import enums.Gender;
import enums.Qualification;
import enums.Role;
import exceptions.AlreadyAppliedException;
import exceptions.NotOwnersException;
import model.Applicant;
import model.Staff;
import model.Store;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationServiceTest {
    Store happyGoods;
    Staff manager;
    Applicant ebuka;
    ApplicationService applicationService;
    @Before
    public void setUp(){
        manager = new Staff("Darlington", "Olelewe", Gender.MALE, "darlington.olelewe@decagon.com", Role.MANAGER);
        happyGoods = new Store("Happy_Goods",manager);
        ebuka = new Applicant("Emeka", "Ibori", Gender.MALE, "emeka@yahoo.com", Role.CASHIER, Qualification.OND);
        applicationService = new ApplicationService();
    }


    @Test
    public void shouldThrowNotOwnersExceptionWhenInstantiatingStoreWithCashier(){
        Staff adebayor = new Staff("Adebayo", "Olelewe", Gender.MALE, "adebayo.olelewe@decagon.com", Role.CASHIER);

        assertThrows(NotOwnersException.class, () -> new Store("Best_Buy", adebayor));
    }


    @Test
    public void shouldIncreaseTheStaffSizeForEachSuccessfulApplication() {
        applicationService.apply(ebuka,happyGoods);
        assertEquals(1, happyGoods.getApplicantList().size());
        Applicant peter = new Applicant("Peter", "Ebong", Gender.MALE, "peter@yahoo.com", Role.CASHIER, Qualification.OND);

        applicationService.apply(peter,happyGoods);
        assertEquals(2,happyGoods.getApplicantList().size());
    }



    @Test
    public void shouldGothroughApplicationListAndCheckForEachCorrespondingEmailAndFirstNameAndThrowException() {
        Applicant peter = new Applicant("Peter", "Ebong", Gender.MALE, "peter@yahoo.com", Role.CASHIER, Qualification.OND);
        applicationService.apply(peter,happyGoods);
        Applicant paul = new Applicant("Peter", "Ebere", Gender.MALE, "peter@yahoo.com", Role.MANAGER, Qualification.OND);

        assertThrows(AlreadyAppliedException.class, () -> applicationService.apply(paul,happyGoods));
    }
}