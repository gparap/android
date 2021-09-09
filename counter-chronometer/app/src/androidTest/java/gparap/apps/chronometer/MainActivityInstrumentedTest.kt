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
package gparap.apps.chronometer

import android.widget.Chronometer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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
    fun isVisible_chronometer() {
        onView(withId(R.id.chronometer)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonStart() {
        onView(withId(R.id.buttonStart)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonStop() {
        onView(withId(R.id.buttonStop)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonClear() {
        onView(withId(R.id.buttonClear)).check(matches(isDisplayed()))
    }

    @Test
    fun onClickStart() {
        val baseTimeBefore = 0

        //make sure time is 00:00 and start the timer
        onView(withId(R.id.buttonClear)).perform(click())
        onView(withId(R.id.buttonStart)).perform(click())

        //run for a while...
        Thread.sleep(1667)

        //get the base time after the timer started
        var baseTimeAfter = 0L
        activityScenario.onActivity {
            val chronometer = it.findViewById<Chronometer>(R.id.chronometer)
            baseTimeAfter = convertTimeToLong(chronometer.text.toString())
        }

        assert(baseTimeAfter > baseTimeBefore)
    }

    @Test
    fun onClickStop() {
        //make sure time is 00:00 and start the timer
        onView(withId(R.id.buttonClear)).perform(click())
        onView(withId(R.id.buttonStart)).perform(click())

        //run for a while...
        Thread.sleep(1667)

        //stop and get the time
        onView(withId(R.id.buttonStop)).perform(click())
        var baseTimeStopped = 0L
        activityScenario.onActivity {
            val chronometer = it.findViewById<Chronometer>(R.id.chronometer)
            baseTimeStopped = convertTimeToLong(chronometer.text.toString())
        }

        //wait for a while...
        Thread.sleep(1667)

        //get the time
        var baseTimeNow = 0L
        activityScenario.onActivity {
            val chronometer = it.findViewById<Chronometer>(R.id.chronometer)
            baseTimeNow = convertTimeToLong(chronometer.text.toString())
        }

        assert(baseTimeStopped == baseTimeNow)
    }

    @Test
    fun onClickClear() {
        //make sure time is 00:00 and start the timer
        onView(withId(R.id.buttonClear)).perform(click())
        onView(withId(R.id.buttonStart)).perform(click())

        //run for a while...
        Thread.sleep(1667)

        //reset and get the time
        onView(withId(R.id.buttonClear)).perform(click())
        var baseTime = 0L
        activityScenario.onActivity {
            val chronometer = it.findViewById<Chronometer>(R.id.chronometer)
            baseTime = convertTimeToLong(chronometer.text.toString())
        }

        assert(baseTime == 0L)
    }

    private fun convertTimeToLong(time: String): Long {
        val temp = time.split(":")
        return temp[1].toLong()
    }
}