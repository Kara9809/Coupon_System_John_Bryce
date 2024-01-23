package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.ConvertUtils;
import database.JDBCUtils;
import entity.CATEGORY;
import entity.Company;

public class CategoryDAOImpl implements CategoryDAO {


    @Override
    public void addCategory(String name) throws SQLException {
        final String query = "INSERT INTO project_coupons.categories VALUES (0, ?);";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, name);

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public List<CATEGORY> getAllCategory() throws SQLException {
        final String query = "SELECT * FROM project_coupons.categories;";

        List<CATEGORY> categories = new ArrayList<>();
        List<?> list = JDBCUtils.runQueryWithResult(query);
        for (Object obj : list) {
            CATEGORY category = ConvertUtils.objectToCategory((Map<String, Object>) obj);
            categories.add(category);
        }
        return categories;
    }
}