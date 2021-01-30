    package com.example.authentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

    public class UserProfilActivity extends AppCompatActivity {

    TextView mUtilisateurNomComplet,mUtilisateurEmail, mUtilisateurActuelPhone;
    Button mBackToMapButton, mSaveModificationUserProfilButton,mPasswordChangeButton,mUserEmailUpdatedButton;
    EditText mUserDescription, mUserEmailUpdated2, mUserPasswordCheck,mUserEmailUpdated, mUserPhoneUpdated;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    String UserEmail="";


    public static String Censure(String email){
  String mail="";
int place=0;
int i=0;
String etoile="";
        char nouveauCaratere=(char)('*');
       while (email.charAt(i)!='@'){
           place+=1;
           i+=1;
       }
       for (int j=1; j<place;j++){
       etoile= etoile+nouveauCaratere;
       }
mail=email.substring(0,1)+etoile+email.substring(place-1);
        return mail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil);
        mUtilisateurNomComplet= findViewById(R.id.UtilisateurNomComplet);
        mUtilisateurEmail=findViewById(R.id.UtilisateurEmail);
        mBackToMapButton=findViewById(R.id.BackToMapButton);
        mSaveModificationUserProfilButton=findViewById(R.id.SaveModificationUserProfilButton);
        mUserDescription=findViewById(R.id.UserDescription);
        mPasswordChangeButton=findViewById(R.id.PasswordChangeButton);

        mUserEmailUpdatedButton=findViewById(R.id.UserEmailUpdatedButton);
        mUserEmailUpdated2=findViewById(R.id.UserEmailUpdated2);
        mUserEmailUpdated=findViewById(R.id.UserEmailUpdated);
        mUserPasswordCheck=findViewById(R.id.UserPasswordCheck);
        mUserPhoneUpdated=findViewById(R.id.UserPhoneUpdated);
        mUtilisateurActuelPhone=findViewById(R.id.UtilisateurActuelPhone);


        fAuth = FirebaseAuth.getInstance();
        String UserIdKey= fAuth.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("UserData").child(UserIdKey);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                String name= datasnapshot.child("userLastName").getValue(String.class);
                String prenom= datasnapshot.child("userFirstName").getValue(String.class);
                 UserEmail= datasnapshot.child("userEmail").getValue(String.class);
                String UserDescription=datasnapshot.child("DescriptionUtilisateur").getValue(String.class);
                mUserDescription.setText(UserDescription);
                mUtilisateurNomComplet.setText(name+" "+prenom);
                mUtilisateurEmail.setText(Censure(UserEmail));
                String UtilisateurActuelPhone =datasnapshot.child("userPhoneNumber").getValue(String.class);
                mUtilisateurActuelPhone.setText(UtilisateurActuelPhone);
            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mBackToMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PremiereOuvertureActivity.class));

            }
        });

        mSaveModificationUserProfilButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String UserDescription = mUserDescription.getText().toString().trim();
                String UserPhoneUpdated = mUserPhoneUpdated.getText().toString().trim();
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference( "UserData");
                String UserIdKey= fAuth.getUid();


                reference.child(UserIdKey).child("DescriptionUtilisateur").setValue(UserDescription);

                if (!Patterns.PHONE.matcher(UserPhoneUpdated).matches()) {
                   mUserPhoneUpdated.setError("Veuillez entrer un numéro de téléphone valide");
                    return;
                }

                reference.child(UserIdKey).child("userPhoneNumber").setValue(UserPhoneUpdated);
                Toast.makeText(UserProfilActivity.this, "Modifications sauvegardées", Toast.LENGTH_SHORT).show();

            }
        });

        mPasswordChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseUser user =fAuth.getCurrentUser();

                fAuth.sendPasswordResetEmail(user.getEmail()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(UserProfilActivity.this, "Mail de changement de MDP envoyé à"+" "+user.getEmail(), Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "erreur"+e.getMessage());

                        Toast.makeText(UserProfilActivity.this, " Erreur lors de l'envoie", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        mUserEmailUpdatedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser user =fAuth.getCurrentUser();

                String UserEmailUpdated2 = mUserEmailUpdated2.getText().toString().trim();
                String UserPasswordCheck = mUserPasswordCheck.getText().toString().trim();
                String UserEmailUpdated = mUserEmailUpdated.getText().toString().trim();


                if (!Patterns.EMAIL_ADDRESS.matcher(UserEmailUpdated).matches()){
                    mUserEmailUpdated.setError("Veuillez saisir une adresse email valide");
                    return;
                }
                if (!UserEmailUpdated2.equals(UserEmailUpdated)) {
                    mUserEmailUpdated2.setError("Les 2 adresses doivent être identique");
                    return;
                }


                AuthCredential credential = EmailAuthProvider.getCredential(user.getEmail(), UserPasswordCheck);



                user.reauthenticate(credential).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        user.updateEmail(UserEmailUpdated);
                      //  user.sendEmailVerification();



                        rootNode=FirebaseDatabase.getInstance();
                        reference=rootNode.getReference( "UserData");
                        String UserIdKey= fAuth.getUid();
                        reference.child(UserIdKey).child("userEmail").setValue(UserEmailUpdated);


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("TAG", "erreur"+e.getMessage());

                        Toast.makeText(UserProfilActivity.this, "Mot de passe invalide", Toast.LENGTH_SHORT).show();
                    }
                });




            }
        });









    }
}