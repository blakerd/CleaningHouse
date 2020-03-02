package com.example.researchapp;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.TextView;

public class CleanersProperties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaners_properties);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView mTitleWindow = (TextView) findViewById(R.id.titleWindow);
        TextView mMessageWindow = (TextView) findViewById(R.id.messageWindow);

        mTitleWindow.setText("This is some title");

        StringBuilder stringBuilder = new StringBuilder();

        String someMessage = "This is some message that will be appended.";

        for (int i = 0; i < 100; ++i)
        {
            stringBuilder.append(someMessage);
        }
        mMessageWindow.setText(stringBuilder.toString());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
