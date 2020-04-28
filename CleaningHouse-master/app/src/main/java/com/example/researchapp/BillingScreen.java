package com.example.researchapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.common.api.internal.DataHolderNotifier;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.renderscript.Sampler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BillingScreen extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView navigationView;
    FirebaseDatabase db;
    FirebaseUser fbuser;
    TextView username;
    TextView status;
    TextView cardNum;
    TextView cvc;
    TextView date;
    String cardNumber;
    String cvcNum;
    String cardDate;
    Button uploadButton;
    View header;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_billing_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Transactions");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        header = navigationView.getHeaderView(0);
        username = header.findViewById(R.id.username);
        status = header.findViewById(R.id.status);
        cardNum = findViewById(R.id.cardNumberEditText);
        cvc = findViewById(R.id.CVCEditText);
        date = findViewById(R.id.dateEditText);
        db = FirebaseDatabase.getInstance();
        fbuser = FirebaseAuth.getInstance().getCurrentUser();
        myRef = db.getReference();
        String uName = fbuser.getDisplayName();

        if(uName == "" ) {
            username.setText("No name provided");
        }
        else {
            username.setText(uName);
        }
        DatabaseReference reference = db.getReference("Users").child(fbuser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                status.setText(dataSnapshot.child("Role").getValue(String.class));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        cardNum.addTextChangedListener(new TextWatcher() {
            private static final char space = ' ';

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Remove all spacing char
                int pos = 0;
                while (true) {
                    if (pos >= s.length()) break;
                    if (space == s.charAt(pos) && (((pos + 1) % 5) != 0 || pos + 1 == s.length())) {
                        s.delete(pos, pos + 1);
                    } else {
                        pos++;
                    }
                }

                // Insert char where needed.
                pos = 4;
                while (true) {
                    if (pos >= s.length()) break;
                    final char c = s.charAt(pos);
                    // Only if its a digit where there should be a space we insert a space
                    if ("0123456789".indexOf(c) >= 0) {
                        s.insert(pos, "" + space);
                    }
                    pos += 5;
                }
            }
        });
        displayInfo();
        uploadButton = (Button) findViewById(R.id.uploadButton);
        //  selectImageButton.setOnClickListener(this);
        View.OnClickListener l = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              switch(v.getId()){
                  case R.id.uploadButton:
                      cardNumber = cardNum.getText().toString();
                      cvcNum = cvc.getText().toString();
                      cardDate = date.getText().toString();
                      myRef.child("Users").child(fbuser.getUid()).child("Payment").child("Card Number:").setValue(cardNumber);
                      myRef.child("Users").child(fbuser.getUid()).child("Payment").child("CVC:").setValue(cvcNum);
                      myRef.child("Users").child(fbuser.getUid()).child("Payment").child("Exp Date:").setValue(cardDate);
              }
            }
        };
        uploadButton.setOnClickListener(l);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        setupDrawerContent(navigationView);
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
    public void displayInfo()
    {
        myRef.child("Users").child(fbuser.getUid()).child("Payment").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                TextView cardNumb = findViewById(R.id.cardNumView);
                TextView cvcNumb = findViewById(R.id.CVCView);
                TextView dateNumb = findViewById(R.id.dateView);
                String cardValue = dataSnapshot.child("Card Number:").getValue(String.class);
                String cvcValue = dataSnapshot.child("CVC:").getValue(String.class);
                String dateValue = dataSnapshot.child("Exp Date:").getValue(String.class);
                if(cardValue == null || cvcValue == null || dateValue == null){
                    cardNumb.setText("None found");
                    cvcNumb.setText("N/A");
                    dateNumb.setText("N/A");
                }
                else{
                    cardNumb.setText(cardValue);
                    cvcNumb.setText(cvcValue);
                    dateNumb.setText(dateValue);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
