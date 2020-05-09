package service;

import model.User;

import java.util.List;

public interface ServiceInterface<T> {
    Boolean add(T user);
    boolean deleteById(T user);
    User getById(T user);
    boolean modifyById(Long id, T user);
    List<T> getAll();
}
