package com.example.researchapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;

public class CleanersHomeScreen extends AppCompatActivity implements View.OnClickListener {
    Button currentProperties;
    Button viewMessages;
    Button upcomingCleanings;
    Button billsReceipts;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaners_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        currentProperties = (Button) findViewById(R.id.currentListings);
        viewMessages = (Button) findViewById(R.id.viewMessages);
        upcomingCleanings = (Button) findViewById(R.id.upcomingCleanings);
        billsReceipts = (Button) findViewById(R.id.billsReceipts);
        currentProperties.setOnClickListener(this);
        viewMessages.setOnClickListener(this);
        upcomingCleanings.setOnClickListener(this);
        billsReceipts.setOnClickListener(this);
    }
    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.currentListings:
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