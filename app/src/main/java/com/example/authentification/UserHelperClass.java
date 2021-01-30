package com.example.authentification;

public class UserHelperClass {
    String UserLastName, UserFirstName, UserEmail, UserPassword,AssoStatut, UserPhoneNumber;
    public UserHelperClass() {

    }

    public UserHelperClass(String userLastName, String userFirstName, String userEmail, String userPassword, String assoStatut, String userPhoneNumber) {
     UserLastName = userLastName;
       UserFirstName = userFirstName;
        UserEmail = userEmail;
        UserPassword = userPassword;
       UserPhoneNumber = userPhoneNumber;
        AssoStatut = assoStatut;
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



    public String getAssoStatut() {
        return AssoStatut;
    }

    public String getUserPhoneNumber() {
        return UserPhoneNumber;
    }

}