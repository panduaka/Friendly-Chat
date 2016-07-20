package com.google.firebase.codelab.friendlychat;

/**
 * Created by windows 8.1 on 7/18/2016.
 */
public class UserDetails {

    private String name;
    //private String email;
    private  String mobile;
    private String userID;
    private String gender;

    UserDetails(){

    }

    UserDetails(String name,String mobile,String userID,String gender){
        this.name=name;
        this.mobile=mobile;
        this.userID=userID;
        this.gender=gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getUserID() {
        return userID;
    }

    public String getGender() {
        return gender;
    }
}
