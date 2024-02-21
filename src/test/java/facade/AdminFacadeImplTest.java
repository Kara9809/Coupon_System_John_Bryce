package facade;

import dao.*;
import entity.Company;
import entity.Customer;
import exception.CouponSystemException;
import exception.ErrorMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AdminFacadeImplTest {

    private static final CompaniesDAO companiesDAO = CompaniesDAOImpl.getInstance();
    private static final CouponsDAO couponsDAO = CouponsDAOImpl.getInstance();
    private static final CustomersDAO customersDAO = CustomersDAOImpl.getInstance();

    private static final AdminFacade adminFacade = AdminFacadeImpl.getInstance();


    @Test
    void loginTest() {
        //try to log in
        try {
            assertTrue(AdminFacadeImpl.getInstance().login("admin@admin.com", "admin"));
        } catch (CouponSystemException e) {
            System.out.println(e.getMessage());
        }
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

            Company companyTestFailNameExists = new Company(0, companyTestSuccess.getName(), "emailNotExists@gmail.com", "1234", null);


            CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
                adminFacade.addCompany(companyTestFailNameExists);
            });

            assertEquals(ErrorMessage.COMPANY_NAME_EXIST.getMessage(), thrown1.getMessage());


            Company companyTestFailEmailExists = new Company(0, "nameNotExists", companyTestSuccess.getEmail(), "1234", null);

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
        // Create an instance of the Company class
        try {
            Company companyTestSuccess = companiesDAO.getCompanyByEmail("email10@gmail.com");
            companyTestSuccess.setPassword("123456789");
            adminFacade.updateCompany(companyTestSuccess.getId(), companyTestSuccess);

            assertEquals(companyTestSuccess.getPassword(), companiesDAO.getSingle(companyTestSuccess.getId()).getPassword());
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }
        try {
            Company companyTestFailIdUpdate = companiesDAO.getCompanyByEmail("email10@gmail.com");
            companyTestFailIdUpdate.setId(20);
            CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
                adminFacade.updateCompany(companyTestFailIdUpdate.getId(), companyTestFailIdUpdate);
            });
            assertEquals(ErrorMessage.COMPANY_UPDATE_ID.getMessage(), thrown1.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Company companyTestFailNameUpdate = companiesDAO.getCompanyByEmail("email10@gmail.com");
            companyTestFailNameUpdate.setName("test123");
            CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
                adminFacade.updateCompany(companyTestFailNameUpdate.getId(), companyTestFailNameUpdate);
            });
            assertEquals(ErrorMessage.COMPANY_UPDATE_NAME.getMessage(), thrown1.getMessage());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            Company companyTestFailIdUpdate = companiesDAO.getCompanyByEmail("email10@gmail.com");
            companyTestFailIdUpdate.setEmail("email4@gmail.com");
            CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
                adminFacade.updateCompany(companyTestFailIdUpdate.getId(), companyTestFailIdUpdate);
            });
            assertEquals(ErrorMessage.COMPANY_EMAIL_EXIST.getMessage(), thrown1.getMessage());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void deleteCompanyTest() {
        // Create a new company
        Company companyToAdd = new Company(0, "nameTest2", "emailTest2@gmail.com", "1234", null);

        // Add the new company
        try {
            adminFacade.addCompany(companyToAdd);
        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
        }
        try {
            int companyId = companiesDAO.getCompanyIdByEmail("emailTest2@gmail.com");
            adminFacade.deleteCompany(companyId);
            Company companyTestSuccess = companiesDAO.getSingle(companyId);
            assertNull(companyTestSuccess);
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }

        CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
            adminFacade.deleteCompany(999);
        });

        assertEquals(ErrorMessage.NOT_EXIST_COMPANY.getMessage(), thrown1.getMessage());
    }

    @Test
    void getAllCompaniesTest() {
        try {
            assertTrue(adminFacade.getAllCompanies().size() > 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getOneCompanyTest() {
        try {
            assertNotNull(adminFacade.getOneCompany(2));
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }

        CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
            adminFacade.getOneCompany(999);
        });

        assertEquals(ErrorMessage.NOT_EXIST_COMPANY.getMessage(), thrown1.getMessage());


    }

    @Test
    void addCustomerTest() {
        // Create a new customer
        Customer customerTestSuccess = new Customer(0, "customerNameTest", "customerLastNameTest", "customerEmailTest@gmail.com", "1234", null);

        // Add the new customer
        try {
            adminFacade.addCustomer(customerTestSuccess);
        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            assertTrue(customersDAO.isExistByEmail(customerTestSuccess.getEmail()));

            Customer customerTestFailEmailExists = new Customer(0, "nameNotExists", "lastNameNotExist", customerTestSuccess.getEmail(), "1234", null);

            CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
                adminFacade.addCustomer(customerTestFailEmailExists);
            });

            assertEquals(ErrorMessage.CUSTOMER_EMAIL_EXIST.getMessage(), thrown1.getMessage());


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    void updateCustomerTest() {
        // Create an instance of the Customer class
        try {
            Customer customerTestSuccess = customersDAO.getCustomerByEmail("email4@gmail.com");
            customerTestSuccess.setPassword("123456789");
            adminFacade.updateCustomer(customerTestSuccess.getId(), customerTestSuccess);

            assertEquals(customerTestSuccess.getPassword(), customersDAO.getSingle(customerTestSuccess.getId()).getPassword());

        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }

        try {
            Customer customerTestFailIdUpdate = customersDAO.getCustomerByEmail("email4@gmail.com");
            customerTestFailIdUpdate.setId(20);
            CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
                adminFacade.updateCustomer(customerTestFailIdUpdate.getId(), customerTestFailIdUpdate);
            });
            assertEquals(ErrorMessage.CUSTOMER_UPDATE_ID.getMessage(), thrown1.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteCustomerTest() {
        // Create a new customer
        Customer customerToAdd = new Customer(0, "customerNameTest2", "testLastName2", "customerEmailTest2@gmail.com", "1234", null);

        // Add the new customer
        try {
            adminFacade.addCustomer(customerToAdd);
        } catch (SQLException | CouponSystemException e) {
            System.out.println(e.getMessage());
        }

        try {
            int customerId = customersDAO.getCustomerIdByEmail("customerEmailTest2@gmail.com");
            adminFacade.deleteCustomer(customerId);
            Customer customerTestSuccess = customersDAO.getSingle(customerId);
            assertNull(customerTestSuccess);
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }

        CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
            adminFacade.deleteCustomer(999);
        });

        assertEquals(ErrorMessage.NOT_EXIST_CUSTOMER.getMessage(), thrown1.getMessage());
    }

    @Test
    void getAllCustomersTest() {
        try {
            assertTrue(adminFacade.getAllCustomers().size() > 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getOneCustomersTest() {
        try {
            assertNotNull(adminFacade.getOneCustomer(2));
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }

        CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
            adminFacade.getOneCustomer(999);
        });

        assertEquals(ErrorMessage.NOT_EXIST_CUSTOMER.getMessage(), thrown1.getMessage());
    }
}