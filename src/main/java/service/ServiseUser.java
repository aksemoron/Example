package service;

import dao.UserDAO;
import dao.UserDaoFactory;
import model.User;

import java.util.List;

public class ServiseUser {

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
    

    public boolean add(User user) {
        return userDAO.add(user);
    }


    public boolean deleteById(User user) {
        return userDAO.deleteById(user);
    }


    public boolean modifyUserById(User user, Long id) {
        return userDAO.modifyUserById(user, id);
    }


    public User getById(User user) {
        return (User) userDAO.getById(user);
    }



    public List<User> getAll() {
        return userDAO.getAll();
    }
}
