package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.ConnectionPool;
import database.ConvertUtils;
import database.JDBCUtils;
import entity.Company;

public class CompaniesDAOImpl implements CompaniesDAO {

    //    STEP 1 FOR SINGELTON CLASS
    private static final CompaniesDAOImpl instance = new CompaniesDAOImpl();

    //    STEP 2 FOR SINGELTON CLASS
    public static CompaniesDAOImpl getInstance() {
        return instance;
    }

    //    STEP 3 FOR SINGELTON CLASS
    private CompaniesDAOImpl() {
    }

    @Override
    public void add(Company company) throws SQLException {
        String query = "INSERT INTO project_coupons.companies VALUES (0, ?, ?, ?);";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public void update(Integer id, Company company) throws SQLException {
        String query = "UPDATE project_coupons.companies SET name = ?, email= ?, password= ? WHERE id = ?;";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        params.put(4, id);
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String query = "DELETE FROM project_coupons.companies WHERE id = ? ;";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public List<Company> getAll() throws SQLException {
        String query = "SELECT * FROM project_coupons.companies;";

        List<Company> companies = new ArrayList<>();
        List<?> list = JDBCUtils.runQueryWithResult(query);
        for (Object obj : list) {
            Company company = ConvertUtils.objectToCompany((Map<String, Object>) obj);
            companies.add(company);
        }
        return companies;
    }

    @Override
    public Company getSingle(Integer id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSingle'");
    }

    @Override
    public boolean isExist(Integer id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExist'");
    }

    @Override
    public boolean isExistByEmailOrName(String email, String name) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistByEmailOrName'");
    }

    @Override
    public boolean isExistByName(String name) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistByName'");
    }

    @Override
    public boolean isExistByEmail(String email) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistByEmail'");
    }

    @Override
    public boolean isExistByEmailAndPassword(String email, String password) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isExistByEmailAndPassword'");
    }

    @Override
    public boolean isOtherExistByEmail(int idCompany, String email) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOtherExistByEmail'");
    }

    @Override
    public int getCompanyIdByEmail(String email) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCompanyIdByEmail'");
    }


}
