package com.firebaseproject.facebookloginauthexample.Modal;

public class UsersData {
    //passing user data to next screen through modal class using intent
    String userName;

    public UsersData(String userName) {
        this.userName = userName;
    }

    public UsersData(){} //default constructor

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
