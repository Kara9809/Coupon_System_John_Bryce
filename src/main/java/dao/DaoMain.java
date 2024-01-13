package dao;

import java.sql.SQLException;
import java.util.List;

public interface DaoMain <T, ID>{

    void add(T t) throws SQLException;

    void update(ID id, T t) throws SQLException; //trows Exception
    void delete(ID id) throws SQLException;
    List<T> getAll() throws SQLException; //trows Exception
    T getSingle(ID id) throws SQLException; //trows Exception
    boolean isExist(ID id) throws SQLException;
}

