package dao;


import java.util.List;

public interface DaoInterface<T> {
    Boolean add(String name);

    boolean deleteById(String id);

    Boolean modifyUserById(T user, Long id);

    List<T> getAll();

    T getById(String id);
}
