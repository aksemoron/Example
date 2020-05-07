package service;

import util.ConnectionToBase;
import dao.UserDao;
import model.User;

import java.util.List;

public class UserService implements ServiceInterface<User> {
    boolean startServer = true;

    UserDao userDao = new UserDao(ConnectionToBase.getConnection());

    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    public void createTable() {
        if (startServer) {
            userDao.createTable();
            startServer = false;
        }
    }

    @Override
    public Boolean add(String name) {
        return userDao.add(name);
    }

    @Override
    public boolean deleteById(String id) {
        return userDao.deleteById(id);
    }

    @Override
    public User getById(String id){
        return userDao.getById(id);
    }

    @Override
    public boolean modifyById(Long id, User user) {
        return userDao.modifyUserById(user, id);
    }
}
