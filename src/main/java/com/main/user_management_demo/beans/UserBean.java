package com.main.user_management_demo.beans;

import com.main.user_management_demo.dao.Impl.JobDAO;
import com.main.user_management_demo.dao.Impl.UserDAO;
import com.main.user_management_demo.models.Job;
import com.main.user_management_demo.models.User;
import com.main.user_management_demo.services.JobService;
import com.main.user_management_demo.services.UserService;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;

import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "user")
@ApplicationScoped
public class UserBean implements Serializable {
    private String message = "";
    private String JobName;
    private User user = new User();
    private final UserService userService = UserService.getInstance();
    private final JobService jobService = JobService.getInstance();


   public UserBean() {}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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



    public String Submit(){



        Job job = jobService.getJobByName(JobName);
        System.out.println(job);

        if(!jobService.checkByNameIfJobExist(JobName)) {

            Job jobCreated = new Job();
            jobCreated.setName("Engineer");
            jobService.addJob(jobCreated);

        }


            if(!jobService.checkByNameIfJobExist(JobName)) {
            setMessage("Job not found");
            return "";

        }else if(!isFullField()){
            //checking if all fields are typed
            setMessage("Fields are required");
            return "";
        };

        job = jobService.getJobByName(JobName);
        user.setJob(job);
        System.out.println(user.getJob().getId());
        userService.addUser(user);
       return "addUser? faces-redirect = true";


    }

    public boolean isFullField() {


        return user.getEmail() != null && user.getFirstName() != null && user.getLastName() != null && user.getUsername() != null;

    }
}
