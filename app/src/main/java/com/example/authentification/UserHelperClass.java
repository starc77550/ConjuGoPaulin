package com.example.authentification;

public class UserHelperClass {
    String UserLastName, UserFirstName, UserEmail, UserPassword;

    public UserHelperClass() {

    }

    public UserHelperClass(String userLastName, String userFirstName, String userEmail, String userPassword) {
        UserLastName = userLastName;
        UserFirstName = userFirstName;
        UserEmail = userEmail;
        UserPassword = userPassword;
    }

    public String getUserLastName() {
        return UserLastName;
    }

    public String getUserFirstName() {
        return UserFirstName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }
}
