package facade;

import java.sql.SQLException;
import java.util.List;

import entity.CATEGORY;
import entity.Coupon;
import entity.Customer;
import exception.CouponSystemException;

public class CustomerFacadeImpl extends ClientFacade implements CustomerFacade{

  @Override
  public boolean login(String email, String password) throws SQLException, CouponSystemException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'login'");
  }

  @Override
  public void purchaseCoupon(Coupon coupon) throws CouponSystemException, SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'purchaseCoupon'");
  }

  @Override
  public List<Coupon> getCustomerCoupon() throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCustomerCoupon'");
  }

  @Override
  public List<Coupon> getCustomerCouponByCategory(CATEGORY category) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCustomerCouponByCategory'");
  }

  @Override
  public List<Coupon> getCustomerCouponByMaxPrice(double maxPrice) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCustomerCouponByMaxPrice'");
  }

  @Override
  public Customer getCustomerDetails() throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCustomerDetails'");
  }

}
