package com.main.user_management_demo.services;

import com.main.user_management_demo.dao.Impl.JobDAO;
import com.main.user_management_demo.dao.Impl.UserDAO;
import com.main.user_management_demo.models.User;

public class UserService {
    private static UserService instance;
    private final UserDAO userDAO = UserDAO.getInstance();
    private final JobDAO jobDAO = JobDAO.getInstance();


    private UserService(){}


    public static UserService getInstance() {
       if (instance == null) {
           instance = new UserService();
       }
       return instance;
    }


    public boolean checkUserExist(int id){
       User user = userDAO.getUserById(id);
       return user != null;
    }
    public User getUserById(int id){
        User user = userDAO.getUserById(id);
        return user;
    }
    public boolean addUser(User user){
        return userDAO.addUser(user);
    }



}
