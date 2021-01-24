package com.example.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SplashScreenTimeOut= 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //redirection vers register après 3sec
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Démarrer un page

                Intent intent=new  Intent(getApplicationContext(),PremiereOuvertureActivity.class);
                startActivity(intent);
                finish();
            }
        };
        //handler post delayed
        new Handler().postDelayed(runnable, SplashScreenTimeOut);

    }
}