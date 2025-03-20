package com.main.user_management_demo.beans;

import com.main.user_management_demo.models.Job;
import com.main.user_management_demo.services.JobService;
import com.main.user_management_demo.services.UserService;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;

import java.io.Serializable;

@ManagedBean(name = "job")
@ApplicationScoped
public class JobBean implements Serializable {

    private final UserService userService = UserService.getInstance();
    private final JobService jobService = JobService.getInstance();

    /* properties */

    private String jobName;
    private String salary;
    private String message = "";


    public JobBean() {}



    public String Submit(){

        if(!isFullField()){
            setMessage("all fields are required");
            return "";
        }

        if(jobService.checkByNameIfJobExist(jobName)){

            setMessage("Job Already Exist");
            return "";
        }

        jobService.addJob(jobService.createJob(jobName, salary));

       return "addJob?faces-redirect=true";
    }



    public boolean isFullField(){
        return jobName != null && salary != null;
    }




    /* getters && setters */

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
