package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;

import java.util.List;

public class ServiseUser implements UserInterface<User> {

    private static ServiseUser serviseUser;

    private static UserDAO userDAO;

    public static ServiseUser getInstance() {
        if (serviseUser == null) {
            serviseUser = new ServiseUser(UserDaoFactory.getUserDao());
        }
        return serviseUser;
    }


    private ServiseUser(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public boolean add(User user) {
        return userDAO.add(user);
    }


    @Override
    public boolean deleteById(User user) {
        return userDAO.deleteById(user);
    }


    @Override
    public boolean modifyUserById(User user, Long id) {
        return userDAO.modifyUserById(user, id);
    }


    @Override
    public User getById(User user) {
        return (User) userDAO.getById(user);
    }


    @Override
    public List<User> getAll() {
        return userDAO.getAll();
    }
}
