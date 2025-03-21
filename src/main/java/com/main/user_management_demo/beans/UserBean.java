package com.main.user_management_demo.beans;

import com.main.user_management_demo.dao.Impl.JobDAO;
import com.main.user_management_demo.dao.Impl.UserDAO;
import com.main.user_management_demo.models.Job;
import com.main.user_management_demo.models.User;
import com.main.user_management_demo.services.JobService;
import com.main.user_management_demo.services.UserService;
import jakarta.faces.bean.ApplicationScoped;
import jakarta.faces.bean.ManagedBean;
import jakarta.faces.bean.RequestScoped;

import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "user")
@RequestScoped
public class UserBean implements Serializable {


    private final UserService userService = UserService.getInstance();
    private final JobService jobService = JobService.getInstance();

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String message = "";
    private String jobName;
    private List<User> users = userService.getAllUsers();


   public UserBean() {}




    public String Submit(){


        Job job = jobService.getJobByName(jobName);
        System.out.println(job);

        if(!jobService.checkByNameIfJobExist(jobName)) {
            setMessage("Job not found");
            return "";

        }else if(!isFullField()){
            //checking if all fields are typed
            setMessage("Fields are required");
            return "";
        };


        userService.addUser(userService.createUser(username, firstName, lastName, email, job));

        return "index?faces-redirect = true";

    }

    public boolean isFullField() {

        return email != null && firstName != null && lastName != null && username != null && jobName != null;

    }
    public String remove(int id){
      boolean isSuccess = userService.deleteUser(id);
      if(!isSuccess){
          setMessage("Faild to delete User");
          return "";
      }

       return "users?faces-redirect = true";
    }




    /* getters and setters */
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJobName() {
       return jobName;
    }
    public void setJobName(String jobName) {

        this.jobName = jobName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
