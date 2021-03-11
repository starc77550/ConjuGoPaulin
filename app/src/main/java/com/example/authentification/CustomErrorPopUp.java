package com.example.authentification;
import android.app.Activity;
import  android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomErrorPopUp extends Dialog{

    // champs

    private String Titre;
    private String ErrorMessages;
    private Button OKButton;
    private TextView mErrorTilte;
    private TextView mErrorMessages;

    // constructeur
    public CustomErrorPopUp(Activity activity){
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.error_popup_template);
        this.Titre="veuillez corriger les éléments suivant";
        this.ErrorMessages="Les erreurs sont:";
        this.OKButton=findViewById(R.id.OKbuttom);
        this.mErrorMessages=findViewById(R.id.ErrorMessages);
        this.mErrorTilte=findViewById(R.id.ErrorTilte);

    }


    public Button getOKButton() {
        return OKButton;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public void setErrorMessages(String errorMessages) {
        ErrorMessages = errorMessages;
    }

    public void build(){

        show();
        mErrorTilte.setText(Titre);
        mErrorMessages.setText(ErrorMessages);


    }


}