package edu.towson.cosc435.labsapp

import android.app.Application
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SongsAppInstrumentedTest {
    @get:Rule
    var activityRule: ActivityTestRule<MainActivity>
            = ActivityTestRule(MainActivity::class.java)

    @Test
    fun next_button_displays_next_song() {
        onView(withId(R.id.songName)).check(matches(withText("Song0")))

        onView(withId(R.id.buttonNext)).perform(click())

        onView(withId(R.id.songName)).check(matches(withText("Song1")))
    }

    @Test
    fun is_awesome_checkbox_correctly_checked() {
        onView(withId(R.id.isAwesomeCb)).check(matches(isChecked()))

        onView(withId(R.id.buttonNext)).perform(click())

        onView(withId(R.id.isAwesomeCb)).check(matches(isNotChecked()))
    }
}
