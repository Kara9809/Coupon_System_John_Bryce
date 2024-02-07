package thread;

import dao.CouponsDAO;
import dao.CouponsDAOImpl;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class RemoveExpiredCouponThread extends Thread {


  private CouponsDAO couponsDAO = CouponsDAOImpl.getInstance();

  public RemoveExpiredCouponThread() {
    this.setDaemon(true);
  }

  @Override
  public void run() {
    while(true) {
      try {
        couponsDAO.deleteALLExpiredCoupon();
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      try {
        TimeUnit.HOURS.sleep(24);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
