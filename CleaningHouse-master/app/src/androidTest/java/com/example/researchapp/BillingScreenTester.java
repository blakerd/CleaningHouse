package com.example.researchapp;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.isSelected;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class BillingScreenTester {

    @Rule
    public ActivityTestRule<BillingScreen> mActivityTestRule = new ActivityTestRule<>(BillingScreen.class);


    // final EditText nameEditText =
    // (EditText) activity.findViewById(R.id.emailButton);
    @Test
    public void billingScreenTester() {

        //OpeningScreen activity = new OpeningScreen();
        //EditText emailInputTest = activity.findViewById(R.id.emailButton);
        //emailInputTest.typeText("Test@email.sc.edu");


        onView(withId(R.id.cardNumberEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.cardNumberEditText)).check(matches(isEnabled()));
        onView(withId(R.id.cardNumberEditText)).check(matches(isClickable()));
        onView(withId(R.id.CVCEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.CVCEditText)).check(matches(isEnabled()));
        onView(withId(R.id.CVCEditText)).check(matches(isClickable()));
        onView(withId(R.id.dateEditText)).check(matches(isDisplayed()));
        onView(withId(R.id.dateEditText)).check(matches(isEnabled()));
        onView(withId(R.id.dateEditText)).check(matches(isClickable()));


    }



}