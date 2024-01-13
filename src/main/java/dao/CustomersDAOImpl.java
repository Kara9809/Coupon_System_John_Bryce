package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.ConvertUtils;
import database.JDBCUtils;
import entity.Company;
import entity.Customer;

public class CustomersDAOImpl implements CustomersDAO {

    @Override
    public void add(Customer customer) throws SQLException {
        String query = "INSERT INTO project_coupons.customers VALUES (0, ?, ?, ?, ?);";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customer.getFirstName());
        params.put(2, customer.getLastName());
        params.put(3, customer.getEmail());
        params.put(4, customer.getPassword());

        JDBCUtils.runQuery(query, params);
//        throw new UnsupportedOperationException("Unimplemented method 'add'");
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
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM project_coupons.customers WHERE id = ? ;";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);

        JDBCUtils.runQuery(query, params);
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSingle'");
    }

    @Override
    public boolean isExist(Integer id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExist'");
    }

    @Override
    public boolean isExistByEmailAndPassword(String email, String password) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistByEmailAndPassword'");
    }

    @Override
    public boolean isExistByEmail(String email) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistByEmail'");
    }

    @Override
    public boolean isOtherExistByEmail(int idCustomer, String email) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOtherExistByEmail'");
    }

    @Override
    public int getCustomerIdByEmail(String email) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCustomerIdByEmail'");
    }

}
