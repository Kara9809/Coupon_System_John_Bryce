package dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.JDBCUtils;
import entity.CATEGORY;

public class CategoryDAOImpl implements CategoryDAO {


    @Override
    public void addCategory(String name) throws SQLException {
        final String INSERT_CATEGORY = "INSERT INTO project_coupons.categories VALUES (0, ?)";

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, name);

        JDBCUtils.runQuery(INSERT_CATEGORY, params);
    }

    @Override
    public List<CATEGORY> getAllCategory() throws SQLException {
        final String GET_ALL_CATEGORY = "SELECT * FROM `project_coupons`.`categories`";

//    TODO IMPLEMENT LOGIC HERE
        return null;
    }

    // @Override
    // public void addCategory(String name) throws SQLException {

    // Map<Integer, Object> params = new HashMap<>();
    // params.put(1, name);

    // JDBCUtils.runQuery(INSERT_CATEGORY,params);

    // }

    // @Override
    // public List<CATEGORY> getAllCategory() throws SQLException {
    // List<CATEGORY> categories = new ArrayList<>();
    // List<?> list = JDBCUtils.runQueryWithResult(GET_ALL_CATEGORY);
    // for (Object obj:list) {
    // CATEGORY category = ConvertUtils.objectToCategory((Map<String, Object>) obj);
    // categories.add(category);
    // }
    // return categories;

    // }

}
