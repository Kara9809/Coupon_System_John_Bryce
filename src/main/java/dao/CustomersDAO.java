package dao;

import java.sql.SQLException;

import entity.Customer;

public interface CustomersDAO extends DaoMain<Customer,Integer> {

    boolean isExistByEmailAndPassword(String email, String password) throws SQLException;
    boolean isExistByEmail(String email) throws SQLException;

    boolean isOtherExistByEmail(int idCustomer, String email) throws SQLException;

    Customer getCustomerByEmail(String email) throws SQLException;

    int getCustomerIdByEmail(String email) throws SQLException;
}
