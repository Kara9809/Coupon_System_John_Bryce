package dao;

import java.sql.SQLException;
import java.util.List;

import entity.CATEGORY;


public interface CategoryDAO {
    void addCategory(String name) throws SQLException;
    List<CATEGORY> getAllCategory() throws SQLException;

}
