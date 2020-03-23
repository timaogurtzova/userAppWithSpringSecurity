package com.hello.dao;

import com.hello.exception.DBException;
import com.hello.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private Session session;

    UserDaoImpl() {
    }

    @Override
    public List<User> getAllUser() throws DBException {
        List<User> users;
        try {
            Query<User> query = session.createQuery("SELECT user FROM User user");
            users = query.list();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
        return users;
    }

    @Override
    public User getUserById(long id) throws DBException {
        User user;
        try {
            user = session.find(User.class, id);
        } catch (HibernateException e) {
            throw new DBException(e);
        }
        return user;
    }

    @Override
    public User getUserWithName(String name) throws DBException {
        User user;
        try {
            Query query = session.createQuery(
                    "SELECT u FROM User u WHERE u.name =:nameUser")
                    .setParameter("nameUser", name)
                    .setMaxResults(1);
            user = (User) query.uniqueResult();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
        return user;
    }

    @Override
    public void updateUser(User newParameterUser) throws DBException {
        try {
            session.update(newParameterUser);
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    @Override
    public void addUser(User user) throws DBException {
        try {
            session.save(user);
        } catch (Throwable e) {
            session.clear();
            throw new DBException(e);
        }
    }

    @Override
    public void deleteUserById(long id) throws DBException {
        try {
            Query query = session.createQuery("DELETE FROM User WHERE id =:param");
            query.setParameter("param", id);
            query.executeUpdate();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }
}