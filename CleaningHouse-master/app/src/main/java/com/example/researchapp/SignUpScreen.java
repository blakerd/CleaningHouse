package com.example.researchapp;
//tonys comment
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.*;

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
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        mFirebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

        userID = mFirebaseUser.getUid();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        reference = mFirebaseDatabase.getReference();

        mStorageRef = FirebaseStorage.getInstance().getReference();


        fullName =  (EditText) findViewById(R.id.name);
        location = (EditText) findViewById(R.id.cityState);
        profilePic = (CircleImageView) findViewById(R.id.imageview_account_profile);
        makeAccBtn = (Button) findViewById(R.id.makeAccBtn);


        makeAccBtn.setOnClickListener(this);

        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Image"), 101);
            }
        });
    }





    @Override
    public void onClick(View v) {
            switch(v.getId()) {
                case R.id.makeAccBtn:
                fName = fullName.getText().toString();
                loc = location.getText().toString();
                reference.child("Users").child(userID).child("Full Name").setValue(fName);
                reference.child("Users").child(userID).child("Location").setValue(loc);
                //yet to implement picture storage
                Intent i = new Intent(SignUpScreen.this, HomeScreenHost.class);
                startActivity(i);
                break;
            }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK && data !=null) {
            Uri uri = data.getData();
            String path = getURIPath(this, uri);
            String selectedImage = getFileName(uri);
           // profilePic.setImageURI(selectedImage);
        }
    }

    private String getFileName(Uri uri)
    {
        String result = null;
        if(uri.getScheme().equals("content"))
        {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            try {
                    if(cursor != null && cursor.moveToFirst())
                    {
                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
            }
            finally {
                cursor.close();
            }
        }
        if(result == null)
        {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if(cut != -1)
            {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    private String getURIPath(Context context, Uri uri)
    {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);

        if(cursor != null)
        {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);

        }
        return null;
    }

}
