package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Category;
import entity.Coupon;

public interface CouponsDAO extends DaoMain<Coupon, Integer> {
    Coupon getSingleByTitle(int companyId, String title) throws SQLException;

    void addCouponPurchase(int customerId, int couponId) throws SQLException;

    void deleteAllCouponPurchaseByCustomerId(int customerId) throws SQLException;

    void deleteAllCouponPurchaseByCouponId(int couponId) throws SQLException;

    void deleteAllCouponByCompanyId(int companyId) throws SQLException;

    boolean isExistByTitleAndCompanyId(String title, int companyId) throws SQLException;

    boolean isExistByCustomerIdAndCouponId(int customerId, int couponId) throws SQLException;

    List<Coupon> getAllByCompanyId(int companyId) throws SQLException;

    List<Coupon> getAllByMaxPrice(int companyId, double price) throws SQLException;

    List<Coupon> getCouponByCategory(int companyId, Category category) throws SQLException;

    List<Coupon> getAllCouponByCustomerId(int customerId) throws SQLException;

    List<Coupon> getAllCouponByCustomerIdAndCategory(int customerId, Category category) throws SQLException;

    List<Coupon> getAllCouponByCustomerIdAndMaxPrice(int customerId, double price) throws SQLException;

    boolean isExistByCouponIdAndCompanyId(int couponId, int companyId) throws SQLException;

    List<Coupon> getAllCouponsExpired() throws SQLException;

    void deleteALLExpiredCoupon() throws SQLException;

}
