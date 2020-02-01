package com.example.researchapp;
//tonys comment
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class SignUpScreen extends AppCompatActivity {
    String userName;
    String email;
    String confirm;
    String passWord;

    EditText userNameInput;
    EditText emailInput;
    EditText confirmInput;
    EditText passWordInput;
    Button btn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        mAuth = FirebaseAuth.getInstance();
        emailInput =  (EditText) findViewById(R.id.email);
        userNameInput = (EditText) findViewById(R.id.userName);
        passWordInput= (EditText) findViewById(R.id.passWord);
        confirmInput = (EditText) findViewById(R.id.confirmpassWord);
        btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                userName = userNameInput.getText().toString();
                email = emailInput.getText().toString();
                passWord = passWordInput.getText().toString();
                confirm = confirmInput.getText().toString();
                if (!userName.equals(null) && passWord.equals(confirm)) {
                    mAuth.createUserWithEmailAndPassword(email, passWord)
                            .addOnCompleteListener(SignUpScreen.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Log.d("SignUpSuccess", "createUserWithEmail:success");
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(SignUpScreen.this, "Account Successfully Created",
                                                Toast.LENGTH_SHORT).show();
                                        nextScreen();
                                        //updateUI(user);
                                    } else {
                                        Log.d("SignUpFail", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignUpScreen.this, "Account Creation Failed",
                                                Toast.LENGTH_SHORT).show();
                                        //updateUI(null);
                                        nextScreen();
                                    }
                                }
                            });
                }

                /*else {
                    Toast.makeText(SignUpScreen.this, "Usernames or passwords do not match",
                            Toast.LENGTH_SHORT).show();
                }*/
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