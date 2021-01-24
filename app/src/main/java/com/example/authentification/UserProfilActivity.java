package com.example.authentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfilActivity extends AppCompatActivity {

    TextView mUtilisateurNomComplet,mUtilisateurEmail;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth fAuth;

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
        fAuth = FirebaseAuth.getInstance();
        String UserIdKey= fAuth.getUid();
        reference= FirebaseDatabase.getInstance().getReference().child("UserData").child(UserIdKey);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {

                String name= datasnapshot.child("userLastName").getValue(String.class);
                String prenom= datasnapshot.child("userFirstName").getValue(String.class);
                String email= datasnapshot.child("userEmail").getValue(String.class);


                mUtilisateurNomComplet.setText(name+" "+prenom);
                mUtilisateurEmail.setText(Censure(email));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}