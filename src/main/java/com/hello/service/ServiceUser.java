package com.hello.service;

import com.hello.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceUser {

    List<User> getAllUserService();

    User getUserByIdService(long id);

    User getUserWithNameService(String name);

    void updateUserService(User user);

    boolean addUserService(User user);

    void deleteUserById(long id);

}
