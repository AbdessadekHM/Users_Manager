package com.main.user_management_demo.services;

import com.main.user_management_demo.dao.Impl.JobDAO;
import com.main.user_management_demo.models.Job;

import java.util.List;

public class JobService {
   private static JobService instance;
   private final UserService userService = UserService.getInstance();
   private final JobDAO jobDAO = JobDAO.getInstance();



   private JobService() {}


   public static JobService getInstance() {
       if (instance == null) {
           instance = new JobService();
       }
       return instance;
   }


   public boolean checkByNameIfJobExist(String name){
       Job job = jobDAO.getJobByName(name);
       return job != null;
   }
   public Job getJobByName(String name){
       return jobDAO.getJobByName(name);
   }
   public boolean addJob(Job job){
       return jobDAO.addJob(job);
   }

    public Job createJob(String jobName, String salary) {
        Job job = new Job();
        job.setName(jobName);
        job.setSalary(salary);
        return job;
    }
    public List<Job> getAllJobs(){
       return jobDAO.getAllJobs();
    }

    public boolean deleteJob(int id){
       try{
           return jobDAO.deleteJob(id);
       } catch (Exception e) {
           e.printStackTrace();
           return false;
       }

    }

}

