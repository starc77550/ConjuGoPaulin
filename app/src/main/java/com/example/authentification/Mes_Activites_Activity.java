package com.example.authentification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.authentification.databinding.ActivityCreationActiviteBinding;
import com.example.authentification.databinding.ActivityMesActivitesBinding;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Mes_Activites_Activity extends AppCompatActivity {
    ActivityMesActivitesBinding binding;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    FirebaseAuth fAuth;
    personAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMesActivitesBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);
        fAuth = FirebaseAuth.getInstance();
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
        binding.BackToMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));

            }
        });


        binding.GoToActivityCreationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreationActivite_Activity.class));

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
}





