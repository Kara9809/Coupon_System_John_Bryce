package facade;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import entity.Category;
import entity.Coupon;
import entity.Customer;
import exception.CouponSystemException;
import exception.ErrorMessage;
import lombok.Getter;

public class CustomerFacadeImpl extends ClientFacade implements CustomerFacade {

    @Getter
    private static final CustomerFacade instance = new CustomerFacadeImpl();

    private CustomerFacadeImpl() {
    }

    @Override
    public boolean login(String email, String password) throws SQLException, CouponSystemException {
        if (!customersDAO.isExistByEmailAndPassword(email, password)) {
            throw new CouponSystemException(ErrorMessage.CUSTOMER_LOGIN_ERROR);
        }
        return true;
    }

    @Override
    public void purchaseCoupon(int customerId, int couponId) throws CouponSystemException, SQLException {
        Coupon couponFromDatabase = couponsDAO.getSingle(couponId);

        if (couponFromDatabase == null) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COUPON);
        }

        if (!customersDAO.isExist(customerId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_CUSTOMER);
        }

        if (couponsDAO.isExistByCustomerIdAndCouponId(customerId, couponId)) {
            throw new CouponSystemException(ErrorMessage.COUPON_PURCHASED);
        }

        if (couponFromDatabase.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new CouponSystemException(ErrorMessage.COUPON_EXPIRED);
        }

        if (couponFromDatabase.getAmount() <= 0) {
            throw new CouponSystemException(ErrorMessage.COUPON_OUT_OF_STOCK);
        }
        couponFromDatabase.setAmount(couponFromDatabase.getAmount() - 1);
        couponsDAO.update(couponId, couponFromDatabase);
        couponsDAO.addCouponPurchase(customerId, couponId);
    }

    @Override
    public List<Coupon> getCustomerCoupon(int customerId) throws SQLException, CouponSystemException {
        if (!customersDAO.isExist(customerId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_CUSTOMER);
        }

        return couponsDAO.getAllCouponByCustomerId(customerId);
    }

    @Override
    public List<Coupon> getCustomerCouponByCategory(int customerId, Category category) throws SQLException, CouponSystemException {
        if (!customersDAO.isExist(customerId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_CUSTOMER);
        }
        return couponsDAO.getAllCouponByCustomerIdAndCategory(customerId, category);
    }

    @Override
    public List<Coupon> getCustomerCouponByMaxPrice(int customerId, double maxPrice) throws SQLException, CouponSystemException {
        if (!customersDAO.isExist(customerId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_CUSTOMER);
        }
        return couponsDAO.getAllCouponByCustomerIdAndMaxPrice(customerId, maxPrice);
    }

    @Override
    public Customer getCustomerDetails(int customerId) throws SQLException, CouponSystemException {
        if (!customersDAO.isExist(customerId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_CUSTOMER);
        }
        return customersDAO.getSingle(customerId);
    }

}
