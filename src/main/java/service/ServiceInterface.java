package service;

import model.User;

import java.util.List;

public interface ServiceInterface<T> {
    boolean add(User user);

    boolean deleteById(T user);

    boolean modifyUserById(T user, Long id);

    List<T> getAll();

    T getById(User user);
}
