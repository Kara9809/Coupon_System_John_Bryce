package facade;

import dao.*;
import entity.Category;
import entity.Coupon;
import entity.Customer;
import exception.CouponSystemException;
import exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerFacadeImplTest {
    private static final CouponsDAO couponsDAO = CouponsDAOImpl.getInstance();

    @Test
    void loginTest() {
        try {
            assertTrue(CustomerFacadeImpl.getInstance().login("email6@gmail.com", "1234"));
        } catch (CouponSystemException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void purchaseCouponTest() {
        try {
            Coupon couponFromDatabase = CouponsDAOImpl.getInstance().getSingle(4);
            assertTrue(couponsDAO.isExistByTitleAndCompanyId(couponFromDatabase.getTitle(), couponFromDatabase.getCompanyId()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Coupon couponTestFailCouponIsEmpty = new Coupon(1000, 1, Category.getRandomCategory(), null, null, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), 0, 0.00, null);

        CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
            CustomerFacadeImpl.getInstance().purchaseCoupon(1, couponTestFailCouponIsEmpty.getId());
        });

        assertEquals(ErrorMessage.NOT_EXIST_COUPON.getMessage(), thrown1.getMessage());


    }

    @Test
    void getCustomerCouponTest(){
        try {
            List<Coupon> couponsFromDatabase = CustomerFacadeImpl.getInstance().getCustomerCoupon(1);
            assertEquals(1, couponsFromDatabase.size());
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void getCustomerCouponByCategoryTest(){
        try {
            List<Coupon> couponsFromDatabase = CustomerFacadeImpl.getInstance().getCustomerCouponByCategory(1, Category.ELECTRICITY);
            assertEquals(1, couponsFromDatabase.size());
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getCustomerCouponByMaxPriceTest(){
        try {
            List<Coupon> couponsFromDatabase = CustomerFacadeImpl.getInstance().getCustomerCouponByMaxPrice(1, 1);
            assertEquals(1, couponsFromDatabase.size());
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getCustomerDetailsTest(){
        try {
            Customer customerFromDb = CustomerFacadeImpl.getInstance().getCustomerDetails(1);
            assertNotNull(customerFromDb);
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }
    }




}
