package facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import database.ConvertUtils;
import database.JDBCUtils;
import entity.Company;
import entity.Customer;
import exception.CouponSystemException;
import exception.ErrorMessage;

import static java.util.Arrays.stream;

public class AdminFacadeImpl extends ClientFacade implements AdminFacade {

    @Override
    public boolean login(String email, String password) {
        return "admin@admin.com".equals(email) && "admin".equals(password);
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException, SQLException {
        if (companiesDAO.isExistByName(company.getName())) {
            throw new CouponSystemException(ErrorMessage.COMPANY_NAME_EXIST);
        }
        if (companiesDAO.isExistByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrorMessage.COMPANY_EMAIL_EXIST);
        }
        companiesDAO.add(company);
    }

    @Override //to check
    public void updateCompany(int companyId, Company company) throws SQLException, CouponSystemException {
        if (companiesDAO.isExist(company.getId())) {
            throw new CouponSystemException(ErrorMessage.COMPANY_UPDATE_ID);
        }
        if (companiesDAO.isExistByName(company.getName())) {
            throw new CouponSystemException(ErrorMessage.COMPANY_UPDATE_NAME);
        }
        companiesDAO.update(companyId, company);
    }

    @Override // to check
    public void deleteCompany(int companyId) throws SQLException {
        companiesDAO.delete(companyId); //delete the company by id
        couponsDAO.deleteAllCouponByCompaniesId(companyId); //delete all coupons
        customersDAO.delete(companyId); //delete the history of purchases of this company by customers
    }

    @Override // not sure, to check
    public List<Company> getAllCompanies() throws SQLException {
        return new ArrayList<>(companiesDAO.getAll());
    }

    @Override
    public Company getOneCompany(int companyId) throws SQLException, CouponSystemException {
        return companiesDAO.getSingle(companyId);
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException, CouponSystemException {
        if (customersDAO.isExistByEmail(customer.getEmail())) {
            throw new CouponSystemException(ErrorMessage.CUSTOMER_EMAIL_EXIST);
        }
        customersDAO.add(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws SQLException, CouponSystemException {
        if (customersDAO.isExist(customer.getId())) {
            throw new CouponSystemException(ErrorMessage.COMPANY_UPDATE_ID);
        }
        customersDAO.update(customerId, customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        customersDAO.delete(customerId); //delete a customer by id
        couponsDAO.deleteAllCouponPurchaseByCustomerId(customerId); //delete all coupons of this customer
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return new ArrayList<>(customersDAO.getAll());
    }

    @Override
    public Customer getOneCustomers(int customerId) throws SQLException {
        return customersDAO.getSingle(customerId);
    }

}
