package com.main.user_management_demo.dao;

import com.main.user_management_demo.models.Job;

import java.util.List;

public interface IJobDAO {
    public List<Job> getAllJobs();
    public Job getJobById(int id);
    public boolean addJob(Job job);
    public boolean updateJob(Job job);
    public boolean deleteJob(int id);
    public Job getJobByName(String name);

}
