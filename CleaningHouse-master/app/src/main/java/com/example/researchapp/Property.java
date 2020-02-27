package com.example.researchapp;

public class Property {
    String name;
    String address;
    String squareFootage;
    String cleaningPrice;

    public void initProperty(String n, String a, String sqft, String c)
    {
        name = n;
        address = a;
        squareFootage = sqft;
        cleaningPrice = c;
    }
}
