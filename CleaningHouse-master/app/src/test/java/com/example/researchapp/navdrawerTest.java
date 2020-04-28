package com.example.researchapp;

import android.content.Intent;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class navdrawerTest {

    @Test
    public void headerView() {
        HomeScreen home = new HomeScreen();
        TextView expected = home.status;
        assertEquals(null,expected);
    }
}
