package duties;

import enums.Qualification;
import enums.Role;
import exceptions.IllegalHandlerException;
import exceptions.IncorrectEmailException;
import model.Applicant;
import model.Product;
import model.Staff;
import model.Store;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class ManagerialServices {
    public void hireFromList(Store store, Staff staff) throws IllegalHandlerException, IncorrectEmailException {
        if(!staff.getRole().equals(Role.MANAGER))throw new IllegalHandlerException("You have to be a staff");
        if(!staff.getWorksAt().equals(store.getStoreName()))throw new IllegalHandlerException("You are a manager but not for this Store");

        for(int i = 0; i < store.getApplicantList().size(); i++){
            if(store.getApplicantList().get(i).getRole().equals(Role.CASHIER) &&
                    (store.getApplicantList().get(i).getQualification().equals(Qualification.BSC) ||
                            store.getApplicantList().get(i).getQualification().equals(Qualification.OND)) &&
                    store.getStaffList().size() < 4 ){

                convertToStaff(store.getApplicantList().get(i), store);
                store.getApplicantList().remove(i);

            }

        }

    }
    public void convertToStaff(Applicant applicant, Store store) throws IncorrectEmailException {
        Staff staff = new Staff(applicant.getFirstName(),
                applicant.getLastName(),
                applicant.getGender(),
                applicant.getEmail(),
                applicant.getRole());
        staff.setWorksAt(store.getStoreName());
        store.getStaffList().add(staff);
    }

    public void addProductsToStore(Staff staff, Store store) throws IllegalHandlerException, IOException {
        if(!staff.getRole().equals(Role.MANAGER))throw new IllegalHandlerException("This function is a Managerial function");
        if(!staff.getWorksAt().equals(store.getStoreName()))throw new IllegalHandlerException("You are a manager but not for this Store");


        stockStore(store);

    }

    private void stockStore(Store store) throws IOException {
        String filepath = "src/main/resources/excelProduct/products.xlsx";
        /*Also known as workbook*/
        XSSFWorkbook ledger = new XSSFWorkbook(filepath);
        /*Also known as worksheet*/
        XSSFSheet productPage = ledger.getSheetAt(0);

        for (int i = 1; i <= productPage.getLastRowNum(); i++) {
            /*Also known as row*/
            XSSFRow singleProduct = productPage.getRow(i);
            store.addingProducts(new Product(singleProduct.getCell(1).getStringCellValue(),
                                            singleProduct.getCell(2).getStringCellValue(),
                                            singleProduct.getCell(3).getNumericCellValue(),
                                        (int) singleProduct.getCell(4).getNumericCellValue(),
                                        (int) singleProduct.getCell(0).getNumericCellValue()));

        }
    }


}
