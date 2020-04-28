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

public class SignUpScreenTest {
    @Rule
    public ActivityTestRule<SignUpScreen> mActivityTestRule = new ActivityTestRule<>(SignUpScreen.class);


    // final EditText nameEditText =
    // (EditText) activity.findViewById(R.id.emailButton);
    @Test
    public void openingScreenTest() {

        //OpeningScreen activity = new OpeningScreen();
        //EditText emailInputTest = activity.findViewById(R.id.emailButton);
        //emailInputTest.typeText("Test@email.sc.edu");
        onView(withId(R.id.name)).check(matches(isDisplayed()));
        onView(withId(R.id.name)).check(matches(isEnabled()));
        onView(withId(R.id.cityState)).check(matches(isDisplayed()));
        onView(withId(R.id.cityState)).check(matches(isEnabled()));
        onView(withId(R.id.imageview_account_profile)).check(matches(isDisplayed()));
        onView(withId(R.id.imageview_account_profile)).check(matches(isEnabled()));
        onView(withId(R.id.makeAccBtn)).check(matches(isClickable()));
        onView(withId(R.id.makeAccBtn)).check(matches(isEnabled()));
        onView(withId(R.id.roleToggle)).check(matches(isClickable()));
        onView(withId(R.id.roleToggle)).check(matches(isEnabled()));



    }

}
