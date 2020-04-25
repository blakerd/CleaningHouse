package com.example.researchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeletePropertyScreen extends AppCompatActivity implements View.OnClickListener{

    Button deleteButton;
    EditText streetAddressInput;
    FirebaseAuth mFirebaseAuth;
    String userID;
    String streetAddress;
    Boolean propDeleted = false;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_property_screen);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(this);
        streetAddressInput =  (EditText) findViewById(R.id.propertyAddress);

        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        userID = mFirebaseUser.getUid();

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("Users").child(userID).child("Properties");

    }

    public void onClick(View v) {
        switch(v.getId()){
            case R.id.deleteButton:
            streetAddress = streetAddressInput.getText().toString();
            streetAddress = streetAddress.toUpperCase();
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot data : dataSnapshot.getChildren())
                        {
                            String strStreetAddress = data.child("Street Address").getValue(String.class);
                            String k = data.getKey();
                            if(strStreetAddress.equals(streetAddress))
                            {
                            myRef.child(k).removeValue();
                            propDeleted = true;
                            }
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                if(propDeleted == true)
                {
                    Toast.makeText(DeletePropertyScreen.this, "Property Deleted", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(DeletePropertyScreen.this, "Unable to delete property, make sure you are typing in the street address", Toast.LENGTH_SHORT).show();
                }

                Intent i = new Intent(this, PropertiesScreen.class);
                startActivity(i);
                break;

        }
    }
}
