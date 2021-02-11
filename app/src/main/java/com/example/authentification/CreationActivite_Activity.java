package com.example.authentification;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.authentification.databinding.ActivityCreationActiviteBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class CreationActivite_Activity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
     ArrayList <String> mThemeResult;
    ActivityCreationActiviteBinding binding;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    CheckBox mCulture;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    String FrequenceAc;
    String date;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityCreationActiviteBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        fAuth = FirebaseAuth.getInstance();

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

                String NomDactivite= binding.EditActivityName.getText().toString();
                String DescriptionActivite= binding.EditActivityDescription.getText().toString();
                String DateActivite=date;
                String NumberOfParticipantActivite=binding.EditNumberOfParticipant.getText().toString();
                String FrequenceActivite= FrequenceAc;
                String AdresseActivite=binding.MyAdresse.getText().toString();
                String TarifActivite =binding.EditActivityTarif.getText().toString();

                String ActiviteId=fAuth.getUid()+"__"+NomDactivite;
                rootNode= FirebaseDatabase.getInstance();
                reference=rootNode.getReference( "Activité");
                reference.child(ActiviteId).child("NomDactivite").setValue(NomDactivite);
                reference.child(ActiviteId).child("thème").setValue(resultat.toString());
                reference.child(ActiviteId).child("DescriptionActivite").setValue(DescriptionActivite);
                reference.child(ActiviteId).child("AdresseActivite").setValue(AdresseActivite);
                reference.child(ActiviteId).child("FrequenceActivite").setValue(FrequenceActivite);
                reference.child(ActiviteId).child("DateActivite").setValue(DateActivite);
                reference.child(ActiviteId).child("NumberOfParticipantActivite").setValue(NumberOfParticipantActivite);
                reference.child(ActiviteId).child("TarifActivite").setValue(TarifActivite);

            }
        });

        binding.Mydate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CreationActivite_Activity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d("TAG", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                 date =  day + "/" + month + "/" + year;
                binding.Mydate.setText(date);
            }
        };




        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.frequence, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner1.setAdapter(adapter);
        binding.spinner1.setOnItemSelectedListener(this);

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
         FrequenceAc = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), FrequenceAc, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
