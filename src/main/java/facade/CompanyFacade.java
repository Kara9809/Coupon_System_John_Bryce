package facade;

import java.sql.SQLException;
import java.util.List;

import entity.CATEGORY;
import entity.Company;
import entity.Coupon;
import exception.CouponSystemException;

public interface CompanyFacade {

    boolean login(String email, String password) throws CouponSystemException, SQLException;

    void addCoupon(int companyId, Coupon coupon) throws SQLException, CouponSystemException;

    void updateCoupon(int companyId, int couponId, Coupon coupon) throws SQLException, CouponSystemException;

    void deleteCoupon(int companyId, int couponId) throws SQLException, CouponSystemException;

    List<Coupon> getCompanyCoupons(int companyId) throws SQLException, CouponSystemException;

    List<Coupon> getCompanyCouponsByCategory(int companyId, CATEGORY category) throws SQLException, CouponSystemException;

    List<Coupon> getCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws SQLException, CouponSystemException;

    Company getCompanyDetails(int companyId) throws SQLException, CouponSystemException;




}
