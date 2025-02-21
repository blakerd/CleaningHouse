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
public class LoginScreenTest {

    @Rule
    public ActivityTestRule<LoginScreen> mActivityTestRule = new ActivityTestRule<>(LoginScreen.class);


    // final EditText nameEditText =
    // (EditText) activity.findViewById(R.id.emailButton);
    @Test
    public void loginScreenTest() {

        //OpeningScreen activity = new OpeningScreen();
        //EditText emailInputTest = activity.findViewById(R.id.emailButton);
        //emailInputTest.typeText("Test@email.sc.edu");

        onView(withId(R.id.puroPicture)).check(matches(isDisplayed()));
        onView(withId(R.id.puroPicture)).check(matches(isEnabled()));
        onView(withId(R.id.emailButton)).check(matches(isDisplayed()));
        onView(withId(R.id.emailButton)).check(matches(isEnabled()));
        onView(withId(R.id.emailButton)).check(matches(isClickable()));
        onView(withId(R.id.passWordButton)).check(matches(isDisplayed()));
        onView(withId(R.id.passWordButton)).check(matches(isEnabled()));
        onView(withId(R.id.passWordButton)).check(matches(isClickable()));
        onView(withId(R.id.signUpButton)).check(matches(isDisplayed()));
        onView(withId(R.id.signUpButton)).check(matches(isEnabled()));
        onView(withId(R.id.signUpButton)).check(matches(isClickable()));
        onView(withId(R.id.textViewLogin)).check(matches(withText("Don't have an account? Make one here.")));
        onView(withId(R.id.textViewLogin)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewLogin)).check(matches(isEnabled()));
        onView(withId(R.id.textViewLogin)).check(matches(isClickable()));
        onView(withId(R.id.pswRecovery)).check(matches(withText("Forget your password? Recover here")));
        onView(withId(R.id.pswRecovery)).check(matches(isDisplayed()));
        onView(withId(R.id.pswRecovery)).check(matches(isEnabled()));
        onView(withId(R.id.pswRecovery)).check(matches(isClickable()));
        onView(withId(R.id.googleSignIn)).check(matches(isDisplayed()));
        onView(withId(R.id.googleSignIn)).check(matches(isEnabled()));
        //onView(withId(R.id.googleSignIn)).check(matches(isClickable()));


    }



}