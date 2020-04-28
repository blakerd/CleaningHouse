package com.example.researchapp;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isEnabled;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class ProfileTester {
    @Rule
    public ActivityTestRule<Profile> mActivityTestRule = new ActivityTestRule<>(Profile.class);


    // final EditText nameEditText =
    // (EditText) activity.findViewById(R.id.emailButton);
    @Test
    public void profileTester() {

        //OpeningScreen activity = new OpeningScreen();
        //EditText emailInputTest = activity.findViewById(R.id.emailButton);
        //emailInputTest.typeText("Test@email.sc.edu");

        onView(withId(R.id.userName)).check(matches(isDisplayed()));
        onView(withId(R.id.userName)).check(matches(isEnabled()));
        onView(withId(R.id.emailTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.emailTextView)).check(matches(isEnabled()));
        onView(withId(R.id.roleTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.roleTextView)).check(matches(isEnabled()));
        //onView(withId(R.id.upcomingCleanings)).check(matches(isClickable()));
        onView(withId(R.id.locationTextView)).check(matches(isDisplayed()));
        onView(withId(R.id.locationTextView)).check(matches(isEnabled()));
        //onView(withId(R.id.billsReceipts)).check(matches(isClickable()));



    }

}
