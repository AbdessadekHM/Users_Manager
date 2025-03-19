package com.main.user_management_demo.dao.Impl;

import com.main.user_management_demo.dao.IUserDAO;
import com.main.user_management_demo.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class UserDAO implements IUserDAO {
    public static UserDAO instance;
    private final EntityManager em;



    private UserDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("users_management");
       em = emf.createEntityManager();
    }
    public static UserDAO getInstance(){
        if(instance == null){
            instance = new UserDAO();
        }
        return instance;
    }


    @Override
    public List<User> getAllUsers() {
        List<User> users;

        Query query = em.createQuery("SELECT u FROM User u", User.class);
        users = query.getResultList();
        return users;
    }

    @Override
    public List<User> getUsersByJobId(int jobId) {
        List<User> users;
        Query query = em.createQuery("SELECT u FROM User u WHERE u.job.id = :jobId", User.class);
        query.setParameter("jobId", jobId);
        users = query.getResultList();
        return users;
    }

    @Override
    public User getUserById(int id) {
        User user;
        em.getTransaction().begin();
        user = em.find(User.class, id);
        em.getTransaction().commit();

        return user;
    }

    @Override
    public void updateUser(User user) {
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();

    }

    @Override
    public void addUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

    }

    @Override
    public void deleteUser(int id) {
        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();

    }
}
