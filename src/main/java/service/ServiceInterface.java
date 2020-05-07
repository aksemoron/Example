package service;

import model.User;

public interface ServiceInterface<T> {
    Boolean add(String name);
    boolean deleteById(String id);
    User getById(String id);
    boolean modifyById(Long id, T user);
}
