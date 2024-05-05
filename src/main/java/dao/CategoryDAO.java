package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Category;


public interface CategoryDAO {
    void addCategory(String name) throws SQLException;
    List<Category> getAllCategory() throws SQLException;
}
