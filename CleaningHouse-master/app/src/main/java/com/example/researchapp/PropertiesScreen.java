package com.example.researchapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;
import android.view.View;

public class PropertiesScreen extends AppCompatActivity implements View.OnClickListener {

    Button uploadNewPropertyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        uploadNewPropertyButton = (Button) findViewById(R.id.uploadNewPropertyButton);
        setSupportActionBar(toolbar);
        uploadNewPropertyButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.uploadNewPropertyButton:
                nextScreen();
                break;
        }
    }
    private void nextScreen() {

        Intent i = new Intent(this, UploadNewPropertyScreen.class);
        startActivity(i);

    }
}
