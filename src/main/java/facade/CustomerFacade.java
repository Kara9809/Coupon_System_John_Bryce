package facade;

import java.sql.SQLException;
import java.util.List;

import entity.CATEGORY;
import entity.Coupon;
import entity.Customer;
import exception.CouponSystemException;

public interface CustomerFacade {

    boolean login(String email, String password) throws SQLException, CouponSystemException;

    void purchaseCoupon(Coupon coupon) throws CouponSystemException, SQLException;

    List<Coupon> getCustomerCoupon() throws SQLException;

    List<Coupon> getCustomerCouponByCategory(CATEGORY category) throws SQLException;

    List<Coupon> getCustomerCouponByMaxPrice(double maxPrice) throws SQLException;

    Customer getCustomerDetails() throws SQLException;
}
