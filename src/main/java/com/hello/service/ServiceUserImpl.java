package com.hello.service;

import com.hello.dao.UserDao;
import com.hello.exception.DBException;
import com.hello.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Transactional(rollbackFor = DBException.class)
public class ServiceUserImpl implements ServiceUser {

    private final UserDao userDAO;

    @Autowired
    private ServiceUserImpl(UserDao userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUserService() {
        List<User> users = null;
        try {
            users = userDAO.getAllUser();
        } catch (DBException e) {
            //ignore
        }
        return users;
    }

    @Override
    public User getUserByIdService(long id) {
        User user = null;
        try {
            user = userDAO.getUserById(id);
        } catch (DBException e) {
            //ignore
        }
        return user;
    }

    @Override
    public User getUserWithNameService(String name) {
        User user = null;
        try {
            if (name != null) {
                user = userDAO.getUserWithName(name);
                if (user != null) {
                    user.getAuthorities().size(); //LAZY
                }
            }
        } catch (DBException e) {

        }
        return user;
    }

    @Override
    public void updateUserService(User user) {
        try {
            if (user != null) {
                userDAO.updateUser(user);
            }
        } catch (DBException e) {

        }
    }

    @Override
    public boolean addUserService(User user) {
        AtomicBoolean addOrNot = new AtomicBoolean(false);
        try {
            if (user != null) {
                User userInDB = userDAO.getUserWithName(user.getName());
                if (userInDB == null) {
                    userDAO.addUser(user);
                    addOrNot.set(true);
                } else {
                    throw new DBException();
                }
            }
        } catch (DBException e) {
            System.out.println("A user with this name is already registered");
        }
        return addOrNot.get();
    }

    @Override
    public void deleteUserById(long id) {
        try {
            userDAO.deleteUserById(id);
        } catch (DBException e) {

        }
    }

}