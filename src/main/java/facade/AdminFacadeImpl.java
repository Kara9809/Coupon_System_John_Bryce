package facade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Company;
import entity.Coupon;
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

    @Override
    public void updateCompany(int companyId, Company company) throws SQLException, CouponSystemException {
        if (!companiesDAO.isExist(companyId)) {
            throw new CouponSystemException(ErrorMessage.COMPANY_UPDATE_ID);
        }
        if (!companiesDAO.isExistByName(company.getName())) {
            throw new CouponSystemException(ErrorMessage.COMPANY_UPDATE_NAME);
        }
        companiesDAO.update(companyId, company);
    }

    @Override
    public void deleteCompany(int companyId) throws SQLException, CouponSystemException {
        if (!companiesDAO.isExist(companyId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COMPANY);
        }

//        Delete company coupons from purchase table
        for (Coupon coupon : couponsDAO.getAllByCompanyId(companyId)) {
            couponsDAO.deleteAllCouponPurchaseByCouponId(coupon.getId());
        }

        couponsDAO.deleteAllCouponByCompanyId(companyId); //delete all coupons
        companiesDAO.delete(companyId); //delete the company by id
    }

    @Override
    public List<Company> getAllCompanies() throws SQLException {
        return companiesDAO.getAll();
    }

    @Override
    public Company getOneCompany(int companyId) throws SQLException, CouponSystemException {
        if (!companiesDAO.isExist(companyId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COMPANY);
        }
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
        if (!customersDAO.isExist(customer.getId())) {
            throw new CouponSystemException(ErrorMessage.CUSTOMER_UPDATE_ID);
        }
        customersDAO.update(customerId, customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException, CouponSystemException {
        if (!customersDAO.isExist(customerId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_CUSTOMER);
        }
        couponsDAO.deleteAllCouponPurchaseByCustomerId(customerId); //delete all coupons of this customer
        customersDAO.delete(customerId); //delete a customer by id
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return customersDAO.getAll();
    }

    @Override
    public Customer getOneCustomers(int customerId) throws SQLException, CouponSystemException {
        if (!customersDAO.isExist(customerId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_CUSTOMER);
        }
        return customersDAO.getSingle(customerId);
    }

}
