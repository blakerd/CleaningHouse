
package com.example.researchapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
 import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;
//import com.google.firebase.storage.*;

public class UploadNewPropertyScreen extends AppCompatActivity implements View.OnClickListener {

    String propertyName;
    String propertyAddress;
    String city;
    String cleaningPrice;
    String userID;

    EditText propertyNameInput;
    EditText propertyAddressInput;
    EditText cityInput;
    EditText cleaningPriceInput;

    Button selectImageButton;
    Button uploadButton;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_new_property_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    //Toast.makeText(UploadNewPropertyScreen.this, "You are logged in.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UploadNewPropertyScreen.this, "Please login.", Toast.LENGTH_SHORT).show();
                }
            }
        };
        //grab userID to reference for storage
        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        userID = mFirebaseUser.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        //mStorageRef = mFirebaseStorage.getReference();

        propertyNameInput =  (EditText) findViewById(R.id.propertyName);
        propertyAddressInput = (EditText) findViewById(R.id.propertyAddress);
        cityInput = (EditText) findViewById(R.id.city);
        cleaningPriceInput = (EditText) findViewById(R.id.cleaningPrice);


       // selectImageButton = (Button) findViewById(R.id.selectImageButton);
        uploadButton = (Button) findViewById(R.id.uploadButton);
      //  selectImageButton.setOnClickListener(this);

        uploadButton.setOnClickListener(this);
        //mStorageRef = FirebaseStorage.getInstance().getReference();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

           // case R.id.selectImageButton:
                // uploadNewPropertyPage();
              //  break;

            case R.id.uploadButton:
                propertyName = propertyNameInput.getText().toString();
                propertyAddress = propertyAddressInput.getText().toString();
                city = cityInput.getText().toString();
                cleaningPrice = cleaningPriceInput.getText().toString();



                myRef.child("Users").child(userID).child("Properties").child(propertyName).child("Street Address").setValue(propertyAddress);
                myRef.child("Users").child(userID).child("Properties").child(propertyName).child("City").setValue(city);
                myRef.child("Users").child(userID).child("Properties").child(propertyName).child("Cleaning Price").setValue(cleaningPrice);
                Intent i = new Intent(this, PropertiesScreen.class);
                startActivity(i);

                break;
        }
    }

    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}




