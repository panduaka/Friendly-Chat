package com.google.firebase.codelab.friendlychat;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by windows 8.1 on 7/18/2016.
 */
public class UserDetails {

    private String name;
    private String email;
    private String mobile;
    private String userID;
    private String gender;
    private int online_offline_status;


    public UserDetails() {
        this.online_offline_status = 0;
    }

    public UserDetails(int online_offline_status) {
        this.online_offline_status = online_offline_status;
    }

    UserDetails(String name, String mobile, String userID, String gender, String email,int online_offline_status) {
        this.name = name;
        this.mobile = mobile;
        this.userID = userID;
        this.gender = gender;
        this.email = email;
        this.online_offline_status=online_offline_status;
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

    public void setEmail(String email) {
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public int getOnline_offline_status() {
        return online_offline_status;
    }

    public void setOnline_offline_status(int online_offline_status) {
        this.online_offline_status = online_offline_status;
    }



    public Map<String, Object> toMap() {
        HashMap<String, Object> update = new HashMap<String, Object>();
        update.put("name", name);
        update.put("email", email);
        update.put("mobile", mobile);
        update.put("userId", userID);
        update.put("gender", gender);
        return update;
    }

}
