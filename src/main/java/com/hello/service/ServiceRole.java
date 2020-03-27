package com.hello.service;

import com.hello.model.Role;

import java.util.Set;

public interface ServiceRole {
    Set<Role> getAllRolesService();

    Role getRoleWithName(String nameRole);
}
