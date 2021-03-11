package com.example.authentification;
import android.app.Activity;
import  android.app.Dialog;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ChoixTypeComptePopUp extends Dialog{

    // champs

    private String Titre;
    private String Cancel;
    private Button mAssoButton,mParticulierButton ;
    private TextView mTitle;
    private TextView mCancelTexView;

    // constructeur
    public ChoixTypeComptePopUp(Activity activity){
        super(activity, R.style.Theme_AppCompat_Dialog);
        setContentView(R.layout.error_popup_template);
        this.Titre="Vous êtes: ";
        this.mAssoButton=findViewById(R.id.AssoButton);
        this.mParticulierButton=findViewById(R.id.Particulierbutton);
        this.mTitle=findViewById(R.id.Title);
        this.mCancelTexView=findViewById(R.id.CancelTexView);

    }




    public Button getmAssoButton() {
        return mAssoButton;
    }

    public Button getmParticulierButton() {
        return mParticulierButton;
    }

    public TextView getmCancelTexView() {
        return mCancelTexView;
    }







}