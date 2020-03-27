package com.hello.dao;

import com.hello.exception.DBException;
import com.hello.model.Role;

import java.util.Set;

public interface RoleDao {
    Set<Role> getAllRoles() throws DBException;

    void addRole(Role role) throws DBException;

    Role getRoleWithName(String name) throws DBException;
}
