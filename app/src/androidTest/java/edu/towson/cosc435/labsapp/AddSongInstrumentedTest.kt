package edu.towson.cosc431.labsapp

import android.app.Activity.RESULT_OK
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddSongInstrumentedTest {

    @get:Rule
    val intentRule: IntentsTestRule<AddSongActivity> = IntentsTestRule(AddSongActivity::class.java)

    @Test
    fun add_song_created_intent() {

        onView(withId(R.id.songNameEt)).perform(typeText("TEST")).perform(closeSoftKeyboard())

        onView(withId(R.id.addSongBtn)).perform(click())

        assertEquals(intentRule.activityResult.resultCode, RESULT_OK)
        assertTrue(intentRule.activityResult.resultData.extras.getString(AddSongActivity.SONG_KEY).contains("TEST"))
    }

}