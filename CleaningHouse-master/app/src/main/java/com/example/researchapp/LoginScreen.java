package com.example.researchapp;
//tonys comment
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import androidx.appcompat.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

public class LoginScreen extends AppCompatActivity {
    Button signUpBtn;
    EditText emailId, password;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        mFirebaseAuth = FirebaseAuth.getInstance();
        signUpBtn = (Button) findViewById(R.id.signUpButton);
        emailId = (EditText) findViewById(R.id.emailButton);
        password = (EditText) findViewById(R.id.passWordButton);
        tvSignIn = (TextView) findViewById(R.id.textView);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override

            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null) {
                    Toast.makeText(LoginScreen.this, "You are logged in.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginScreen.this, HomeScreenHost.class);
                    startActivity(i);
                } else {
                    Toast.makeText(LoginScreen.this, "Please login.", Toast.LENGTH_SHORT).show();
                }
            }
        };
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pad = password.getText().toString();
                if (email.isEmpty()) {
                    emailId.setError("Please enter an email-id.");
                    emailId.requestFocus();

                } else if (pad.isEmpty()) {
                    password.setError("Please enter a password.");
                    password.requestFocus();
                } else if (email.isEmpty() && pad.isEmpty()) {
                    Toast.makeText(LoginScreen.this, "Fields are empty.", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pad.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pad).addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginScreen.this, "Login Un-Successful. Please try again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(LoginScreen.this, HomeScreenHost.class));
                            }

                        }
                    });
                } else {
                    Toast.makeText(LoginScreen.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intoSignUp = new Intent(LoginScreen.this, OpeningScreen.class);
                startActivity(intoSignUp);
            }
        });
    }
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}
