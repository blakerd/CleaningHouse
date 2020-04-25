package com.example.researchapp;

public class Users {
    private String userName;
    private String Location;
    //private String imageURL;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }


    public Users(String userName, String Location) {
        this.userName = "Sally Mae";
        this.Location = "San Jose, California";
        //this.imageURL = imageURL;
    }

    public Users() {

    }

}
