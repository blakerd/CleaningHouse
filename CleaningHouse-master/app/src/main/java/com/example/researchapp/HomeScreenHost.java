package com.example.researchapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeScreenHost extends AppCompatActivity implements View.OnClickListener {
    Button logOutBtn;
    FirebaseAuth mFirebaseAuth;
    Button currentProperties;
    Button viewMessages;
    Button upcomingCleanings;
    Button billsReceipts;
    DrawerLayout drawer;
    NavigationView navigationView;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen_host);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        /*if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            drawer.openDrawer(GravityCompat.START);
        }*/

        setupDrawerContent(navigationView);
        logOutBtn = findViewById(R.id.logOut);
        currentProperties = (Button) findViewById(R.id.currentProperties);
        viewMessages = (Button) findViewById(R.id.viewMessages);
        upcomingCleanings = (Button) findViewById(R.id.upcomingCleanings);
        billsReceipts = (Button) findViewById(R.id.billsReceipts);
        currentProperties.setOnClickListener(this);
        viewMessages.setOnClickListener(this);
        upcomingCleanings.setOnClickListener(this);
        billsReceipts.setOnClickListener(this);
      
        Toast.makeText(HomeScreenHost.this, "Welcome" , Toast.LENGTH_SHORT).show();

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.getInstance().signOut();
                Intent backToHome = new Intent(HomeScreenHost.this, OpeningScreen.class);
                startActivity(backToHome);
            }
        });
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_profile:
                Intent h = new Intent(this, Profile.class);
                startActivity(h);
                break;
            case R.id.nav_schedule:
                Intent i = new Intent(this, ScheduleScreen.class);
                startActivity(i);
                break;
            case R.id.property:
                Intent j = new Intent(this, PropertiesScreen.class);
                startActivity(j);
                break;
            case R.id.listings:

                break;
            case R.id.nav_message:
                Intent l = new Intent(this, MessageScreen.class);
                startActivity(l);
                break;
            case R.id.transactions:
                Intent m = new Intent(this, BillingScreen.class);
                startActivity(m);
                break;
            case R.id.contacts:

                break;
            case R.id.termsOfService:
                Intent o = new Intent(this, terms_and_conditions_page.class);
                startActivity(o);
                break;
                default:

        }
        LayoutInflater inflater = getLayoutInflater();
        LinearLayout container = (LinearLayout) findViewById(R.id.content_frame);
        inflater.inflate(R.layout.activity_home_screen_host, container);
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer.closeDrawers();
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