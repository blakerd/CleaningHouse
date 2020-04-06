package com.example.researchapp;
//tonys comment
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpScreen extends AppCompatActivity implements View.OnClickListener {
    private static final int IMAGE = 1;
    String userID;
    String fName;
    String loc;

    EditText fullName;
    EditText location;

    CircleImageView profilePic;

    Button makeAccBtn;

    Users user;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

        userID = mFirebaseUser.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        reference = mFirebaseDatabase.getReference("Users");

        fullName =  (EditText) findViewById(R.id.name);
        location = (EditText) findViewById(R.id.cityState);
        profilePic = (CircleImageView) findViewById(R.id.imageview_account_profile);
        makeAccBtn = (Button) findViewById(R.id.makeAccBtn);

        user = new Users();

        profilePic.setOnClickListener(this);
        makeAccBtn.setOnClickListener(this);
    }

    private void getValues() {
        user.setUserName(fullName.getText().toString());
        user.setLocation(location.getText().toString());
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.imageview_account_profile:
                Intent p = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(p, IMAGE);
                break;
            case R.id.makeAccBtn:

                fName = fullName.getText().toString();
                loc = location.getText().toString();
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        getValues();
                        reference.child(userID).setValue(user);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                //reference.child("Users").child(userID).child("Name").child(fName).child("Location").setValue(location);
                //reference.child("Users").child(userID).child("Name").setValue(fName);
                Intent i = new Intent(SignUpScreen.this, HomeScreenHost.class);
                startActivity(i);
                break;

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == IMAGE && resultCode == RESULT_OK && data !=null) {
            Uri selectedImage = data.getData();
            profilePic.setImageURI(selectedImage);
        }
    }

}
