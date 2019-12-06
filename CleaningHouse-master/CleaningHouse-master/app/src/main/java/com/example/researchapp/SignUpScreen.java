package com.example.researchapp;
//tonys comment
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
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
                nextScreen();
            }




        });

    }

    private void nextScreen() {

        Intent i = new Intent(this, HomeScreenHost.class);
        startActivity(i);

    }

}
