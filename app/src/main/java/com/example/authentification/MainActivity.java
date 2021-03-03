package com.example.authentification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.authentification.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.Query;


public class MainActivity extends AppCompatActivity {
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    Button mBackToLobby;
    FirebaseAuth fAuth;

    private RecyclerView recyclerView;
    personAdapter adapter; // Create Object of the Adapter class
long  NumbreOfActiviy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding;

        binding=ActivityMainBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);

        mBackToLobby = findViewById(R.id.BackToLobby);
        fAuth = FirebaseAuth.getInstance();
        String UserIdKey= fAuth.getUid();

        rootNode=FirebaseDatabase.getInstance();

        reference= FirebaseDatabase.getInstance().getReference().child("Activité");

        /**********************/


            // Create a instance of the database and get
            // its reference

            recyclerView = findViewById(R.id.recycler1);

            // To display the Recycler view linearly
            recyclerView.setLayoutManager(
                    new LinearLayoutManager(this));

            // It is a class provide by the FirebaseUI to make a
            // query in the database to fetch appropriate data
            FirebaseRecyclerOptions<person> options
                    = new FirebaseRecyclerOptions.Builder<person>()
                    .setQuery(reference, person.class)
                    .build();
            // Connecting object of required Adapter class to
            // the Adapter class itself
            adapter = new personAdapter(options);
            // Connecting Adapter class with the Recycler view*/
            recyclerView.setAdapter(adapter);



        /********************/

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot datasnapshot) {
                if(datasnapshot.exists()){
                    NumbreOfActiviy=(datasnapshot.getChildrenCount());
                }


            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mBackToLobby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreationActivite_Activity.class));

            }
        });

        binding.BackToProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserProfilActivity.class));

            }
        });

        binding.GoToMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));

            }
        });
        binding.TestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                binding.TestView.setText( Long.toString(NumbreOfActiviy));

            }
        });



    }


    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stoping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }


    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();;
        startActivity(new Intent(getApplicationContext(),login.class));
        finish();
    }



}
