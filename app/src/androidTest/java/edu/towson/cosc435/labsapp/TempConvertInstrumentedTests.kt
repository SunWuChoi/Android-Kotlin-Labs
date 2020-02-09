package edu.towson.cosc435.labsapp

import android.app.Activity
import android.app.Application
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
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
class TempConvertInstrumentedTests {
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
    fun testErrorMessage() {
        val appContext = ApplicationProvider.getApplicationContext<Application>()
        // in the test, we click the convert button without first entering and text,
        // and verify that the result TextView contains our error message
        onView(withId(R.id.convert_btn))
            .perform(click())
        onView(withId(R.id.result_tv))
            .check(matches(withText(appContext.resources.getString(R.string.error_string))))
    }

    @Test
    fun testCelsiusToFahrenheit() {
        // in this test, we enter some text into the input EditText, select a radio button, click the
        // convert button, and verify the result TextView contains the correct value
        onView(withId(R.id.input_temp_et))
            .perform(typeText("100")).perform(closeSoftKeyboard());
        onView(withId(R.id.c2f_radio_btn))
            .perform(click())
        onView(withId(R.id.convert_btn))
            .perform(click())
        onView(withId(R.id.result_tv))
            .check(matches(withText("212.0")))
    }

    @Test
    fun testFahrenheitToCelsius() {
        // TODO - 8. Write a test for converting fahrenheit to celsius
        // this test should be very similar to the testCelsiusToFahrenheit test above
    }
}
