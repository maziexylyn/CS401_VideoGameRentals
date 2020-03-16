// This is a class that represents the concept of an user.

package com.CS401;

public class User {

    protected String userID;
    protected String password;

    public User() {
        this.userID = "";
        this.password = "";
    }

    public User(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    // Method to verify the login information from the database.
    public boolean verifyLogin(String userID, String password) {
        return true;
    }

}
