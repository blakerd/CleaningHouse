package com.example.researchapp;

import android.content.Intent;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginScreenTest {

    @Test
    public void nextScreen(){
        LoginScreen loginScreen = new LoginScreen();
        HomeScreenHost HSH = new HomeScreenHost();
        int expected = 1;
        int output;
        output = loginScreen.check;
        assertEquals(expected, output);
    }
}