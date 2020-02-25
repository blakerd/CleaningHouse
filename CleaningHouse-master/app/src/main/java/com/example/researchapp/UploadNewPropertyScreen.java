
package com.example.researchapp;

import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
 import androidx.appcompat.app.AppCompatActivity;
 import androidx.appcompat.widget.Toolbar;

public class UploadNewPropertyScreen extends AppCompatActivity implements View.OnClickListener {

    String propertyName;
    String propertyAddress;
    String squareFootage;
    String cleaningPrice;

    EditText propertyNameInput;
    EditText propertyAddressInput;
    EditText squareFootageInput;
    EditText cleaningPriceInput;

    Button selectImageButton;
    Button uploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_new_property_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        propertyNameInput =  (EditText) findViewById(R.id.propertyName);
        propertyAddressInput = (EditText) findViewById(R.id.propertyAddress);
        squareFootageInput = (EditText) findViewById(R.id.squareFootage);
        cleaningPriceInput = (EditText) findViewById(R.id.cleaningPrice);

        selectImageButton = (Button) findViewById(R.id.selectImageButton);
        uploadButton = (Button) findViewById(R.id.uploadButton);
        selectImageButton.setOnClickListener(this);
        uploadButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.selectImageButton:
                // uploadNewPropertyPage();
                break;
            case R.id.uploadButton:
                propertyName = propertyNameInput.getText().toString();
                propertyAddress = propertyAddressInput.getText().toString();
                squareFootage = squareFootageInput.getText().toString();
                cleaningPrice = cleaningPriceInput.getText().toString();
                Property p = new Property();
                p.initProperty(propertyName,propertyAddress,squareFootage,cleaningPrice);
                break;
        }
    }
}