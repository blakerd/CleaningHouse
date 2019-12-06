package com.example.researchapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeScreenHost extends AppCompatActivity implements View.OnClickListener {
    Button currentProperties;
    Button viewMessages;
    Button upcomingCleanings;
    Button billsReceipts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_host);
        currentProperties = (Button) findViewById(R.id.currentProperties);
        viewMessages = (Button) findViewById(R.id.viewMessages);
        upcomingCleanings = (Button) findViewById(R.id.upcomingCleanings);
        billsReceipts = (Button) findViewById(R.id.billsReceipts);
        currentProperties.setOnClickListener(this);
        viewMessages.setOnClickListener(this);
        upcomingCleanings.setOnClickListener(this);
        billsReceipts.setOnClickListener(this);
    }
    public void onClick(View v) {
      switch(v.getId()){
          case R.id.currentProperties:
            propertiesPage();
            break;
          case R.id.viewMessages:
              messagePage();
              break;
          case R.id.upcomingCleanings:
              schedulePage();
              break;
          case R.id.billsReceipts:
              billingPage();
              break;
      }
    }
    public void propertiesPage(){
        Intent i = new Intent(this, PropertiesScreen.class);
        startActivity(i);
    }
    public void schedulePage() {
        Intent i = new Intent(this, ScheduleScreen.class);
        startActivity(i);
    }
    public void billingPage(){
        Intent i = new Intent(this, BillingScreen.class);
        startActivity(i);

    }
    public void messagePage(){
        Intent i = new Intent(this, MessageScreen.class);
        startActivity(i);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}