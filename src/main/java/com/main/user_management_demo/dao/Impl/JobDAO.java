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

    private JobDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("users_management");
        em = emf.createEntityManager();
    }
    @Override
    public List<Job> getAllJobs() {
        List<Job> jobs;
        Query query = em.createQuery("select j from Job j");
        jobs = query.getResultList();

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
        em.getTransaction().begin();
        em.merge(job);
        em.getTransaction().commit();
        return true;

    }

    @Override
    public boolean deleteJob(int id) {
        em.getTransaction().begin();
        Job job = em.find(Job.class, id);
        em.remove(job);
        em.getTransaction().commit();
        return true;

    }
}
