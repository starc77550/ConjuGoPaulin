package com.example.authentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.authentification.databinding.ActivityCreationActiviteBinding;
import com.example.authentification.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreationActivite_Activity extends AppCompatActivity {
     ArrayList <String> mThemeResult;
    ActivityCreationActiviteBinding binding;
    CheckBox mCulture;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreationActiviteBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        mCulture = findViewById(R.id.checkBoxCulture);

        binding.AnnulerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));

            }
        });

        binding.MiseEnLigneActiviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuffer resultat=new StringBuffer();

                if(binding.checkBoxSport.isChecked()){
                    resultat.append("#"+binding.checkBoxSport.getText().toString());
                }
                if(binding.checkBoxCulture.isChecked()){
                    resultat.append("#"+binding.checkBoxCulture.getText().toString());
                }
                if(binding.checkBoxMusique.isChecked()){
                    resultat.append("#"+binding.checkBoxMusique.getText().toString());
                }
                if(binding.checkBoxEducation.isChecked()){
                    resultat.append("#"+binding.checkBoxEducation.getText().toString());
                }
                if(binding.checkBoxJeuxDeSociete.isChecked()){
                    resultat.append("#"+binding.checkBoxJeuxDeSociete.getText().toString());
                }
                if(binding.checkBoxPleinAir.isChecked()){
                    resultat.append("#"+binding.checkBoxPleinAir.getText().toString());
                }

                rootNode= FirebaseDatabase.getInstance();
                reference=rootNode.getReference( "Activité");
              reference.child("IdActivité").child("thème").setValue(resultat.toString());


            }
        });




    }
}