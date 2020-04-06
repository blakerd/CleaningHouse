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

    /*public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }*/

    public Users(String userName, String Location /*, String imageURL*/) {
        this.userName = userName;
        this.Location = Location;
        //this.imageURL = imageURL;
    }

    public Users() {

    }

}
