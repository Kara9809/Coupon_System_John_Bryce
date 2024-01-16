package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import entity.CATEGORY;
import entity.Coupon;

public class CouponsDAOImpl implements CouponsDAO {

    // Step 1 for singleton class
    private static final CouponsDAOImpl instance = new CouponsDAOImpl();

    // Step 2 for singleton class
    public static CouponsDAOImpl getInstance() {
        return instance;
    }

    // Step 3 for singleton class
    private CouponsDAOImpl() {
    }

    @Override
    public void add(Coupon t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public void update(Integer id, Coupon t) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<Coupon> getAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public Coupon getSingle(Integer id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSingle'");
    }

    @Override
    public boolean isExist(Integer id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExist'");
    }

    @Override
    public void addCouponPurchase(int customerId, int couponId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCouponPurchase'");
    }

    @Override
    public void deleteAllCouponPurchaseByCustomerId(int customerId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllCouponPurchaseByCustomerId'");
    }

    @Override
    public void deleteAllCouponPurchaseByCouponId(int couponId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllCouponPurchaseByCouponId'");
    }

    @Override
    public void deleteAllCouponByCompaniesId(int companyId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAllCouponByCompaniesId'");
    }

    @Override
    public boolean isExistByTitleAndCompanyId(String title, int companyId, int couponId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistByTitleAndCompanyId'");
    }

    @Override
    public boolean isExistByTitleAndCompanyId(String title, int companyId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistByTitleAndCompanyId'");
    }

    @Override
    public boolean isExistByCustomerIdAndCouponId(int customerId, int couponId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistByCustomerIdAndCouponId'");
    }

    @Override
    public List<Coupon> getAllByCompanyId(int companyId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllByCompanyId'");
    }

    @Override
    public List<Coupon> getAllByMaxPrice(int companyId, double price) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllByMaxPrice'");
    }

    @Override
    public List<Coupon> getCouponByCategory(int companyId, CATEGORY category) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCouponByCategory'");
    }

    @Override
    public List<Coupon> getAllCouponByCustomerId(int customerId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCouponByCustomerId'");
    }

    @Override
    public List<Coupon> getAllCouponByCustomerIdAndCategory(int customerId, CATEGORY category) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCouponByCustomerIdAndCategory'");
    }

    @Override
    public List<Coupon> getAllCouponByCustomerIdAndMaxPrice(int customerId, double price) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCouponByCustomerIdAndMaxPrice'");
    }

    @Override
    public boolean isExistByCouponIdAndCompanyId(int couponId, int companyId) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistByCouponIdAndCompanyId'");
    }

    @Override
    public List<Coupon> getAllCouponsExpired(Date date) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllCouponsExpired'");
    }

    @Override
    public void deleteALLExpiredCoupon() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteALLExpiredCoupon'");
    }

}
