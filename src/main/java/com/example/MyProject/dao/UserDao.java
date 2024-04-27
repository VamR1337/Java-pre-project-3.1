package com.example.MyProject.dao;


import com.example.MyProject.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);
    List<User> listUsers();
    void delete(User user);
    User getUserById(long id);
    void update(User user);
}
