package service;

import java.util.List;

public interface UserInterface<T> {
    boolean add(T user);

    boolean deleteById(T user);

    boolean modifyUserById(T user, Long id);

    List<T> getAll();

    T getById(T user);

    T logIN(T user);
}
