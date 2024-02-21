package facade;

import dao.*;
import entity.Category;
import entity.Coupon;
import exception.CouponSystemException;
import exception.ErrorMessage;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class CompanyFacadeImplTest {
    private static final CompaniesDAO companiesDAO = CompaniesDAOImpl.getInstance();
    private static final CouponsDAO couponsDAO = CouponsDAOImpl.getInstance();
    private static final CustomersDAO customersDAO = CustomersDAOImpl.getInstance();

    private static final AdminFacade adminFacade = AdminFacadeImpl.getInstance();

    @Test
    void loginTest() {
        try {
            assertTrue(CompanyFacadeImpl.getInstance().login("email6@gmail.com", "1234"));
        } catch (CouponSystemException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void addCouponTest() {
        // Create a new coupon
        Coupon couponTestSuccess = new Coupon(0, 5, Category.getRandomCategory(), "titleTest", "descriptionTest", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(2)), 1, 9.90, "imageTest");

        // Add the new coupon
        try {
            couponsDAO.add(couponTestSuccess);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            assertTrue(couponsDAO.isExistByTitleAndCompanyId(couponTestSuccess.getTitle(), couponTestSuccess.getCompanyId()));

            //first fail test - cant add coupon because the company is not exist by ID

            Coupon couponTestFailCompanyIsNotExistById = new Coupon(0, 999, Category.getRandomCategory(), "titleFailTest", "descriptionFailTest", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(2)), 1, 9.90, "imageTestFail");

            CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
                CompanyFacadeImpl.getInstance().addCoupon(couponTestFailCompanyIsNotExistById.getCompanyId(), couponTestFailCompanyIsNotExistById);
            });

            assertEquals(ErrorMessage.NOT_EXIST_COMPANY.getMessage(), thrown1.getMessage());

            //second fail test - cant add coupon because the title exist in this company ID

            Coupon couponTestFailTitleIsExistByCompanyId = new Coupon(0, 5, Category.getRandomCategory(), "titleTest", "descriptionTestFail", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(3)), 1, 9.90, "imageTestFail");

            CouponSystemException thrown2 = assertThrows(CouponSystemException.class, () -> {
                CompanyFacadeImpl.getInstance().addCoupon(couponTestFailTitleIsExistByCompanyId.getCompanyId(), couponTestFailTitleIsExistByCompanyId);
            });

            assertEquals(ErrorMessage.COUPON_TITLE_EXIST.getMessage(), thrown2.getMessage());


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    void updateCouponTest() throws SQLException {
        // Create a new coupon
        Coupon couponTestSuccess = new Coupon(0, 6, Category.getRandomCategory(), "titleTest1", "descriptionTest1", Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusDays(2)), 1, 9.90, "imageTest1");

        // Add the new coupon
        try {
            couponsDAO.add(couponTestSuccess);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            assertTrue(couponsDAO.isExistByTitleAndCompanyId(couponTestSuccess.getTitle(), couponTestSuccess.getCompanyId()));

           Coupon couponToCheck =  couponsDAO.getSingleByTitle(6, "titleTest1");
            couponToCheck.setImage("image 134");
            couponsDAO.update(couponTestSuccess.getId(), couponTestSuccess);

            assertEquals(couponTestSuccess.getImage(), couponsDAO.getSingle(couponToCheck.getId()).getImage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            Coupon couponToCheck =  couponsDAO.getSingleByTitle(6, "titleTest1");

            couponToCheck.setTitle("tittleTest2");
            couponsDAO.update(couponTestSuccess.getId(), couponToCheck);

            assertEquals(couponTestSuccess.getTitle(), couponsDAO.getSingle(couponToCheck.getId()).getTitle());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            Coupon couponTestFailIdUpdate = couponsDAO.getSingleByTitle(6, "titleTest1");
            couponTestFailIdUpdate.setId(999);
            CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
                CompanyFacadeImpl.getInstance().updateCoupon(6, couponTestFailIdUpdate.getId(), couponTestFailIdUpdate);
            });
            assertEquals(ErrorMessage.COUPON_UPDATE_ID.getMessage(), thrown1.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {

            Coupon couponTestFailTitleIsExistByCompanyID = couponsDAO.getSingleByTitle(6, "titleTest1");
            couponTestFailTitleIsExistByCompanyID.setCompanyId(888);
            CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
                CompanyFacadeImpl.getInstance().updateCoupon(888, couponTestFailTitleIsExistByCompanyID.getId(), couponTestFailTitleIsExistByCompanyID);

            });
            assertEquals(ErrorMessage.COUPON_UPDATE_COMP_ID.getMessage(), thrown1.getMessage());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteCouponTest() {
        try {
            List<Coupon> couponsFromDb = couponsDAO.getAllByCompanyId(5);
            CompanyFacadeImpl.getInstance().deleteCoupon(5, couponsFromDb.get(0).getId());
            Coupon couponTestSuccess = CouponsDAOImpl.getInstance().getSingle(couponsFromDb.get(0).getId());
            assertNull(couponTestSuccess);
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }

        CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
            CompanyFacadeImpl.getInstance().deleteCoupon(5, 999);
        });

        assertEquals(ErrorMessage.COUPON_OR_COMPANY_NOT_EXIST.getMessage(), thrown1.getMessage());

    }

    @Test
    void getCompanyCouponsTest() {
        try {
            assertNull(adminFacade.getOneCompany(5).getCoupons());
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }
        CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
            adminFacade.getOneCompany(999);
        });

        assertEquals(ErrorMessage.NOT_EXIST_COMPANY.getMessage(), thrown1.getMessage());

    }

    @Test
    void getCompanyCouponsByCategoryTest() {
        try {
            assertNull(adminFacade.getOneCompany(5).getCoupons());
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }
        CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
            adminFacade.getOneCompany(999);
        });

        assertEquals(ErrorMessage.NOT_EXIST_COMPANY.getMessage(), thrown1.getMessage());


    }

    @Test
    void getCompanyCouponsByMaxPriceTest() {
        try {
            assertNull(adminFacade.getOneCompany(5).getCoupons());
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }
        CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
            adminFacade.getOneCompany(999);
        });

        assertEquals(ErrorMessage.NOT_EXIST_COMPANY.getMessage(), thrown1.getMessage());
    }


    @Test
    void getCompanyDetailsTest() {
        try {
            assertNull(adminFacade.getOneCompany(5).getCoupons());
        } catch (SQLException | CouponSystemException e) {
            throw new RuntimeException(e);
        }
        CouponSystemException thrown1 = assertThrows(CouponSystemException.class, () -> {
            adminFacade.getOneCompany(999);
        });

        assertEquals(ErrorMessage.NOT_EXIST_COMPANY.getMessage(), thrown1.getMessage());
    }
}
