package edu.towson.cosc435.labsapp

import android.app.Application
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert

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
    fun useAppContext() {
        // Context of the app under test.
        val appContext = ApplicationProvider.getApplicationContext<Application>()
        assertEquals("edu.towson.cosc435.labsapp", appContext.packageName)
    }

    @Test
    fun NextButtonShowsNextSong() {
        onView(withId(R.id.songName))
            .check(matches(withText("Song1")))
        onView(withId(R.id.buttonNext))
            .perform(click())
        onView(withId(R.id.songName))
            .check(matches(withText("Song2")))
    }

    @Test
    fun PrevButtonDoesNothingOnFirstSong() {
        onView(withId(R.id.songName))
            .check(matches(withText("Song1")))
        onView(withId(R.id.buttonPrev))
            .perform(click())
        onView(withId(R.id.songName))
            .check(matches(withText("Song1")))
    }

    @Test
    fun ButtonNextStopsWorkingAtTheLastSong() {
        val act = activityRule.activity
        val cnt = act.songs.getCount()
        var i = cnt
        var clicks = 0
        while (i >= 0) {
            i--
            onView(withId(R.id.buttonNext))
                .perform(click())
            clicks++
        }
        onView(withId(R.id.buttonNext))
            .perform(click())
        clicks++
        assertTrue( clicks > 10 )
        onView(withId(R.id.songName))
            .check(matches(withText("Song10")))
    }
}
