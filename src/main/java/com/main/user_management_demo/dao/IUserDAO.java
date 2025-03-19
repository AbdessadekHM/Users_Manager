package com.main.user_management_demo.dao;

import com.main.user_management_demo.models.User;

import java.util.List;

public interface IUserDAO {
    public List<User> getAllUsers();
    public List<User> getUsersByJobId(int jobId);
    public User getUserById(int id);
    public boolean updateUser(User user);
    public boolean addUser(User user);
    public boolean deleteUser(int id);

}
