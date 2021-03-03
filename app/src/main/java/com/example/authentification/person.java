package com.example.authentification;
// Your packdateActivite name can be different depending
// on your project name
public class person
{
    // Variable to store data corresponding
    // to nomDactivite keyword in database
    private String nomDactivite;

    // Variable to store data corresponding
    // to activityStatuts keyword in database
    private String activityStatuts;

    // Variable to store data corresponding
    // to dateActivite keyword in database
    private String dateActivite;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public person() {}

    // Getter and setter method
    public String getnomDactivite()
    {
        return nomDactivite;
    }
    public void setnomDactivite(String nomDactivite)
    {
        this.nomDactivite = nomDactivite;
    }
    public String getactivityStatuts()
    {
        return activityStatuts;
    }
    public void setactivityStatuts(String activityStatuts)
    {
        this.activityStatuts = activityStatuts;
    }
    public String getdateActivite()
    {
        return dateActivite;
    }
    public void setdateActivite(String dateActivite)
    {
        this.dateActivite = dateActivite;
    }
}
