package com.main.user_management_demo.beans;

import com.main.user_management_demo.dao.Impl.JobDAO;
import com.main.user_management_demo.dao.Impl.UserDAO;
import com.main.user_management_demo.models.Job;
import com.main.user_management_demo.models.User;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;

import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "user")
@ApplicationScoped
public class UserBean implements Serializable {
    private String message;
    private String JobName;
   private User user = new User();
   private final UserDAO userDAO = UserDAO.getInstance();

   public UserBean() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean JobsExist() {

        List<Job> jobs = JobDAO.getInstance().getAllJobs();

        return !jobs.isEmpty();
    }

    public void Submit(){

        System.out.println("Submit");

        System.out.println(isFullField());
        Job job = null;

        job = JobDAO.getInstance().getJobByName(JobName);

        if(job == null) {
            setMessage("Job not found, add a job");
            return;
        };
        user.setJob(job);

        System.out.println(JobsExist());

        if(!JobsExist()) {
           setMessage("Add a Job first");
           return ;
        }
        if(!isFullField() ){
            return ;
        }

        userDAO.addUser(user);
        System.out.println("User added successfully");

    }

    public boolean isFullField() {


        return user.getEmail() != null && user.getFirstName() != null && user.getLastName() != null && user.getJob() !=null && user.getUsername() != null;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }
}
