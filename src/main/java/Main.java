import dao.CompaniesDAO;
import dao.CompaniesDAOImpl;
import dao.CustomersDAO;
import dao.CustomersDAOImpl;
import database.DatabaseManager;
import entity.Company;
import entity.Customer;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseManager.dropAndCreateStrategy();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

//    CompaniesDAO companiesDAO = new CompaniesDAOImpl();
//    Company c = new Company(0, "c1", "c1@gmail.com", "12345", null);
//    try {
//      companiesDAO.add(c);
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }

//        CustomersDAO customersDAO = new CustomersDAOImpl();
//        Customer customer = new Customer(0, "c1", "c12", "customer1@gmail.com", "12345");
//        try {
//            customersDAO.add(customer);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

    }
}
