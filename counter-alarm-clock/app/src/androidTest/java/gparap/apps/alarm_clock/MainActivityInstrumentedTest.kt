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
package gparap.apps.alarm_clock

import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test
import java.time.LocalTime
import java.util.*

class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var decorView: View

    @Before
    fun setUp() {
        //launch activity and retrieve the top-level window decor view
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity {
            decorView = it.window.decorView
        }
    }

    @Test
    fun isNotVisible_textViewTimer() {
        onView(withId(R.id.textViewTimer)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isVisible_textViewAlarm() {
        onView(withId(R.id.textViewAlarm)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewTimerLabel() {
        onView(withId(R.id.textViewTimerLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewAlarmLabel() {
        onView(withId(R.id.textViewAlarmLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonSetAlarm() {
        onView(withId(R.id.buttonSetAlarm)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_timePicker() {
        onView(withId(R.id.timePicker)).check(matches(isDisplayed()))
    }

    @Test
    fun onButtonClick_setAlarm() {
        val expectedAlarm = "1:13 PM"
        var actualAlarm = ""

        //clear previous alarm
        activityScenario.onActivity {
            val textView = it.findViewById<TextView>(R.id.textViewAlarm)
            textView.text = ""
        }

        //set an alarm
        onView(withId(R.id.timePicker)).perform(PickerActions.setTime(13, 13))
        onView(withId(R.id.buttonSetAlarm)).perform(click())

        //get alarm
        activityScenario.onActivity {
            val textView = it.findViewById<TextView>(R.id.textViewAlarm)
            actualAlarm = textView.text.toString()
        }

        //test
        assert(expectedAlarm == actualAlarm)
    }

    @Test
    fun onWrongAlarmHourSet_displayErrorMessage() {
        //get current time
        val hourNow = getCurrentHour()
        val minuteNow = getCurrentMinute()

        //set wrong alarm hours
        onView(withId(R.id.timePicker)).perform(PickerActions.setTime(hourNow - 1, minuteNow))
        onView(withId(R.id.buttonSetAlarm)).perform(click())

        //test
        onView(withText(R.string.toast_error_setting_hours))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))

        //safe delay for the toast to disappear
        Thread.sleep(1667)
    }

    @Test
    fun onWrongAlarmMinutesSet_displayErrorMessage() {
        //get current time
        val hourNow = getCurrentHour()
        val minuteNow = getCurrentMinute()

        //set wrong alarm minutes
        onView(withId(R.id.timePicker)).perform(PickerActions.setTime(hourNow, minuteNow - 1))
        onView(withId(R.id.buttonSetAlarm)).perform(click())

        //test
        onView(withText(R.string.toast_error_setting_minutes))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))

        //safe delay for the toast to disappear
        Thread.sleep(1667)
    }

    private fun getCurrentHour(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val localTime = LocalTime.now()
            localTime.hour
        } else {
            val calendar: Calendar = GregorianCalendar()
            calendar[Calendar.HOUR_OF_DAY]
        }
    }

    private fun getCurrentMinute(): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val localTime = LocalTime.now()
            localTime.minute
        } else {
            val calendar: Calendar = GregorianCalendar()
            calendar[Calendar.MINUTE]
        }
    }
}