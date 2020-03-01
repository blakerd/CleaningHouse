package com.example.researchapp;

public class Search{
    String input; //TODO Write method to take in user input for search via a button and textbox
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Properties");
    reference.addListenerForSingleValueEvent(valueEventListener);

    //User query by city
    Query cityQuery = reference.orderByChild("City").equalTo(input);

    //User query by price
    Query cityQuery = reference.orderByChild("Cleaning Price").equalTo(input);

    //User query by price
    Query cityQuery = reference.orderByChild("Street Address").equalTo(input);

}