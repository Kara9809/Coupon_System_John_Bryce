package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Customer;

public class CustomersDAOImpl implements CustomersDAO{

  @Override
  public void add(Customer t) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'add'");
  }

  @Override
  public void update(Integer id, Customer t) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(Integer id) throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  @Override
  public List<Customer> getAll() throws SQLException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAll'");
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
