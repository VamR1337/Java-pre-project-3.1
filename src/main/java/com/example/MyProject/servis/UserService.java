package com.example.MyProject.servis;



import com.example.MyProject.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void delete(long id);
    List<User> listUsers();
    User getUserById(long id);
    void update(User user);
}
