package com.example.researchapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class OpeningScreen extends AppCompatActivity implements View.OnClickListener {
    //blake changed the code here in two different branches
    Button login;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening_screen);
        login = (Button) findViewById(R.id.btn);
        signin = (Button) findViewById(R.id.button);

        login.setOnClickListener(this);
        signin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
               openActivitySignInPage();
                break;
            case R.id.btn:
                openActivity2();
                break;
        }
    }
    public void openActivity2() {
        Intent openFrom = new Intent(this, LoginScreen.class);
        startActivity(openFrom);
    }
    public void openActivitySignInPage() {
        Intent openFrom = new Intent(this, ChooseRole.class);
        startActivity(openFrom);
    }

}
