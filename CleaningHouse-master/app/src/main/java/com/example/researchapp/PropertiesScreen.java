package com.example.researchapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PropertiesScreen extends AppCompatActivity implements View.OnClickListener {

    String userID;
    TextView propText;
    EditText propNickname;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    Button uploadNewPropertyButton;
    Button displayPropertyButton;
    DrawerLayout drawer;
    NavigationView navigationView;
    TextView username;
    TextView status;
    View header;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        uploadNewPropertyButton = (Button) findViewById(R.id.uploadNewPropertyButton);
        displayPropertyButton = (Button) findViewById(R.id.displayPropertyButton);
        propText = (TextView) findViewById(R.id.Property);
        propNickname = (EditText) findViewById(R.id.propertyNickname);
        toolbar.setTitle("Properties");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setupDrawerContent(navigationView);
        uploadNewPropertyButton.setOnClickListener(this);
        displayPropertyButton.setOnClickListener(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        header = navigationView.getHeaderView(0);
        username = header.findViewById(R.id.username);
        status = header.findViewById(R.id.status);
        mFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uName = mFirebaseUser.getDisplayName();
        if(uName == "") {
            username.setText("No name provided");
        }
        else {
            username.setText(uName);
        }
        userID = mFirebaseUser.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        DatabaseReference ref = mFirebaseDatabase.getReference("Users").child(userID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                status.setText(dataSnapshot.child("Role").getValue(String.class));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.uploadNewPropertyButton:
                nextScreen();
                break;
            case R.id.displayPropertyButton:
                displayProperty();
                break;

        }
    }
    private void nextScreen() {

        Intent i = new Intent(this, UploadNewPropertyScreen.class);
        startActivity(i);

    }
    private void displayProperty() {
       String a = propNickname.getText().toString();

        myRef.child("Users").child(userID).child("Properties").child(a).child("Street Address").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                 String value = dataSnapshot.getValue(String.class);
                propText.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {

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
            case R.id.nav_home://TODO: Change this to CleanersHomeScreen if status == cleaner
                Intent g = new Intent(this, HomeScreen.class);
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
            case R.id.nav_message:
                Intent l = new Intent(this, MessageScreen.class);
                startActivity(l);
                break;
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
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
  /*  protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);

    }

   */
}
