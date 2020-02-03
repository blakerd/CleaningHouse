package com.example.researchapp;

import org.junit.Test;
import android.content.Intent;

import static org.junit.Assert.*;
import androidx.appcompat.app.AppCompatActivity;
public class ChooseRoleTest {
    @Test
    public void onClick() {
        ChooseRole activity = com.example.researchapp.ChooseRole;
        activity.findViewById(R.id.hostBtn).performClick();
        Intent expectedIntent = new Intent(this, SignUpScreen.class);
        assertEquals(expectedIntent,activity.getSupportParentActivityIntent();
       //attempting to compare nextPageHost()'s Intent to the expected intent
        //not sure how to get the actual returned Intent to compare to the expected Intent
        //may need to utilize Espresso-intents or Mockito
    }
}