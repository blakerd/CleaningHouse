package com.example.researchapp;
import android.content.Context;
import android.graphics.Path;
import android.widget.EditText;
import android.widget.TextView;


import com.example.researchapp.HomeScreen;

import org.junit.Test;
import org.junit.internal.runners.TestMethod;

import java.io.Console;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class BillTest {
    @Test
    public void cardNumberTest() {
        BillingScreen bST = new BillingScreen();

        String result = bST.cardNumber;

        assertEquals(null, result);

    }
    @Test
    public void emailTest() {
        BillingScreen bST = new BillingScreen();

        TextView result = bST.status;

        assertEquals(null, result);

    }
}