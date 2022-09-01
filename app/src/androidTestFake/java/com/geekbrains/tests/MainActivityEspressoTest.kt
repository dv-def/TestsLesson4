package com.geekbrains.tests

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.search.MainActivity
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activitySearch_IsWorking() {
        onView(withId(R.id.searchEditText)).perform(click())
        onView(withId(R.id.searchEditText)).perform(replaceText("algol"), closeSoftKeyboard())
        onView(withId(R.id.searchEditText)).perform(pressImeActionButton())

        onView(withId(R.id.totalCountTextView)).check(matches(withText("Number of results: 42")))
    }

    @Test
    fun activitySearch_IsVisible() {
        val assertion = matches(withEffectiveVisibility(Visibility.VISIBLE))
        onView(withId(R.id.searchEditText)).check(assertion)
    }

    @Test
    fun activityButton_IsVisible() {
        val assertion = matches(withEffectiveVisibility(Visibility.VISIBLE))
        onView(withId(R.id.toDetailsActivityButton)).check(assertion)
    }

    @Test
    fun activityTotalCountTextView_IsInvisible() {
        val assertion = matches(withEffectiveVisibility(Visibility.INVISIBLE))
        onView(withId(R.id.totalCountTextView)).check(assertion)
    }

    @Test
    fun activityProgressBar_IsGone() {
        val assertion = matches(withEffectiveVisibility(Visibility.GONE))
        onView(withId(R.id.progressBar)).check(assertion)
    }

    @Test
    fun activityButtonText_IsToDetails() {
        val assertion = matches(withText(R.string.to_details))
        onView(withId(R.id.toDetailsActivityButton)).check(assertion)
    }

    @Test
    fun activitySearchHint_IsEnterKeyword() {
        val assertion = matches(withHint(R.string.search_hint))
        onView(withId(R.id.searchEditText)).check(assertion)
    }

    @After
    fun close() {
        scenario.close()
    }
}
