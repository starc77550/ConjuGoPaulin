package com.example.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.authentification.databinding.ActivityContactBinding;
import com.example.authentification.databinding.ActivityCreationActiviteBinding;

public class Contact_Activity extends AppCompatActivity {
    TextView ccas;
    ActivityContactBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityContactBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        ccas = findViewById(R.id.leccas);
        ccas.setMovementMethod(LinkMovementMethod.getInstance());

        /*sortir = (Button) findViewById(R.id.sortir);
        sortir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeOfScreen();
            }
        }); */





        binding.sortir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));

            }
        });
    }

    /*public void changeOfScreen(){
        Intent intent = new Intent(this,EcranDeSortie.class);
        startActivity(intent);
    }*/

    public void open(View view) {
        Intent email= new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:conjugoimtld@gmail.com"));
        email.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        email.putExtra(Intent.EXTRA_TEXT, "My Email message");
        startActivity(email);
    }
}