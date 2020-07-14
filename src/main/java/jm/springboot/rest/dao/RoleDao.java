package jm.springboot.rest.dao;

import jm.springboot.rest.model.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getAllRole();
    Role getRoleById(Long id);
    Role getRoleByName(String role);
    void addRole(Role role);
    void deleteRole(Role role);
    void updateRole(Role role);
}