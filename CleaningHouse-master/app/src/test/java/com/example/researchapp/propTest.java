package com.example.researchapp;
import android.content.Context;
import android.graphics.Path;
import android.widget.TextView;


import org.junit.Test;
import org.junit.internal.runners.TestMethod;

import java.io.Console;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class propTest {
    @Test
    public void userNameTest() {
        PropertiesScreen hST = new PropertiesScreen ();

        TextView result = hST.username;

        assertEquals(null, result);

    }
    @Test
    public void statTest() {
        PropertiesScreen hST = new PropertiesScreen ();

        TextView result = hST.status;

        assertEquals(null, result);

    }
}