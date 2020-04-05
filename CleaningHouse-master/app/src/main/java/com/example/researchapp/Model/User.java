package com.example.researchapp.Model;

public class User {
    private String userId;
    private String userName;
    private String status;
    private String imageURL;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public User(String userId, String userName, String status, String imageURL) {
        this.userId = userId;
        this.userName = userName;
        this.status = status;
        this.imageURL = imageURL;
    }

    public User() {

    }

}
