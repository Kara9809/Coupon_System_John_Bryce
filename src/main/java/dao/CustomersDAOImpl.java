package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.ConvertUtils;
import database.JDBCUtils;
import entity.Customer;
import lombok.Getter;

public class CustomersDAOImpl implements CustomersDAO {
    @Getter
    private static final CustomersDAO instance = new CustomersDAOImpl();

    private CustomersDAOImpl() {
    }

    @Override
    public void add(Customer customer) throws SQLException {
        String query = "INSERT INTO project_coupons.customers VALUES (0, ?, ?, ?, ?);";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public void update(Integer id, Customer customer) throws SQLException {
        String query = "UPDATE project_coupons.customers SET first_name = ?, last_name - ?, email= ?, password= ? WHERE id = ?;";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());
        params.put(5, customer.getId());

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM project_coupons.customers WHERE id = ?;";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        String query = "SELECT * FROM project_coupons.customers;";

        List<Customer> customers = new ArrayList<>();
        List<?> list = JDBCUtils.runQueryWithResult(query);
        for (Object obj : list) {
            Customer customer = ConvertUtils.objectToCustomer((Map<String, Object>) obj);
            customers.add(customer);
        }
        return customers;
    }

    @Override
    public Customer getSingle(Integer id) throws SQLException {
        String query = "SELECT * FROM project_coupons.customers WHERE id = ?;";
        Customer customer = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            customer = ConvertUtils.objectToCustomer((Map<String, Object>) obj);
        }
        return customer;
    }

    @Override
    public boolean isExist(Integer id) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.customers WHERE id = ?) AS result;";
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
    public boolean isExistByEmailAndPassword(String email, String password) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.customers WHERE email = ? AND password = ?) AS result;";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);

        boolean result = false;

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            result = ConvertUtils.objectToBoolean((Map<String, Object>) obj);
        }

        return result;
    }

    @Override
    public boolean isExistByEmail(String email) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.customers WHERE email = ?) AS result;";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);

        boolean result = false;

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            result = ConvertUtils.objectToBoolean((Map<String, Object>) obj);
        }

        return result;
    }

    @Override
    public boolean isOtherExistByEmail(int id, String email) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.customers WHERE email = ? AND id != ?) AS result;";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, id);

        boolean result = false;

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            result = ConvertUtils.objectToBoolean((Map<String, Object>) obj);
        }

        return result;
    }

    @Override
    public Customer getCustomerByEmail(String email) throws SQLException {
        String query = "SELECT * FROM project_coupons.customers WHERE email = ?;";
        Customer customer = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            customer = ConvertUtils.objectToCustomer((Map<String, Object>) obj);
        }
        return customer;
    }

    @Override
    public int getCustomerIdByEmail(String email) throws SQLException {
        String query = "SELECT id AS result FROM project_coupons.customers WHERE email = ?";
        int id = 0;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            id = ConvertUtils.objectToInt((Map<String, Object>) obj);
        }
        return id;
    }
}
