package com.hello.dao;

import com.hello.exception.DBException;
import com.hello.model.Role;
import com.hello.model.RoleType;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private Session session;

    RoleDaoImpl() {
    }

    @Override
    public Set<Role> getAllRoles() throws DBException {
        Set<Role> roles;
        try {
            Query<Role> query = session.createQuery("SELECT role FROM Role role");
            roles = new HashSet<>();
            roles.addAll(query.list());
        } catch (HibernateException e) {
            throw new DBException(e);
        }
        return roles;
    }

    @Override
    public void addRole(Role role) throws DBException {
        try {
            session.save(role);
        } catch (Throwable e) {
            session.clear();
            throw new DBException(e);
        }
    }

    @Override
    public Role getRoleWithName(String name) throws DBException {
        Role role;
        try {
            Query query = session.createQuery(
                    "SELECT r FROM Role r WHERE r.roleType =:nameRole")
                    .setParameter("nameRole", RoleType.valueOf(name))
                    .setMaxResults(1);
            role = (Role) query.uniqueResult();
        } catch (HibernateException e) {
            throw new DBException(e);
        }
        return role;
    }
}
