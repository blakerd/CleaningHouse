package com.example.researchapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.Tag;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.util.Log;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PropertiesScreen extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MyActivity";
    String userID;
    TextView propTextHeader;
    TextView propText1;
    TextView propText2;
    TextView propText3;
    TextView propText4;
    TextView propText5;
    EditText propNickname;
    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;
    private DatabaseReference Ref2;
    Button uploadNewPropertyButton;
    Button deletePropertyButton;
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
        deletePropertyButton = (Button) findViewById(R.id.deletePropertyButton);

        propTextHeader = (TextView) findViewById(R.id.PropertiesHeader);


        toolbar.setTitle("Properties");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        uploadNewPropertyButton.setOnClickListener(this);
        deletePropertyButton.setOnClickListener(this);
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
        displayProperties();

        DatabaseReference ref = mFirebaseDatabase.getReference("Users").child(userID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                status.setText(dataSnapshot.child("Role").getValue(String.class));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setupDrawerContent(navigationView);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.uploadNewPropertyButton:
                nextScreen();
                break;
            case R.id.deletePropertyButton:
                goToDeletePropertyScreen();
                break;

        }
    }
    private void nextScreen() {

        Intent i = new Intent(this, UploadNewPropertyScreen.class);
        startActivity(i);

    }
    private void goToDeletePropertyScreen() {

        Intent i = new Intent(this, DeletePropertyScreen.class);
        startActivity(i);

    }

    //display up to five properties
    public void displayProperties() {

        myRef.child("Users").child(userID).child("Properties").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long lngNumProps = dataSnapshot.getChildrenCount();
                int intNumProps = (int) lngNumProps;
                String numPString = new String();
                numPString = numPString.valueOf(lngNumProps);
                Log.println(Log.INFO,"PropScreenLog",numPString);
                TextView[] textViews = new TextView[intNumProps];
                TextView temp;

                TextView tv = (TextView) findViewById(R.id.PropertiesHeader);


                int i = 0;

                int left = 0;
                int top = 0;
                int right = 0;
                int bottom = 0;
                for (DataSnapshot data : dataSnapshot.getChildren())
                {
                    String strStreetAddress = data.child("Street Address").getValue(String.class);
                    temp = new TextView(PropertiesScreen.this);
                    temp.setPaddingRelative(left, top+=60, right, bottom);
                    temp.setGravity(Gravity.CENTER);
                    temp.setAllCaps(true);
                    temp.setTextColor(Color.parseColor("#000000"));
                    temp.setText(strStreetAddress);

                    Log.println(Log.INFO,"dataLog", strStreetAddress);

                    //add the textview to the relativelayout
                    RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rLayout);

                    relativeLayout.addView(temp);

                    textViews[i] = temp;
                    ++i;
                }

//                int j = 0;
//                for ( DataSnapshot data : dataSnapshot.getChildren())
//                {
//                    ++j;
//                    String strStreetAddress = data.child("Street Address").getValue(String.class);
//                    textViews[j].setText(strStreetAddress);
//                }

//                int i = 0;
//                propText1 = (TextView) findViewById(R.id.Property1);
//                propText2 = (TextView) findViewById(R.id.Property2);
//                propText3 = (TextView) findViewById(R.id.Property3);
//                propText4 = (TextView) findViewById(R.id.Property4);
//                propText5 = (TextView) findViewById(R.id.Property5);
//                for (DataSnapshot data : dataSnapshot.getChildren()) {
//                    String value = data.child("Street Address").getValue(String.class);
//                    i++;
//                    if(i == 1)
//                    {
//                        propText1.setText(value);
//                    }
//                    else if(i == 2)
//                    {
//                        propText2.setText(value);
//                    }
//                    else if(i == 3)
//                    {
//                        propText3.setText(value);
//                    }
//                    else if(i == 4)
//                    {
//                        propText4.setText(value);
//                    }
//                    else if(i == 5)
//                    {
//                        propText5.setText(value);
//                    }
//                    //propList.add(value);
//
//                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(PropertiesScreen.this, "outer loop fail", Toast.LENGTH_SHORT).show();
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
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

}
