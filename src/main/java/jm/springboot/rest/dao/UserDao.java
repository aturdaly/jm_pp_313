package jm.springboot.rest.dao;

import jm.springboot.rest.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUser();
    boolean validateUser(String name, String password);
    User getUserById(Long id);
    User getUserByName(String name);
    void addUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
}
