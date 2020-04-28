package com.example.researchapp;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
public class UNPSTest {

    @Rule
    public ActivityTestRule<UploadNewPropertyScreen> mActivityTestRule = new ActivityTestRule<>(UploadNewPropertyScreen.class);


    // final EditText nameEditText =
    // (EditText) activity.findViewById(R.id.emailButton);
    @Test
    public void uNPSTest() {

        onView(withId(R.id.uploadButton)).check(matches(isDisplayed()));
        onView(withId(R.id.uploadButton)).check(matches(isEnabled()));
        onView(withId(R.id.uploadButton)).check(matches(isClickable()));
        onView(withId(R.id.listingToggle)).check(matches(isDisplayed()));
        onView(withId(R.id.listingToggle)).check(matches(isEnabled()));
        onView(withId(R.id.listingToggle)).check(matches(isClickable()));
        onView(withId(R.id.propertyName)).check(matches(isDisplayed()));
        onView(withId(R.id.propertyAddress)).check(matches(isDisplayed()));
        onView(withId(R.id.cleaningPrice)).check(matches(isDisplayed()));
        onView(withId(R.id.city)).check(matches(isDisplayed()));
    }



}