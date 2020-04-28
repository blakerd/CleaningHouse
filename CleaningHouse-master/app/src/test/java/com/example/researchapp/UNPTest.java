package com.example.researchapp;
import android.content.Context;
import android.graphics.Path;
import android.widget.TextView;


import org.junit.Test;
import org.junit.internal.runners.TestMethod;

import java.io.Console;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class UNPTest {
    @Test
    public void priceTest() {
        UploadNewPropertyScreen hST = new UploadNewPropertyScreen();

        String result = hST.cleaningPrice;

        assertEquals(null, result);

    }
    @Test
    public void propertyNameTest() {
        UploadNewPropertyScreen hST = new UploadNewPropertyScreen();

        String result = hST.propertyName;

        assertEquals(null, result);

    }
}