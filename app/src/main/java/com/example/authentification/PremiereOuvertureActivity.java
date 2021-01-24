package com.example.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PremiereOuvertureActivity extends AppCompatActivity {

    Button mButtonLoginOrRegister, mButtonDecouvrirActivite;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_premiere_ouverture2);
        mButtonDecouvrirActivite = findViewById(R.id.ButtonDecouvrirActivite);
        mButtonLoginOrRegister = findViewById(R.id.ButtonLoginOrRegister);

        mButtonLoginOrRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }
        });


        mButtonDecouvrirActivite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),UserProfilActivity.class));
            }
        });


    }
}