package com.example.researchapp;
//tonys comment
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import androidx.appcompat.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;
import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpScreen extends AppCompatActivity implements View.OnClickListener {
    private static final int IMAGE = 1;
    EditText fullName;
    EditText location;
    CircleImageView profilePic;

    Button makeAccBtn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        mAuth = FirebaseAuth.getInstance();
        fullName =  (EditText) findViewById(R.id.name);
        location = (EditText) findViewById(R.id.cityState);
        profilePic = (CircleImageView) findViewById(R.id.imageview_account_profile);
        makeAccBtn = (Button) findViewById(R.id.makeAccBtn);

        profilePic.setOnClickListener(this);
        makeAccBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.imageview_account_profile:
                Intent p = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(p, IMAGE);
                break;
            case R.id.makeAccBtn:
                Intent i = new Intent(this, HomeScreenHost.class);
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
