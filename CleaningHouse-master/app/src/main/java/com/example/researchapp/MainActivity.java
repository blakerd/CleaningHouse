package com.example.researchapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //private ImageView puroLogo;
    //blake changed the code here
    private ImageView login;
    private ImageView signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = (ImageView) findViewById(R.id.btn);
        signin = (ImageView) findViewById(R.id.button);

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
        Intent openFrom = new Intent(this, com.example.researchapp.MainActivity2.class);
        startActivity(openFrom);
    }
    public void openActivitySignInPage() {
        Intent openFrom = new Intent(this, openActivitySignInPage.class);
        startActivity(openFrom);
    }

}
