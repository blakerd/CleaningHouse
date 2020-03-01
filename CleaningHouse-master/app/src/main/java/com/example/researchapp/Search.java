package com.example.researchapp;

public class Search{
    private List<Property> propertyList;
    String input; //TODO Write method to take in user input for search via a button and textbox

    //TODO add an "OnClick" reference to execute this code


    propertyList = new ArrayList<>();

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Properties");

    //User query by city
    Query cityQuery = reference.orderByChild("City").startAt(input).endAT(input +"\uf8ff");
    cityQuery.addListenerForSingleValueEvent(valueEventListener);

    //User query by price
    Query priceQuery = reference.orderByChild("Cleaning Price").startAt(input).endAT(input +"\uf8ff");
    priceQuery.addListenerForSingleValueEvent(valueEventListener);

    //User query by price
    Query addressQuery = reference.orderByChild("Street Address").startAt(input).endAT(input +"\uf8ff");
    addressQuery.addListenerForSingleValueEvent(valueEventListener);

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
}