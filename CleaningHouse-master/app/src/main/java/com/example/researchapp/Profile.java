package com.example.researchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private TextView name, location;

    private FirebaseDatabase db;
    private DatabaseReference myref;

    private final String TAG = this.getClass().getName().toUpperCase();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        name = findViewById(R.id.userName);
        //location = findViewById(R.id.locationProfile);

        db = FirebaseDatabase.getInstance();
        myref = db.getReference("Users");

        myref.addValueEventListener(new ValueEventListener() {
            String fname;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    fname = (ds.child("Full Name").getValue(String.class));
                }
                name.setText(fname);
            }
               

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
