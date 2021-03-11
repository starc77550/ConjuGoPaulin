package com.example.authentification;

public class ActivityCreationHelperClass {
    String NomDactivite, DescriptionActivite,DateActivite,NumberOfParticipantActivite,FrequenceActivite, Theme, AdresseActivite,TarifActivite, AdaptedHandicaped, AuteurID,ActivityStatuts ;

    public ActivityCreationHelperClass() {
    }

    public ActivityCreationHelperClass(String nomDactivite, String descriptionActivite, String dateActivite, String numberOfParticipantActivite, String frequenceActivite, String theme, String adresseActivite, String tarifActivite, String adaptedHandicaped, String auteurID, String activityStatuts) {

        NomDactivite = nomDactivite;
        DescriptionActivite = descriptionActivite;
        DateActivite = dateActivite;
        NumberOfParticipantActivite = numberOfParticipantActivite;
        FrequenceActivite = frequenceActivite;
        Theme = theme;
        AdresseActivite = adresseActivite;
        TarifActivite = tarifActivite;
        AdaptedHandicaped = adaptedHandicaped;
        AuteurID = auteurID;
        ActivityStatuts = activityStatuts;
    }

    public String getNomDactivite() {
        return NomDactivite;
    }

    public String getDescriptionActivite() {
        return DescriptionActivite;
    }

    public String getDateActivite() {
        return DateActivite;
    }

    public String getNumberOfParticipantActivite() {
        return NumberOfParticipantActivite;
    }

    public String getFrequenceActivite() {
        return FrequenceActivite;
    }

    public String getTheme() {
        return Theme;
    }

    public String getAdresseActivite() {
        return AdresseActivite;
    }

    public String getTarifActivite() {
        return TarifActivite;
    }

    public String getAdaptedHandicaped() {
        return AdaptedHandicaped;
    }

    public String getAuteurID() {
        return AuteurID;
    }

    public String getActivityStatuts() {
        return ActivityStatuts;
    }
}

