package facade;

import java.sql.SQLException;
import java.util.List;

import entity.Company;
import entity.Customer;
import exception.CouponSystemException;

public interface AdminFacade {


    boolean login(String email, String password) throws CouponSystemException;

    void addCompany(Company company) throws CouponSystemException, SQLException;

    void updateCompany(int companyId, Company company) throws SQLException, CouponSystemException;

    void deleteCompany(int companyId) throws SQLException, CouponSystemException;

    List<Company> getAllCompanies() throws SQLException;

    Company getOneCompany(int companyId) throws SQLException, CouponSystemException;

    void addCustomer(Customer customer) throws SQLException, CouponSystemException;

    void updateCustomer(int customerId, Customer customer) throws SQLException, CouponSystemException;

    void deleteCustomer(int customerId) throws SQLException, CouponSystemException;

    List<Customer> getAllCustomers() throws SQLException;

    Customer getOneCustomer(int customerId) throws SQLException, CouponSystemException;



}
