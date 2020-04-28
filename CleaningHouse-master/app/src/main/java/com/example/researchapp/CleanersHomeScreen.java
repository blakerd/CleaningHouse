package com.example.researchapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.core.view.GravityCompat;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CleanersHomeScreen extends AppCompatActivity implements View.OnClickListener {
    Button currentProperties;
    //Button viewMessages;
    Button upcomingCleanings;
    Button billsReceipts;
    Button logOutBtn;
    DrawerLayout drawer;
    TextView username;
    TextView status;
    View header;
    FirebaseUser fbuser;
    FirebaseDatabase db;
    FirebaseAuth mFirebaseAuth;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaners_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Cleaner Home Screen");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        header = navigationView.getHeaderView(0);
        username = header.findViewById(R.id.username);
        status = header.findViewById(R.id.status);
        fbuser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseDatabase.getInstance();
        setupDrawerContent(navigationView);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(fbuser == null)
            reference = db.getReference("Users").child("");
            //username.setText(fbuser.getUid()); we set the username below
        else
            reference = db.getReference("Users").child(fbuser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                if(fbuser.getDisplayName() == "")
                {
                    username.setText("No name provided");
                }
                else {
                    username.setText(fbuser.getDisplayName());
                }
                status.setText(dataSnapshot.child("Role").getValue(String.class));
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        currentProperties = (Button) findViewById(R.id.currentProperties);
        //viewMessages = (Button) findViewById(R.id.viewMessages);
        upcomingCleanings = (Button) findViewById(R.id.upcomingCleanings);
        billsReceipts = (Button) findViewById(R.id.billsReceipts);
        logOutBtn = findViewById(R.id.logOut);
        currentProperties.setOnClickListener(this);
        //viewMessages.setOnClickListener(this);
        upcomingCleanings.setOnClickListener(this);
        billsReceipts.setOnClickListener(this);

        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFirebaseAuth.getInstance().signOut();
                Intent backToHome = new Intent(CleanersHomeScreen.this, OpeningScreen.class);
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
            case R.id.nav_home:
                Intent g = new Intent(this,CleanersHomeScreen.class);
                startActivity(g);
                break;
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
                Intent k = new Intent(this,Listings.class);
                startActivity(k);
                break;
            /*case R.id.nav_message:
                Intent l = new Intent(this, MessageScreen.class);
                startActivity(l);
                break;*/
            case R.id.transactions:
                Intent m = new Intent(this, BillingScreen.class);
                startActivity(m);
                break;
            case R.id.contacts:
                Intent n = new Intent(this,Contacts.class);
                startActivity(n);
                break;
            case R.id.termsOfService:
                Intent o = new Intent(this, terms_and_conditions_page.class);
                startActivity(o);
                break;
            default:

        }
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
                listings();
                break;
            /*case R.id.viewMessages:
                messagePage();
                break;*/
            case R.id.upcomingCleanings:
                schedulePage();
                break;
            case R.id.billsReceipts:
                billingPage();
                break;
        }
    }
    public void listings(){
        Intent i = new Intent(this, Listings.class);
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
    /*public void messagePage(){
        Intent i = new Intent(this, MessageScreen.class);
        startActivity(i);

    }*/

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}