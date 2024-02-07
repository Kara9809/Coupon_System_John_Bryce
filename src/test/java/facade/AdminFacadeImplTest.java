package facade;

import dao.*;
import entity.Company;
import exception.CouponSystemException;
import exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AdminFacadeImplTest {

    private static final CompaniesDAO companiesDAO = CompaniesDAOImpl.getInstance();
    private static final CouponsDAO couponsDAO = CouponsDAOImpl.getInstance();
    private static final CustomersDAO customersDAO = CustomersDAOImpl.getInstance();

    private static final AdminFacade adminFacade = AdminFacadeImpl.getInstance();



    @Test
    void login() {

    }

    @Test
    void addCompanyTest() {
        // Create a new company
        Company companyTestSuccess = new Company(0, "nameTest", "emailTest@gmail.com", "1234", null);

        // Add the new company
        try {
            adminFacade.addCompany(companyTestSuccess);
        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            assertTrue(companiesDAO.isExistByEmail(companyTestSuccess.getEmail()));

            Company companyTestFailNameExists = new Company(0, companyTestSuccess.getName(), "emailNotExsists@gmail.com", "1234", null);


            CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
                adminFacade.addCompany(companyTestFailNameExists);
            });

            assertEquals(ErrorMessage.COMPANY_NAME_EXIST.getMessage(), thrown1.getMessage());


            Company companyTestFailEmailExists = new Company(0, "nameNotExsists", companyTestSuccess.getEmail(), "1234", null);

            CouponSystemException thrown2 = assertThrows(CouponSystemException.class, () -> {
                adminFacade.addCompany(companyTestFailEmailExists);
            });

            assertEquals(ErrorMessage.COMPANY_EMAIL_EXIST.getMessage(), thrown2.getMessage());



        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void updateCompanyTest() {
    }

    @Test
    void deleteCompanyTest() {
    }

    @Test
    void getAllCompaniesTest() {
    }

    @Test
    void getOneCompanyTest() {
    }

    @Test
    void addCustomerTest() {
    }

    @Test
    void updateCustomerTest() {
    }

    @Test
    void deleteCustomerTest() {
    }

    @Test
    void getAllCustomersTest() {
    }

    @Test
    void getOneCustomersTest() {
    }
}