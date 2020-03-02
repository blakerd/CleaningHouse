package com.example.researchapp;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.*;

import java.util.ArrayList;


public class Search extends AppCompatActivity implements View.OnClickListener{
    ArrayList<Property> propertyList;

    String input;

    String city;
    String cleaningPrice;
    String streetAddress;

    EditText cityNameInput;
    EditText cleaningPriceInput;
    EditText streetAddressInput;

    Button searchButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //TODO Write method to take in user input for search via a button and textbox

        //TODO add an "OnClick" reference to execute this code

        cityNameInput = (EditText) findViewById(R.id.city);
        cleaningPriceInput = (EditText) findViewById(R.id.cleaningPrice);
        streetAddressInput = (EditText) findViewById(R.id.streetAddress);

        searchButton = (Button) findViewById(R.id.searchButton);

        searchButton.setOnClickListener(this);

    }
    public void onClick(View v) {
        switch(v.getId()){

            // case R.id.selectImageButton:
            // uploadNewPropertyPage();
            //  break;

            case R.id.searchButton:
                city = cityNameInput.getText().toString();
                cleaningPrice = cleaningPriceInput.getText().toString();
                streetAddress = streetAddressInput.getText().toString();
                propertyList = new ArrayList<>();


                ValueEventListener valueEventListener = new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot){
                        if(dataSnapshot.exists()){
                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                Property property = snapshot.getValue(Property.class);
                                propertyList.add(property);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Properties");

                //User query by city
                Query cityQuery = reference.orderByChild("City").startAt(input).endAt(input +"\uf8ff");
                cityQuery.addListenerForSingleValueEvent(valueEventListener);

                //User query by price
                Query priceQuery = reference.orderByChild("Cleaning Price").startAt(input).endAt(input +"\uf8ff");
                priceQuery.addListenerForSingleValueEvent(valueEventListener);

                //User query by price
                Query addressQuery = reference.orderByChild("Street Address").startAt(input).endAt(input +"\uf8ff");
                addressQuery.addListenerForSingleValueEvent(valueEventListener);
                break;
        }
    }
}