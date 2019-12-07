package com.example.researchapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ChooseRole extends AppCompatActivity implements View.OnClickListener {
    Button cleanerBtn;
    Button hostBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
        cleanerBtn = (Button) findViewById(R.id.cleanerBtn);
        hostBtn = (Button) findViewById(R.id.hostBtn);
        cleanerBtn.setOnClickListener(this);
        hostBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.cleanerBtn:
                nextPage();
                break;
            case R.id.hostBtn:
                nextPage();
                break;
        }
    }
    private void nextPage() {

       Intent i = new Intent(this, HomeScreenHost.class);
        startActivity(i);

    }
}