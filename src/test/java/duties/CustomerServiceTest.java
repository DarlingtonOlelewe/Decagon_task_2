package duties;

import enums.Gender;
import enums.Qualification;
import enums.Role;
import exceptions.OutOfStockException;
import exceptions.ProductCategoryDoesNotExist;
import model.Applicant;
import model.Customer;
import model.Staff;
import model.Store;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CustomerServiceTest {
    Customer Joy;
    CustomerService customerService;
    Staff manager;
    ManagerialServices managerialServices;
    Store happyGoods;
    CashierService cashierService;
    Applicant cashierApplicant;
    ApplicationService applicationService;
    @Before
    public void setUp() throws Exception {

        cashierApplicant = new Applicant("John", "James",Gender.MALE,"john@yahoo.com", Role.CASHIER, Qualification.BSC);
        Joy = new Customer("Joy", "Ibezim", Gender.FEMALE, "ibezim@gmail.com");
        customerService = new CustomerService();
        manager = new Staff("Darlington", "Olelewe", Gender.MALE, "darlington.olelewe@decagon.com", Role.MANAGER);
        happyGoods = new Store("Happy_Goods",manager);
        managerialServices = new ManagerialServices();
        cashierService = new CashierService();
        managerialServices.addProductsToStore(manager,happyGoods);
        applicationService = new ApplicationService();


        applicationService.apply(cashierApplicant,happyGoods);
        managerialServices.hireFromList(happyGoods,manager);
    }

    @Test
    public void shouldIncreaseTheCartUponAddingToCartBasedOnDifferentName() {
        assertEquals(0,Joy.getCartMap().size());
        customerService.addToCart(Joy, "cake", 5, happyGoods);

        assertEquals(1,Joy.getCartMap().size());
        customerService.addToCart(Joy, "cake", 5, happyGoods);
        assertEquals(1,Joy.getCartMap().size());
        customerService.addToCart(Joy, "Pringles", 5, happyGoods);
        assertEquals(2,Joy.getCartMap().size());

    }

    @Test
    public void shouldReturnTheTotalPriceOfGoodsInCart() {
        customerService.addToCart(Joy, "cake", 5, happyGoods);
        customerService.addToCart(Joy, "Pringles", 5, happyGoods);
        customerService.addToCart(Joy, "cake", 5, happyGoods);

        assertEquals(57500, (int)customerService.getCartPrice(Joy, happyGoods));
    }

    @Test
    public void shouldRemoveAllProductSerialNumbersAndUnitFromCart() {
        customerService.addToCart(Joy, "cake", 5, happyGoods);
        customerService.addToCart(Joy, "Pringles", 5, happyGoods);
        customerService.addToCart(Joy, "cake", 5, happyGoods);

        int cartSize = customerService.clearCart(Joy).size();

        assertEquals(0, cartSize);

    }

    @Test
    public void shouldGiveDetailsInTheCart() {
        customerService.addToCart(Joy, "cake", 5, happyGoods);
        customerService.addToCart(Joy, "Pringles", 5, happyGoods);
        customerService.addToCart(Joy, "cake", 5, happyGoods);
        assertEquals(2, customerService.viewCart(Joy).size());


        customerService.clearCart(Joy);
        assertEquals(0, customerService.viewCart(Joy).size());


    }

    @Test
    public void shouldReturnProductsOfACertainCategoryIfItExists(){

        assertEquals(4,customerService.viewProductsByCategory(Joy,happyGoods,"Accesories").size());
    }

    @Test
    public void shouldThrowProductCategoryDoesNotExistException(){
        assertThrows(ProductCategoryDoesNotExist.class, () -> customerService.viewProductsByCategory(Joy,happyGoods,"FootWare"));
    }

    @Test
    public void shouldThrowOutOfStockForAFinishedProduct() throws IOException {
        Staff cashier = happyGoods.getStaffList().get(1);
        customerService.addToCart(Joy,"Cake",15,happyGoods);

        cashierService.sellProduct(Joy,cashier,happyGoods,76000);


        assertThrows(OutOfStockException.class, ()-> customerService.addToCart(Joy,"Cake",2,happyGoods));


    }


}