package com.hello.service;

import com.hello.dao.RoleDao;
import com.hello.exception.DBException;
import com.hello.model.Role;
import com.hello.model.RoleType;
import com.hello.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class ServiceRoleImpl implements ServiceRole {

    private final RoleDao roleDao;

    @Autowired
    private ServiceUser serviceUser;

    @Autowired
    private ServiceRoleImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @PostConstruct
    private void init() throws DBException {
        Role admin = new Role(RoleType.ROLE_ADMIN);
        Role user = new Role(RoleType.ROLE_USER);
        roleDao.addRole(admin);
        roleDao.addRole(user);

        String name = "admin";
        String password = "1";
        int age = 100;
        String city = "AdminCity";
        Set<Role> roles = new HashSet<>();
        roles.add(admin);
        roles.add(user);
        User adminUser = new User(name, age, password, city, roles);
        serviceUser.addUserService(adminUser);
    }

    @Override
    public Set<Role> getAllRolesService() {
        Set<Role> roles = null;
        try {
            roles = roleDao.getAllRoles();
        } catch (DBException e) {
            //ignore
        }
        return roles;
    }

    @Override
    public Role getRoleWithName(String nameRole) {
        Role role = null;
        try {
            role = roleDao.getRoleWithName(nameRole);
        } catch (DBException e) {

        }
        return role;
    }
}
