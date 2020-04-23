package com.example.researchapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OpeningScreen extends AppCompatActivity {
    //blake changed the code here in two different branches
   Button signUpBtn;
   EditText emailId, password;
   TextView tvSignUp;
   FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference reference;
    private FirebaseUser mFirebaseUser;
    private String userID;
    public String email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);

        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        reference = mFirebaseDatabase.getReference();
        signUpBtn = (Button) findViewById(R.id.signUpButton);
        emailId = (EditText) findViewById(R.id.emailButton);
        password = (EditText) findViewById(R.id.passWordButton);
        tvSignUp = (TextView) findViewById(R.id.textView);
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = emailId.getText().toString().trim();
                String paw = password.getText().toString().trim();
                if (email.isEmpty()) {
                    emailId.setError("Please enter an email-id.");
                    emailId.requestFocus();

                } else if (paw.isEmpty()) {
                    password.setError("Please enter a password.");
                    password.requestFocus();
                } else if (email.isEmpty() && paw.isEmpty()) {
                    Toast.makeText(OpeningScreen.this, "Fields are empty.", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && paw.isEmpty()) && (paw.length() < 8)) {
                    Toast.makeText(OpeningScreen.this, "Password must be at-least 8 characters log!", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && paw.isEmpty())) {
                    mFirebaseAuth.createUserWithEmailAndPassword(email, paw).addOnCompleteListener(OpeningScreen.this,
                            new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(OpeningScreen.this, "SignUp Unsuccessful. Please try again with a different longer, password. You may already have an account under that email", Toast.LENGTH_SHORT).show();
                                    } else {
                                        mFirebaseUser = mFirebaseAuth.getCurrentUser();
                                        userID = mFirebaseUser.getUid();
                                        reference.child("Users").child(userID).child("Email").setValue(email);
                                        startActivity(new Intent(OpeningScreen.this, SignUpScreen.class));
                                    }
                                }
                            });
                } else {
                    Toast.makeText(OpeningScreen.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
                    /*switch(v.getId()){
                        case R.id.button:
                            openActivity2();
                            break;
                        case R.id.signUpButton:
                            openActivitySignInPage();
                            break;*/
            }

        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent openFrom = new Intent(OpeningScreen.this, LoginScreen.class);
                //startActivity(openFrom);
                startActivity(new Intent(OpeningScreen.this, LoginScreen.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){ }

}
