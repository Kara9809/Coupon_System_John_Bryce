package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.ConvertUtils;
import database.JDBCUtils;
import entity.Category;

public class CategoryDAOImpl implements CategoryDAO {


    @Override
    public void addCategory(String name) throws SQLException {
        final String query = "INSERT INTO project_coupons.categories VALUES (0, ?);";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, name);

        JDBCUtils.runQuery(query, params);
    }

    @Override
    public List<Category> getAllCategory() throws SQLException {
        final String query = "SELECT * FROM project_coupons.categories;";

        List<Category> categories = new ArrayList<>();
        List<?> list = JDBCUtils.runQueryWithResult(query);
        for (Object obj : list) {
            Category category = ConvertUtils.objectToCategory((Map<String, Object>) obj);
            categories.add(category);
        }
        return categories;
    }
}