package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class UserHibernateDAO implements UserDAO<User>{

    private Session session;


    public UserHibernateDAO(Session session){
        this.session=session;
    }


    @Override
    public boolean add(User user) {
        Transaction transaction =session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteById(User user) {
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean modifyUserById(User user, Long id) {
       Transaction transaction = session.beginTransaction();
       session.update(user);
       transaction.commit();
       return true;
    }

    @Override
    public List<User> getAll() {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User");
        List<User> users = query.list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public User getById(User user) {
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("FROM User WHERE id = :id");
        query.setParameter("id", user.getId());
        List<User> cars =query.list();
        transaction.commit();
        session.close();
        return cars.get(0);
    }
}
