package com.example.researchapp;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

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
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SimpleHomeScreenTest {

    @Rule
    public ActivityTestRule<OpeningScreen> mActivityTestRule = new ActivityTestRule<>(OpeningScreen.class);

    @Test
    public void simpleHomeScreenTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.textView), withText("Already have an account? Sign-in here."),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativelayout),
                                        0),
                                2),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.emailButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativelayout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("harleymom5999@gmail.com"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.emailButton), withText("harleymom5999@gmail.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativelayout),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText2.perform(pressImeActionButton());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.passWordButton),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativelayout),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("Checkers0509"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.passWordButton), withText("Checkers0509"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativelayout),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText4.perform(pressImeActionButton());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.signUpButton), withText("Sign In"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativelayout),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.upcomingCleanings), withText("View Schedule"),
                        childAtPosition(
                                allOf(withId(R.id.relativelayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                2)),
                                3),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.home), withText("HOME"),
                        childAtPosition(
                                allOf(withId(R.id.relativelayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                2)),
                                2),
                        isDisplayed()));
        textView.check(matches(withText("HOME")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
