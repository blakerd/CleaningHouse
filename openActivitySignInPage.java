package com.example.researchapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class openActivitySignInPage extends AppCompatActivity implements View.OnClickListener {
    Button cleanerBtn;
    Button hostBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_sign_in_page);
        cleanerBtn = (Button) findViewById(R.id.cleanerBtn);
        hostBtn = (Button) findViewById(R.id.hostBtn);
        cleanerBtn.setOnClickListener(this);
        hostBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cleanerBtn:
                finalStage();
                break;
            case R.id.hostBtn:
                finalStage();
                break;
        }
    }
    private void finalStage() {

       Intent i = new Intent(this, finalStage.class);
        startActivity(i);

    }
}