package com.example.authentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register extends AppCompatActivity {
    //variable

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    EditText mUserLastName, mUserFirstName, mUserEmail,mUserPassword, mEditTelephone;
    Switch mswitch1;
    Button mRegisterBtn, mSortirInscription;
    TextView mLoginBtn, mRegister;
    FirebaseAuth fAuth;
    String AssoStatut="non";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mUserLastName = findViewById(R.id.editTextUserLastName);
        mUserFirstName = findViewById(R.id.editTextUserFirstName);
        mUserEmail = findViewById(R.id.editTextEmailAddress);
        mUserPassword = findViewById(R.id.editTextTextPassword);


        mRegister =findViewById(R.id.textView2);
        mSortirInscription=findViewById(R.id.SortirInscription);
        mEditTelephone=findViewById(R.id.EditTelephone);

        mRegisterBtn = findViewById(R.id.button);
        mLoginBtn = findViewById(R.id.textView5);
        mswitch1 = findViewById(R.id.switch1);

        fAuth = FirebaseAuth.getInstance();

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserLastName = mUserLastName.getText().toString().trim();
                String UserFirstName = mUserFirstName.getText().toString().trim();
                String UserEmail = mUserEmail.getText().toString().trim();
                String UserPassword = mUserPassword.getText().toString().trim();
                String UserPhoneNumber = mEditTelephone.getText().toString().trim();

                if (!Patterns.EMAIL_ADDRESS.matcher(UserEmail).matches()){
                    mUserEmail.setError("Veuillez saisir une adresse email valide");
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

               if (!Patterns.PHONE.matcher(UserPhoneNumber).matches()) {
                    mEditTelephone.setError("Veuillez entrer un numéro de téléphone valide");
                    return;
                }

                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference( "UserData");
                    UserHelperClass UserDataSender= new UserHelperClass(UserLastName, UserFirstName, UserEmail, UserPassword, AssoStatut ,UserPhoneNumber);




                //enregistrer l'utilisateur dans fbase

                fAuth.createUserWithEmailAndPassword(UserEmail,UserPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser user =fAuth.getCurrentUser();
                            String UserIdKey= fAuth.getUid();
                            reference.child(UserIdKey).setValue(UserDataSender);

                            user.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override

                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(register.this, "Email de vérification envoyé à"+" "+UserEmail, Toast.LENGTH_SHORT).show();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d("TAG", "erreur"+e.getMessage());

                                    Toast.makeText(register.this, " Erreur lors de l'envoie", Toast.LENGTH_SHORT).show();
                                }
                            });

                        startActivity(new Intent(getApplicationContext(),UserProfilActivity.class));

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

                if(AssoStatut=="non"){
                    AssoStatut="A certifier";
                }
                if(AssoStatut=="A certifier"){
                    AssoStatut="non";
                }

            }
        });
    }
}