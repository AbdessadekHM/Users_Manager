package com.main.user_management_demo.dao;

import com.main.user_management_demo.models.User;

import java.util.List;

public interface IUserDAO {
    public List<User> getAllUsers();
    public List<User> getUsersByJobId(int jobId);
    public User getUserById(int id);
    public void updateUser(User user);
    public void addUser(User user);
    public void deleteUser(int id);

}
