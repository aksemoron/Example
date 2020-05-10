package service;

import util.DBHelper;
import dao.UserJdbcDao;
import model.User;

import java.util.List;

public class UserService implements ServiceInterface<User> {

    UserJdbcDao userJdbcDao = new UserJdbcDao(DBHelper.getDBHelper().getConnection());


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
