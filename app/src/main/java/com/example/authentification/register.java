package com.example.authentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {
    //variable

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    EditText mUserLastName, mUserFirstName, mUserEmail,mUserPassword;
    Switch mswitch1;
    Button mRegisterBtn, mSortirInscription;
    TextView mLoginBtn, mRegister;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView a;

    String AssoStatut="false";

    Button btn;
    DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mUserLastName = findViewById(R.id.editTextUserLastName);
        mUserFirstName = findViewById(R.id.editTextUserFirstName);
        mUserEmail = findViewById(R.id.editTextTextEmailAddress);
        mUserPassword = findViewById(R.id.editTextTextPassword);
        mRegisterBtn = findViewById(R.id.button);
        mLoginBtn = findViewById(R.id.textView5);
        mswitch1 = findViewById(R.id.switch1);
        mRegister =findViewById(R.id.textView2);
        mSortirInscription=findViewById(R.id.SortirInscription);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);




        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserLastName = mUserLastName.getText().toString().trim();
                String UserFirstName = mUserFirstName.getText().toString().trim();
                String UserEmail = mUserEmail.getText().toString().trim();
                String UserPassword = mUserPassword.getText().toString().trim();
                String UserAsso= mswitch1.getText().toString().trim();



                if (TextUtils.isEmpty(UserEmail)){
                    mUserEmail.setError("Email nécessaire.");
                    return;
                }
                if(TextUtils.isEmpty(UserPassword)){
                    mUserPassword.setError("mot de passe nécessaire.");
                    return;
                }
                if (UserPassword.length() < 7) {
                    mUserPassword.setError("Minimum 7 charactères");
                    return;
                }
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference( "UserData");



                UserHelperClass UserData= new UserHelperClass(UserLastName, UserFirstName, UserEmail, UserPassword);



                progressBar.setVisibility(View.INVISIBLE);

                //enregistrer l'utilisateur dans fbase

                fAuth.createUserWithEmailAndPassword(UserEmail,UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(register.this, "Compte créé.", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            String UserIdKey= fAuth.getUid();
                            reference.child(UserIdKey).setValue(UserData);
                        }
                        else {
                            Toast.makeText(register.this, "Erreur !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });




            }
        });
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),login.class));
            }


        });

        mSortirInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PremiereOuvertureActivity.class));
            }
        });

        mswitch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(AssoStatut=="false"){
                    AssoStatut="true";
                }
                if(AssoStatut=="true"){
                    AssoStatut="true";
                }


            }
        });





    }
}