package facade;

import java.sql.SQLException;
import java.util.List;

import dao.CouponsDAO;
import dao.CouponsDAOImpl;
import entity.CATEGORY;
import entity.Company;
import entity.Coupon;
import entity.Customer;
import exception.CouponSystemException;
import exception.ErrorMessage;

public class CompanyFacadeImpl extends ClientFacade implements CompanyFacade {

    @Override
    public boolean login(String email, String password) throws CouponSystemException, SQLException {
        if (!companiesDAO.isExistByEmailAndPassword(email, password)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COMPANY);
        }
        return true;
    }

    @Override
    public void addCoupon(Coupon coupon) throws SQLException, CouponSystemException {
        if (couponsDAO.isExistByTitleAndCompanyId(coupon.getTitle(), coupon.getCompanyId())) {
            throw new CouponSystemException(ErrorMessage.COUPON_TITLE_EXIST);
        }
        couponsDAO.add(coupon);
    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon) throws SQLException, CouponSystemException {
        if (!couponsDAO.isExist(couponId)) {
            throw new CouponSystemException(ErrorMessage.COUPON_UPDATE_ID);
        }
        if (!couponsDAO.isExist(coupon.getCompanyId())) {
            throw new CouponSystemException(ErrorMessage.COUPON_UPDATE_COMP_ID);
        }
        couponsDAO.update(couponId, coupon);
    }

    @Override //I added customer id to this signature of method
    public void deleteCoupon(int couponId, int customerId) throws SQLException, CouponSystemException {
        if (!couponsDAO.isExist(couponId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COUPON);
        }

        //Delete coupon_id from purchase table - version 1
//    for (Coupon coupon : couponsDAO.getAllCouponByCustomerId(customerId)) {
//      couponsDAO.deleteAllCouponPurchaseByCustomerId(coupon.getId());
//    }

        couponsDAO.deleteAllCouponPurchaseByCustomerId(customerId); //v2 delete all coupons purchases by customer id
        couponsDAO.delete(couponId); //delete the coupon by id
    }

    @Override
    public List<Coupon> getCompanyCoupons() throws SQLException, CouponSystemException {
        return couponsDAO.getAllByCompanyId(getCompanyDetails().getId());
    }

    @Override
    public List<Coupon> getCompanyCouponsByCategory(CATEGORY category) throws SQLException, CouponSystemException {
        if (!companiesDAO.isExist(getCompanyDetails().getId())) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COMPANY);
        }
        return couponsDAO.getCouponByCategory(getCompanyDetails().getId(), category); //can't do category.getCategoryId())
    }

    @Override
    public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) throws SQLException, CouponSystemException {
        return couponsDAO.getAllByMaxPrice(getCompanyDetails().getId(), maxPrice);
    }

    @Override
    public Company getCompanyDetails() throws SQLException, CouponSystemException {
        if (!companiesDAO.isExist(getCompanyDetails().getId())) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COMPANY);
        }
        return companiesDAO.getSingle(getCompanyDetails().getId());
    }
}
