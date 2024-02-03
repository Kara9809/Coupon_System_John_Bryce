package facade;

import java.sql.SQLException;
import java.util.List;

import entity.CATEGORY;
import entity.Coupon;
import entity.Customer;
import exception.CouponSystemException;

public interface CustomerFacade {

    boolean login(String email, String password) throws SQLException, CouponSystemException;

    void purchaseCoupon(int customerId, int couponId) throws CouponSystemException, SQLException;

    List<Coupon> getCustomerCoupon(int customerId) throws SQLException, CouponSystemException;

    List<Coupon> getCustomerCouponByCategory(int customerId, CATEGORY category) throws SQLException, CouponSystemException;

    List<Coupon> getCustomerCouponByMaxPrice(int customerId, double maxPrice) throws SQLException, CouponSystemException;

    Customer getCustomerDetails(int customerId) throws SQLException, CouponSystemException;
}
