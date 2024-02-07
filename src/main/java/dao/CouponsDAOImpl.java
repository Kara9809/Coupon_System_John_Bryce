package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.ConvertUtils;
import database.JDBCUtils;
import entity.Category;
import entity.Coupon;
import lombok.Getter;

public class CouponsDAOImpl implements CouponsDAO {

    @Getter
    private static final CouponsDAO instance = new CouponsDAOImpl();

    private CouponsDAOImpl() {
    }

    @Override
    public void add(Coupon coupon) throws SQLException {
        final String query = "INSERT INTO project_coupons.coupons VALUES (0, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCompanyId());
        params.put(2, coupon.getCategory().getCategoryId());
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, coupon.getStartDate());
        params.put(6, coupon.getEndDate());
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public void update(Integer id, Coupon coupon) throws SQLException {
        String query = "UPDATE project_coupons.coupons SET company_id = ?, category_id = ?, title = ?, description = ?, start_date = ?, end_date = ?, amount = ?, price = ?, image = ?, WHERE id = ?;";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, coupon.getCompanyId());
        params.put(2, coupon.getCategory().getCategoryId());
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, coupon.getStartDate());
        params.put(6, coupon.getEndDate());
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM project_coupons.coupons WHERE id = ? ;";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public List<Coupon> getAll() throws SQLException {
        String query = "SELECT * FROM project_coupons.coupons;";

        List<Coupon> coupons = new ArrayList<>();
        List<?> list = JDBCUtils.runQueryWithResult(query);
        for (Object obj : list) {
            Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
            coupons.add(coupon);
        }
        return coupons;
    }

    @Override
    public Coupon getSingle(Integer id) throws SQLException {
        String query = "SELECT * FROM project_coupons.coupons WHERE id = ?;";
        Coupon coupon = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
        }
        return coupon;
    }

    @Override
    public boolean isExist(Integer id) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.coupons WHERE id = ?) AS result;";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        boolean result = false;

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            result = ConvertUtils.objectToBoolean((Map<String, Object>) obj);
        }

        return result;
    }

    @Override
    public void addCouponPurchase(int customerId, int couponId) throws SQLException {
        String query = "INSERT INTO project_coupons.purchases VALUES (?, ?);";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        params.put(2, couponId);

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public void deleteAllCouponPurchaseByCustomerId(int customerId) throws SQLException {
        String query = "DELETE FROM project_coupons.purchases WHERE customer_id = ?;";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public void deleteAllCouponPurchaseByCouponId(int couponId) throws SQLException {
        String query = "DELETE FROM project_coupons.purchases WHERE coupon_id = ?;";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponId);

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public void deleteAllCouponByCompanyId(int companyId) throws SQLException {
        String query = "DELETE FROM project_coupons.coupons WHERE company_id = ? ;";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public boolean isExistByTitleAndCompanyId(String title, int companyId) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.coupons WHERE title = ? AND company_id = ?) AS result;";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, title);
        params.put(2, companyId);

        boolean result = false;

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            result = ConvertUtils.objectToBoolean((Map<String, Object>) obj);
        }

        return result;
    }

    @Override
    public boolean isExistByCustomerIdAndCouponId(int customerId, int couponId) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.purchases WHERE customer_id = ? AND coupon_id = ?) AS result;";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        params.put(2, couponId);

        boolean result = false;

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            result = ConvertUtils.objectToBoolean((Map<String, Object>) obj);
        }

        return result;
    }


    @Override
    public List<Coupon> getAllByCompanyId(int companyId) throws SQLException {
        String query = "SELECT * FROM project_coupons.coupons WHERE company_id = ?;";

        List<Coupon> coupons = new ArrayList<>();

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
            coupons.add(coupon);
        }
        return coupons;
    }

    @Override
    public List<Coupon> getAllByMaxPrice(int companyId, double price) throws SQLException {
        String query = "SELECT * FROM project_coupons.coupons WHERE company_id = ? AND price <= ?;";

        List<Coupon> coupons = new ArrayList<>();

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);
        params.put(2, price);

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
            coupons.add(coupon);
        }

        return coupons;
    }


    @Override
    public List<Coupon> getCouponByCategory(int companyId, Category category) throws SQLException {
        String query = "SELECT * FROM project_coupons.coupons WHERE company_id = ? AND category_id = ? ;";

        List<Coupon> coupons = new ArrayList<>();

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyId);
        params.put(2, category.getCategoryId());

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
            coupons.add(coupon);
        }
        return coupons;

    }

    @Override
    public List<Coupon> getAllCouponByCustomerId(int customerId) throws SQLException {
        String query = "SELECT * FROM project_coupons.coupons " +
                "INNER JOIN project_coupons.purchases " +
                "ON project_coupons.purchases.coupon_id=project_coupons.coupons.id WHERE customer_id = ?;";

        List<Coupon> coupons = new ArrayList<>();

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
            coupons.add(coupon);
        }
        return coupons;
    }

    @Override
    public List<Coupon> getAllCouponByCustomerIdAndCategory(int customerId, Category category) throws SQLException {
        String query = "SELECT * FROM project_coupons.coupons " +
                "INNER JOIN project_coupons.purchases " +
                "ON project_coupons.purchases.coupon_id=project_coupons.coupons.id WHERE customer_id = ? AND category_id = ?;";

        List<Coupon> coupons = new ArrayList<>();

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        params.put(2, category.getCategoryId());

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
            coupons.add(coupon);
        }
        return coupons;
    }

    @Override
    public List<Coupon> getAllCouponByCustomerIdAndMaxPrice(int customerId, double price) throws SQLException {
        String query = "SELECT * FROM project_coupons.coupons " +
                "INNER JOIN project_coupons.purchases " +
                "ON project_coupons.purchases.coupon_id=project_coupons.coupons.id WHERE customer_id = ?" +
                " AND WHERE price <= ?;";

        List<Coupon> coupons = new ArrayList<>();

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerId);
        params.put(2, price);

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
            coupons.add(coupon);
        }
        return coupons;
    }

    @Override
    public boolean isExistByCouponIdAndCompanyId(int couponId, int companyId) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.coupons WHERE id = ? AND company_id = ?) AS result;";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, couponId);
        params.put(2, companyId);

        boolean result = false;

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            result = ConvertUtils.objectToBoolean((Map<String, Object>) obj);
        }

        return result;
    }


    @Override
    public List<Coupon> getAllCouponsExpired() throws SQLException {
        String query = "SELECT * FROM project_coupons.coupons WHERE end_date < CURDATE();";
        List<Coupon> coupons = new ArrayList<>();

        List<?> list = JDBCUtils.runQueryWithResult(query);

        for (Object obj : list) {
            Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
            coupons.add(coupon);
        }
        return coupons;
    }

    @Override
    public void deleteALLExpiredCoupon() throws SQLException {
        String deleteExpiredPurchasedCoupons = "DELETE FROM project_coupons.purchases WHERE coupon_id IN (SELECT id FROM project_coupons.coupons WHERE end_date< CURDATE();)";
        String deleteExpiredCoupons = "DELETE FROM project_coupons.coupons WHERE end_date < CURDATE();";

        JDBCUtils.runQuery(deleteExpiredPurchasedCoupons);
        JDBCUtils.runQuery(deleteExpiredCoupons);
    }

}
