package service;

import dao.ConnectionToBase;
import dao.UserDao;
import model.User;

import java.util.List;

public class UserService {
    boolean startServer = true;

    UserDao userDao = new UserDao(ConnectionToBase.getConnection());

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void createTable() {
        if (startServer) {
            userDao.createTable();
            startServer = false;
        }
    }

    public Boolean addUser(String name) {
        return userDao.addUser(name);
    }


    public boolean deleteUserById(String id) {
        return userDao.deleteUserById(id);
    }


    public User getUserById(String id){
        return userDao.getUserById(id);
    }
    public boolean modifyUserById(Long id,User user) {
        return userDao.modifyUserById(user, id);
    }
}
