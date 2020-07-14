package jm.springboot.rest.service;

import jm.springboot.rest.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRole();
    Role getRoleById(Long id);
    Role getRoleByName(String role);
    void addRole(Role role);
    void deleteRole(Role role);
    void updateRole(Role role);
}
