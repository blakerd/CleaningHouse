package com.example.researchapp;
import android.content.Context;
import android.graphics.Path;
import android.widget.TextView;


import org.junit.Test;
import org.junit.internal.runners.TestMethod;

import java.io.Console;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class DPTest {
    @Test
    public void userIDTest() {
        DeletePropertyScreen hST = new DeletePropertyScreen();

        String result = hST.userID;

        assertEquals(null, result);

    }
    @Test
    public void streetAddrTest() {
        DeletePropertyScreen hST = new DeletePropertyScreen();

        TextView result = hST.streetAddressInput;

        assertEquals(null, result);

    }
}