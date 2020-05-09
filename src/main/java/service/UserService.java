package service;

import util.ConnectionToBase;
import dao.UserJdbcDao;
import model.User;

import java.util.List;

public class UserService implements ServiceInterface<User> {
    boolean startServer = true;

    UserJdbcDao userJdbcDao = new UserJdbcDao(ConnectionToBase.getConnection());

    public List<User> getAllUsers() {
        return userJdbcDao.getAll();
    }

    public void createTable() {
        if (startServer) {
            userJdbcDao.createTable();
            startServer = false;
        }
    }

    @Override
    public Boolean add(User user) {
        return userJdbcDao.add(user);
    }

    @Override
    public boolean deleteById(User user) {
        return userJdbcDao.deleteById(user);
    }

    @Override
    public User getById(User user){
        return userJdbcDao.getById(user);
    }

    @Override
    public boolean modifyById(Long id, User user) {
        return userJdbcDao.modifyUserById(user, id);
    }

    @Override
    public List<User> getAll() {
        return userJdbcDao.getAll();
    }
}
