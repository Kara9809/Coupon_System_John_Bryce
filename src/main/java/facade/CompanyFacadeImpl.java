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
import lombok.Getter;

public class CompanyFacadeImpl extends ClientFacade implements CompanyFacade {

    @Getter
    private static final CompanyFacade instance = new CompanyFacadeImpl();

    private CompanyFacadeImpl() {
    }

    @Override
    public boolean login(String email, String password) throws CouponSystemException, SQLException {
        if (!companiesDAO.isExistByEmailAndPassword(email, password)) {
            throw new CouponSystemException(ErrorMessage.COMPANY_LOGIN_ERROR);
        }
        return true;
    }

    @Override
    public void addCoupon(int companyId, Coupon coupon) throws SQLException, CouponSystemException {
        if (!companiesDAO.isExist(companyId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COMPANY);
        }
        if (couponsDAO.isExistByTitleAndCompanyId(coupon.getTitle(), companyId)) {
            throw new CouponSystemException(ErrorMessage.COUPON_TITLE_EXIST);
        }
        coupon.setCompanyId(companyId);
        couponsDAO.add(coupon);
    }

    @Override
    public void updateCoupon(int companyId, int couponId, Coupon coupon) throws SQLException, CouponSystemException {
        if (!couponsDAO.isExist(couponId)) {
            throw new CouponSystemException(ErrorMessage.COUPON_UPDATE_ID);
        }
        if (!couponsDAO.isExistByCouponIdAndCompanyId(couponId, companyId)) {
            throw new CouponSystemException(ErrorMessage.COUPON_UPDATE_COMP_ID);
        }
        coupon.setCompanyId(companyId);
        couponsDAO.update(couponId, coupon);
    }

    @Override
    public void deleteCoupon(int companyId, int couponId) throws SQLException, CouponSystemException {
        if (!couponsDAO.isExistByCouponIdAndCompanyId(couponId, companyId)) {
            throw new CouponSystemException(ErrorMessage.COUPON_OR_COMPANY_NOT_EXIST);
        }
        couponsDAO.deleteAllCouponPurchaseByCouponId(couponId);
        couponsDAO.delete(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) throws SQLException, CouponSystemException {
        if (!companiesDAO.isExist(companyId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COMPANY);
        }
        return couponsDAO.getAllByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getCompanyCouponsByCategory(int companyId, CATEGORY category) throws SQLException, CouponSystemException {
        if (!companiesDAO.isExist(companyId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COMPANY);
        }
        return couponsDAO.getCouponByCategory(companyId, category);
    }

    @Override
    public List<Coupon> getCompanyCouponsByMaxPrice(int companyId, double maxPrice) throws SQLException, CouponSystemException {
        if (!companiesDAO.isExist(companyId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COMPANY);
        }
        return couponsDAO.getAllByMaxPrice(companyId, maxPrice);
    }

    @Override
    public Company getCompanyDetails(int companyId) throws SQLException, CouponSystemException {
        if (!companiesDAO.isExist(companyId)) {
            throw new CouponSystemException(ErrorMessage.NOT_EXIST_COMPANY);
        }
        return companiesDAO.getSingle(companyId);
    }
}
