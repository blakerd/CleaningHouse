package com.example.researchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;

import com.google.android.material.navigation.NavigationView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.FileDownloadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
    String userID;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private FirebaseStorage mFirebaseStorage;
    private StorageReference mStorageRef;
    CircleImageView avatarIv;
    DrawerLayout drawer;
    NavigationView navigationView;
    View header;
    TextView username;
    TextView status;
    TextView nameTv, emailTv, locationTv, roleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Profile");
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        header = navigationView.getHeaderView(0);
        username = header.findViewById(R.id.username);
        status = header.findViewById(R.id.status);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        String uName = user.getDisplayName();
        userID = user.getUid();

        avatarIv = (CircleImageView) findViewById(R.id.imageProfile);


        nameTv = (TextView)findViewById(R.id.userName);
        emailTv = (TextView)findViewById(R.id.emailTextView);
        roleTv= (TextView) findViewById(R.id.roleTextView);
        locationTv= (TextView) findViewById(R.id.locationTextView);

        mFirebaseStorage = FirebaseStorage.getInstance();
        mStorageRef = mFirebaseStorage.getReference();

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference("Users").child(userID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    String name = "" + dataSnapshot.child("Full Name").getValue(String.class);
                   String email = "" + dataSnapshot.child("Email").getValue();
                    String role = "" + dataSnapshot.child("Role").getValue(String.class);
                    String location = "" + dataSnapshot.child("Location").getValue(String.class);

                    nameTv.setText(name);
                    emailTv.setText(email);
                    roleTv.setText(role);
                    locationTv.setText(location);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*

        try {
            File tempFile = File.createTempFile("ProfilePic",".jpg");
            StorageReference r = mStorageRef.child("Images");//.child("Profile Pictures").child("testing");
            String l = r.toString();

            Log.println(Log.INFO,"profTag", l);
            r.getFile(tempFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Log.println(Log.INFO,"profTag", "Download success");
                            Toast.makeText(Profile.this, "success", Toast.LENGTH_SHORT).show();



                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.println(Log.INFO,"profTag", "Failed to download pic");
                    Toast.makeText(Profile.this, "Failed to grab pic", Toast.LENGTH_SHORT).show();


                }
            });
            String absolutePath = tempFile.getAbsolutePath();
            String tempFilePath = absolutePath.
                    substring(0,absolutePath.lastIndexOf(File.separator));
            tempFilePath = tempFilePath + "/";
            String photoPath;
            photoPath = tempFile.getAbsolutePath();
            Log.println(Log.INFO,"profTag", photoPath);
            Log.println(Log.INFO,"profTag", tempFilePath);
            Bitmap photo;
            photo = BitmapFactory.decodeFile(tempFile.getAbsolutePath());
            avatarIv.setImageBitmap(photo);

            String photoPath;
            photoPath = tempFile.getPath();
            Log.println(Log.INFO,"profTag", photoPath);
            Bitmap photo;
            photo = BitmapFactory.decodeFile(photoPath);
            Log.println(Log.INFO,"profTag", photo.toString());
            avatarIv.setImageBitmap(photo);





            //gs://chauthentication.appspot.com/Images/Profile Pictures/lf8TMNGCCrcEwcUiefeIpHlnJZC3
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        if(uName == "") {
            username.setText("No name provided");
        }
        else {
            username.setText(uName);
        }

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
                Intent k = new Intent(this, Listings.class);
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
                Intent n = new Intent(this, Contacts.class);
                startActivity(n);
                break;
            case R.id.termsOfService:
                Intent o = new Intent(this, terms_and_conditions_page.class);
                startActivity(o);
                break;
            default:

        }
    }
}