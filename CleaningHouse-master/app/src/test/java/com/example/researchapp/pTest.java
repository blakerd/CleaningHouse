package com.example.researchapp;
import android.content.Context;
import android.graphics.Path;
import android.widget.TextView;


import org.junit.Test;
import org.junit.internal.runners.TestMethod;

import java.io.Console;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class pTest {
    @Test
    public void userNameTest() {
        Profile hST = new Profile();

        TextView result = hST.username;

        assertEquals(null, result);

    }
    @Test
    public void emailTest() {
        Profile hST = new Profile();

        TextView result = hST.status;

        assertEquals(null, result);

    }
}