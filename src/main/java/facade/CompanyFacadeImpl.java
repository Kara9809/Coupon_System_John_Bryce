package facade;

import java.sql.SQLException;
import java.util.List;

import entity.CATEGORY;
import entity.Company;
import entity.Coupon;
import exception.CouponSystemException;

public class CompanyFacadeImpl extends ClientFacade implements CompanyFacade{

  @Override
  public boolean login(String email, String password) throws CouponSystemException, SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'login'");
  }

  @Override
  public void addCoupon(Coupon coupon) throws SQLException, CouponSystemException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addCoupon'");
  }

  @Override
  public void updateCoupon(int couponId, Coupon coupon) throws SQLException, CouponSystemException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateCoupon'");
  }

  @Override
  public void deleteCoupon(int couponId) throws SQLException, CouponSystemException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteCoupon'");
  }

  @Override
  public List<Coupon> getCompanyCoupons() throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCompanyCoupons'");
  }

  @Override
  public List<Coupon> getCompanyCouponsByCategory(CATEGORY category) throws SQLException, CouponSystemException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCompanyCouponsByCategory'");
  }

  @Override
  public List<Coupon> getCompanyCouponsByMaxPrice(double maxPrice) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCompanyCouponsByMaxPrice'");
  }

  @Override
  public Company getCompanyDetails() throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCompanyDetails'");
  }

    
}
