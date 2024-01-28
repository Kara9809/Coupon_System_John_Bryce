package facade;

import java.sql.SQLException;
import java.util.List;

import entity.CATEGORY;
import entity.Coupon;
import entity.Customer;
import exception.CouponSystemException;
import exception.ErrorMessage;

public class CustomerFacadeImpl extends ClientFacade implements CustomerFacade {

    @Override
    public boolean login(String email, String password) throws SQLException, CouponSystemException {
        if (!customersDAO.isExistByEmailAndPassword(email, password)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_CUSTOMER);
        }
        return true;
    }

    @Override //to check
    public void purchaseCoupon(Coupon coupon) throws CouponSystemException, SQLException {
        //לא ניתן לרכוש קופון יותר מפעם אחת
        if (couponsDAO.isExistByCustomerIdAndCouponId(getCustomerDetails().getId(), coupon.getId())) {
            throw new CouponSystemException(ErrorMessage.COUPON_PURCHASED);
        }
        // לא ניתן לרכוש קופון אם הכמות שלו היא 0
        if (!couponsDAO.isExist(coupon.getId())) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COUPON);
        }
        // לא ניתן לרכוש את הקופון אם תאריך התפוגה שלו כבר הגיע.
        if (getCustomerCoupon().stream()
                .anyMatch(couponsDAO.getAllCouponsExpired()::contains)) {
            throw new CouponSystemException(ErrorMessage.COUPON_EXPIRED);
        }
        //רכישת הקופון
        couponsDAO.addCouponPurchase(getCustomerDetails().getId(), coupon.getId());
        //לאחר הרכישה יש להוריד את הכמות במלאי של הקופון ב-1
        couponsDAO.deleteAllCouponPurchaseByCustomerId(getCustomerDetails().getId());
    }

    @Override
    public List<Coupon> getCustomerCoupon() throws SQLException, CouponSystemException {
        return couponsDAO.getAllCouponByCustomerId(getCustomerDetails().getId());
    }

    @Override
    public List<Coupon> getCustomerCouponByCategory(CATEGORY category) throws SQLException, CouponSystemException {
        return couponsDAO.getAllCouponByCustomerIdAndCategory(getCustomerDetails().getId(), category);
    }

    @Override
    public List<Coupon> getCustomerCouponByMaxPrice(double maxPrice) throws SQLException, CouponSystemException {
        return couponsDAO.getAllCouponByCustomerIdAndMaxPrice(getCustomerDetails().getId(), maxPrice);
    }

    @Override
    public Customer getCustomerDetails() throws SQLException, CouponSystemException {
        if (!customersDAO.isExist(getCustomerDetails().getId())) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_CUSTOMER);
        }
        return customersDAO.getSingle(getCustomerDetails().getId());
    }

}
