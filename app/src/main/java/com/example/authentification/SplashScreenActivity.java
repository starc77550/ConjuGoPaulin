package com.example.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuthSettings;


public class SplashScreenActivity extends AppCompatActivity {

    private final int SplashScreenTimeOut= 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        FirebaseAuth fAuth;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();



        //redirection vers register après 3sec
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                //Démarrer un page

                if(user!= null) {
                    Intent intent = new Intent(getApplicationContext(), UserProfilActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(getApplicationContext(), PremiereOuvertureActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        //handler post delayed
        new Handler().postDelayed(runnable, SplashScreenTimeOut);

    }
}