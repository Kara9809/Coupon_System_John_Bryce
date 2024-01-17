package facade;

import dao.*;
import exception.CouponSystemException;

import java.sql.SQLException;

public abstract class ClientFacade {

    protected CompaniesDAO companiesDAO = CompaniesDAOImpl.getInstance();
    protected CustomersDAO customersDAO = CustomersDAOImpl.getInstance();
    protected CouponsDAO couponsDAO = CouponsDAOImpl.getInstance();

    public ClientFacade() {

    }

    public abstract boolean login(String email, String password) throws CouponSystemException, SQLException;
}
