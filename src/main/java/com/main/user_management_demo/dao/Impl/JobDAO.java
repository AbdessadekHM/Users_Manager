package com.main.user_management_demo.dao.Impl;

import com.main.user_management_demo.dao.IJobDAO;
import com.main.user_management_demo.models.Job;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class JobDAO implements IJobDAO {
    private static JobDAO instance;
    private final EntityManager em;

    public static JobDAO getInstance() {
        if (instance == null) {
            instance = new JobDAO();
        }
        return instance;
    }


    private JobDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("users_management");
        em = emf.createEntityManager();
    }
    @Override
    public List<Job> getAllJobs() {
        List<Job> jobs;
        em.getTransaction().begin();
        Query query = em.createQuery("select j from Job j");
        jobs = query.getResultList();
        em.getTransaction().commit();

        return jobs;
    }

    @Override
    public Job getJobById(int id) {
        em.getTransaction().begin();
        Job job = em.find(Job.class, id);
        em.getTransaction().commit();
        return job;
    }

    @Override
    public boolean addJob(Job job) {
        em.getTransaction().begin();
        em.persist(job);
        em.getTransaction().commit();
        return true;

    }

    @Override
    public boolean updateJob(Job job) {
        // i can modify this, if i encounter the same error as user dao
        em.getTransaction().begin();
        em.merge(job);
        em.getTransaction().commit();
        return true;

    }

    @Override
    public boolean deleteJob(int id) {

        Job job = em.find(Job.class, id);
        em.getTransaction().begin();
        em.remove(job);
        em.getTransaction().commit();
        return true;

    }

    @Override
    public Job getJobByName(String name) {
        Query query = em.createQuery("select j from Job j where j.name = :name");
        query.setParameter("name", name);
        List<Job> jobs = query.getResultList();

        if(jobs.isEmpty()){
            return null;
        }


        return (Job) jobs.getFirst();
    }
}
