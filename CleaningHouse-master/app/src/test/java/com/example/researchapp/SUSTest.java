package com.example.researchapp;
import android.content.Context;
import android.graphics.Path;
import android.widget.TextView;


import org.junit.Test;
import org.junit.internal.runners.TestMethod;

import java.io.Console;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class SUSTest {
    @Test
    public void userNameTest() {
        SignUpScreen hST = new SignUpScreen ();

        TextView result = hST.fullName;

        assertEquals(null, result);

    }
    @Test
    public void locationTest() {
        SignUpScreen hST = new SignUpScreen ();

        TextView result = hST.location;

        assertEquals(null, result);

    }
}