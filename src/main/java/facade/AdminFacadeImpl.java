package facade;

import java.sql.SQLException;
import java.util.List;

import entity.Company;
import entity.Customer;
import exception.CouponSystemException;
import exception.ErrorMessage;

public class AdminFacadeImpl extends ClientFacade implements AdminFacade {

    @Override
    public boolean login(String email, String password) throws CouponSystemException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCompany'");
    }

    @Override
    public void deleteCompany(int companyId) throws SQLException, CouponSystemException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCompany'");
    }

    @Override
    public List<Company> getAllCompanies() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCompanies'");
    }

    @Override
    public Company getOneCompany(int companyId) throws SQLException, CouponSystemException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOneCompany'");
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException, CouponSystemException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCustomer'");
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws SQLException, CouponSystemException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateCustomer'");
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException, CouponSystemException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteCustomer'");
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCustomers'");
    }

    @Override
    public Customer getOneCustomers(int customerId) throws SQLException, CouponSystemException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOneCustomers'");
    }

}
