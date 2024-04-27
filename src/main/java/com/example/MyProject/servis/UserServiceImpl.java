package com.example.MyProject.servis;

import com.example.MyProject.dao.UserDao;
import com.example.MyProject.model.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Transactional
    @Override
    public void delete(long id) {
        User user = userDao.getUserById(id);
        if (user != null) {
            userDao.delete(user);
        }
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }
}
