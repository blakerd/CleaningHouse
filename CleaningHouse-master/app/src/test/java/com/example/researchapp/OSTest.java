package com.example.researchapp;
import android.content.Context;
import android.graphics.Path;


import org.junit.Test;
import org.junit.internal.runners.TestMethod;

import java.io.Console;
import java.io.StringWriter;

import static org.junit.Assert.*;

public class OSTest {
    @Test
    public void simpleSuccessTest() {
    OpeningScreen oST = new OpeningScreen();

    String result = oST.isSuccessful();

    assertEquals("This Screen is a great success!", result);

    }
}