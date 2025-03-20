package com.main.user_management_demo.dao.Impl;

import com.main.user_management_demo.models.Job;
import com.main.user_management_demo.models.User;
import com.main.user_management_demo.services.UserService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {

    private final UserDAO instance = UserDAO.getInstance();
    private final JobDAO jobInstance = JobDAO.getInstance();
    private final UserService userService = UserService.getInstance();

    public Job createJob(String name){
        Job job = new Job();

        job.setName(name);
        job.setSalary("3000");
        return job;
    }
    public User createUser(int id, Job job){
        User user = new User();

        user.setEmail("test@gmail.com");
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setUsername("test");
        user.setJob(job);


        return user;
    }

    void createTestEnv(String name){



        Job job = createJob(name);
      jobInstance.addJob(job);

        User user = createUser(0,job);
        User user1 = createUser(1,job);
        User user2 = createUser(2,job);
        User user3 = createUser(3,job);
        User user4 = createUser(4,job);
        User user5 = createUser(5,job);


        userService.addUser(user);
        userService.addUser(user1);
        userService.addUser(user2);
        userService.addUser(user3);
        userService.addUser(user4);
        userService.addUser(user5);
    }

    @Test
    void getAndAddUser() {
        createTestEnv("test");


        List<User> users = UserDAO.getInstance().getAllUsers();
        users.stream().map(User::getId).forEach(System.out::println);
        assertNotNull(users);
        assertEquals(6,users.size());


    }
    @Test
    void updateAndGetById(){
        createTestEnv("Teachers");
        User user = instance.getUserById(2);
        System.out.println(user.getId());
        user.setFirstName("Abdessadek");
        boolean isSuccess = instance.updateUser(user);
        assertTrue(isSuccess);


    }
    @Test
    void deleteUser(){
        createTestEnv("Teachers");
        User user = instance.getUserById(1);
        boolean isSuccess = instance.deleteUser(user.getId());
        assertTrue(isSuccess);
    }
    @Test
    void getUserById(){
        createTestEnv("Director");
        User user = instance.getUserById(1);
        System.out.println(user.getId());
        user.setFirstName("Abdessadek");

        instance.updateUser(user);

        System.out.println(user.getFirstName());
        assertNotNull(user);
    }
    @Test
    void AddUserFromService(){
        createTestEnv("Professor");

        List<User> users = instance.getAllUsers();
        users.forEach(user -> System.out.println(user.getFirstName()));

    }
}