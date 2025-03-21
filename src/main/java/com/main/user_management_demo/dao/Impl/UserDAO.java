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
    public boolean updateUser(User user) {
        Query query = em.createQuery("update User u set u.username = :username , u.firstName = :firstname, u.job = :job, u.lastName = :lastname, u.email = :email where u.id = :id");
        query.setParameter("username", user.getUsername());
        query.setParameter("firstname", user.getFirstName());
        query.setParameter("job", user.getJob());
        query.setParameter("lastname", user.getLastName());
        query.setParameter("email", user.getEmail());
        query.setParameter("id", user.getId());

        /*
        //"i did comment this piece of code, because it was throws an error of [id] is a primary key so can't update, i think that come from how merge works"
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
         */
        return true;

    }

    @Override
    public boolean addUser(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        return true;

    }

    @Override
    public boolean deleteUser(int id) {

        em.getTransaction().begin();
        em.remove(em.find(User.class, id));
        em.getTransaction().commit();

        return true;

    }
}
