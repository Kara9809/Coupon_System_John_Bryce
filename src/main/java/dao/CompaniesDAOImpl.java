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
import lombok.Getter;

public class CompaniesDAOImpl implements CompaniesDAO {

    @Getter
    private static final CompaniesDAO instance = new CompaniesDAOImpl();

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
        JDBCUtils.runQuery(query, params);
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
        String query = "SELECT * FROM project_coupons.companies WHERE id = ?;";
        Company company = null;
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            company = ConvertUtils.objectToCompany((Map<String, Object>) obj);
        }
        return company;

    }

    @Override
    public boolean isExist(Integer id) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.companies WHERE id = ?) AS result;";
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
    public boolean isExistByEmailOrName(String email, String name) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.companies WHERE email = ? OR name = ?) AS result;";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, name);

        boolean result = false;

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            result = ConvertUtils.objectToBoolean((Map<String, Object>) obj);
        }

        return result;
    }

    @Override
    public boolean isExistByName(String name) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.companies WHERE name = ?) AS result;";
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, name);

        boolean result = false;

        List<?> list = JDBCUtils.runQueryWithResult(query, params);

        for (Object obj : list) {
            result = ConvertUtils.objectToBoolean((Map<String, Object>) obj);
        }

        return result;
    }

    @Override
    public boolean isExistByEmail(String email) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.companies WHERE email = ?) AS result;";
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
    public boolean isExistByEmailAndPassword(String email, String password) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.companies WHERE email = ? AND password = ?) AS result;";
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
    public boolean isOtherExistByEmail(int id, String email) throws SQLException {
        String query = "SELECT EXISTS (SELECT * FROM project_coupons.companies WHERE email = ? AND id != ?) AS result;";
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
    public int getCompanyIdByEmail(String email) throws SQLException {
        String query = "SELECT id AS result FROM project_coupons.companies WHERE email = ?";
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
