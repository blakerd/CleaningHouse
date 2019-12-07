package com.example.researchapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import androidx.appcompat.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

public class LoginScreen extends AppCompatActivity {
    String userName;
    String passWord;
    EditText userNameInput;
    EditText passWordInput;
    Button btn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);


        mAuth = FirebaseAuth.getInstance();
        userNameInput = (EditText) findViewById(R.id.userName);
        passWordInput= (EditText) findViewById(R.id.passWord);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                userName = userNameInput.getText().toString();
                passWord = passWordInput.getText().toString();
                mAuth.signInWithEmailAndPassword(userName, passWord)
                        .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d("SignInSuccess", "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(LoginScreen.this, "Authentication success.",
                                            Toast.LENGTH_SHORT).show();
                                    //nextScreen();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w("SignInFail", "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginScreen.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }
                        });
                switch (v.getId()) {
                    case R.id.button:
                        nextScreen();
                        break;
                }
            }
            });

    }






    private void nextScreen() {

        Intent i = new Intent(this, HomeScreenHost.class);
        startActivity(i);

    }


}