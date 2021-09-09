/*
 * Copyright 2021 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.countdown_timer

import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isVisible_textViewTimerLabel() {
        onView(withId(R.id.textViewTimerLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editTextHours() {
        onView(withId(R.id.editTextHours)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editTextMinutes() {
        onView(withId(R.id.editTextMinutes)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editTextSeconds() {
        onView(withId(R.id.editTextSeconds)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonStart() {
        onView(withId(R.id.buttonStart)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonReset() {
        onView(withId(R.id.buttonReset)).check(matches(isDisplayed()))
    }

    @Test
    fun buttonStartCountdown() {
        //enter values and start the countdown
        val secondsOld = "20"
        val minutesOld = "10"
        val hoursOld = "1"
        onView(withId(R.id.editTextSeconds)).perform(typeText(secondsOld))
        closeSoftKeyboard()
        onView(withId(R.id.editTextMinutes)).perform(typeText(minutesOld))
        closeSoftKeyboard()
        onView(withId(R.id.editTextHours)).perform(typeText(hoursOld))
        closeSoftKeyboard()
        onView(withId(R.id.buttonStart)).perform(click())

        //run timer for a while...
        Thread.sleep(1667)

        //get the edit text values
        val secondsNew = getEditTextValueById(R.id.editTextSeconds)
        val minutesNew = getEditTextValueById(R.id.editTextMinutes)
        val hoursNew = getEditTextValueById(R.id.editTextHours)

        //test
        assert(secondsNew.toInt() <= secondsOld.toInt())
        assert(minutesNew.toInt() <= minutesOld.toInt())
        assert(hoursNew.toInt() <= hoursOld.toInt())
    }

    @Test
    fun buttonResetCountdown() {
        //enter any value first
        onView(withId(R.id.editTextSeconds)).perform(typeText("11"))
        closeSoftKeyboard()

        //start the timer and run for a while
        onView(withId(R.id.buttonStart)).perform(click())
        Thread.sleep(1667)

        //reset the timer
        onView(withId(R.id.buttonReset)).perform(click())

        //get the edit text values
        val seconds = getEditTextValueById(R.id.editTextSeconds)
        val minutes = getEditTextValueById(R.id.editTextMinutes)
        val hours = getEditTextValueById(R.id.editTextHours)

        //test
        assert(seconds == "")
        assert(minutes == "")
        assert(hours == "")
    }

    private fun getEditTextValueById(id: Int): String {
        var value = ""
        activityScenario.onActivity {
            val editText = it.findViewById<EditText>(id)
            value = editText.text.toString()
        }
        return value
    }
}