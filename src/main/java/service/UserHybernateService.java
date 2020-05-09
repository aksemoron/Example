package service;

import dao.UserHibernateDAO;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserHybernateService implements ServiceInterface<User> {

    private SessionFactory sessionFactory;
    
    public UserHybernateService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    @Override
    public Boolean add(User user) {
        return new UserHibernateDAO(sessionFactory.openSession()).add(user);
    }

    @Override
    public boolean deleteById(User user) {
        return new UserHibernateDAO(sessionFactory.openSession()).deleteById(user);
    }

    @Override
    public User getById(User user) {
        return new UserHibernateDAO(sessionFactory.openSession()).getById(user);
    }

    @Override
    public boolean modifyById(Long id, User user) {
        return new UserHibernateDAO(sessionFactory.openSession()).modifyUserById(user, id);
    }

    @Override
    public List<User> getAll() {
        return new UserHibernateDAO(sessionFactory.openSession()).getAll();
    }
}
