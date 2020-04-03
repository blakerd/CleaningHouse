package com.example.researchapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PropertiesScreen extends AppCompatActivity implements View.OnClickListener {

    String userID;
    TextView propText;
    EditText propNickname;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    Button uploadNewPropertyButton;
    Button displayPropertyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        uploadNewPropertyButton = (Button) findViewById(R.id.uploadNewPropertyButton);
        displayPropertyButton = (Button) findViewById(R.id.displayPropertyButton);
        propText = (TextView) findViewById(R.id.Property);
        propNickname = (EditText) findViewById(R.id.propertyNickname);
        setSupportActionBar(toolbar);
        uploadNewPropertyButton.setOnClickListener(this);
        displayPropertyButton.setOnClickListener(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
        userID = mFirebaseUser.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.uploadNewPropertyButton:
                nextScreen();
                break;
            case R.id.displayPropertyButton:
                displayProperty();
                break;

        }
    }
    private void nextScreen() {

        Intent i = new Intent(this, UploadNewPropertyScreen.class);
        startActivity(i);

    }
    private void displayProperty() {
       String a = propNickname.getText().toString();

        myRef.child("Users").child(userID).child("Properties").child(a).child("Street Address").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                 String value = dataSnapshot.getValue(String.class);
                propText.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }

  /*  protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }

   */
}
