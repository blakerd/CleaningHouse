package com.example.researchapp;
import android.content.Context;
import android.graphics.Path;
import android.widget.TextView;


import org.junit.Test;
import org.junit.internal.runners.TestMethod;

import java.io.Console;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class logTest {
    @Test
    public void sITest() {
        LoginScreen hST = new LoginScreen();

        TextView result = hST.tvSignIn;

        assertEquals(null, result);

    }
    @Test
    public void pswTest() {
        LoginScreen hST = new LoginScreen();

        TextView result = hST.pswRecover;

        assertEquals(null, result);

    }
}