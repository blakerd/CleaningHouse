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
public class AddCreditCardInfoTest {

    @Rule
    public ActivityTestRule<OpeningScreen> mActivityTestRule = new ActivityTestRule<>(OpeningScreen.class);

    @Test
    public void addCreditCardInfoTest() {
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
                allOf(withId(R.id.signUpButton), withText("Sign In"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.relativelayout),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.billsReceipts), withText("View Transactions"),
                        childAtPosition(
                                allOf(withId(R.id.relativelayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                2)),
                                4),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.cardNumberEditText), withText("Card Number"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                2)),
                                0),
                        isDisplayed()));
        editText.check(matches(isDisplayed()));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.CVCEditText), withText("CVC"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                2)),
                                1),
                        isDisplayed()));
        editText2.check(matches(isDisplayed()));

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.dateEditText), withText("Date (MMYY)"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                2)),
                                2),
                        isDisplayed()));
        editText3.check(matches(isDisplayed()));

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.cardNumberEditText),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                2)),
                                0),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("5555 5555 5555 5555"), closeSoftKeyboard());

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.CVCEditText),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatEditText6.perform(replaceText("555"), closeSoftKeyboard());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.dateEditText),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                2)),
                                2),
                        isDisplayed()));
        appCompatEditText7.perform(replaceText("0505"), closeSoftKeyboard());

        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.uploadButton), withText("Upload"),
                        childAtPosition(
                                allOf(withId(R.id.linearLayout),
                                        childAtPosition(
                                                withId(R.id.drawer_layout),
                                                2)),
                                3),
                        isDisplayed()));
        appCompatButton4.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.cardNumView), withText("5555 5555 5555 5555"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_layout),
                                        1),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("5555 5555 5555 5555")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.dateView), withText("0505"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_layout),
                                        1),
                                2),
                        isDisplayed()));
        textView2.check(matches(withText("0505")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.CVCView), withText("555"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.drawer_layout),
                                        1),
                                3),
                        isDisplayed()));
        textView3.check(matches(withText("555")));
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
