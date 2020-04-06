package com.example.researchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class CleanersProperties extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaners_properties);
        TextView mTitleWindow = (TextView) findViewById(R.id.titleWindow);
        TextView mMessageWindow = (TextView) findViewById(R.id.messageWindow);

        mTitleWindow.setText("Properties will display here: ");
        StringBuilder stringBuilder = new StringBuilder();
        String someMessage = " This will be a property's title, wage, and square footage. ";
        for(int i = 0; i < 100; ++i)
        {
            stringBuilder.append(someMessage);
        }

        mMessageWindow.setText(stringBuilder.toString());
    }
}
