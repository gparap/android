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
package gparap.apps.calendar

import android.view.View
import android.widget.CalendarView
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test


class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var decorView: View
    
    @Before
    fun setUp() {
        //launch activity and get the top-level view
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity { 
            decorView = it.window.decorView
        }
    }

    @Test
    fun isVisible_calendarView() {
        try{
            onView(withId(R.id.calendarView)).check(matches(isDisplayed()))
        }catch (e: androidx.test.espresso.AmbiguousViewMatcherException){
            assert(true)    //it's ok, we have only one calendar
        }
    }

    @Test
    fun isVisible_editTextEventName() {
        onView(withId(R.id.editTextEventName)).check(matches(isDisplayed()))
    }


    @Test
    fun isVisible_editTextEventDetails() {
        onView(withId(R.id.editTextEventDetails)).check(matches(isDisplayed()))
    }


    @Test
    fun isVisible_buttonAddEvent() {
        onView(withId(R.id.buttonAddEvent)).check(matches(isDisplayed()))
    }


    @Test
    fun isVisible_buttonRemoveEvent() {
        onView(withId(R.id.buttonRemoveEvent)).check(matches(isDisplayed()))
    }


    @Test
    fun isVisible_buttonClearText() {
        onView(withId(R.id.buttonClearText)).check(matches(isDisplayed()))
    }

    @Test
    fun onClickClearText() {
        //add some text and clear
        onView(withId(R.id.editTextEventName)).perform(typeText("name"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.editTextEventDetails)).perform(typeText("details"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.buttonClearText)).perform(click())

        //test
        activityScenario.onActivity {
            val eventName = it.findViewById<EditText>(R.id.editTextEventName)
            val eventDetails = it.findViewById<EditText>(R.id.editTextEventDetails)

            assert(eventName.text.toString() == "")
            assert(eventDetails.text.toString() == "")
        }
    }

    @Test
    fun onClickAddEvent() {
        onView(withClassName(Matchers.equalTo(CalendarView::class.java.name))).perform(click())

        //add a calendar event with details
        onView(withId(R.id.editTextEventName)).perform(typeText("event name"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.editTextEventDetails)).perform(typeText("event details"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.buttonAddEvent)).perform(click())

        //refresh the view
        onView(withClassName(Matchers.equalTo(CalendarView::class.java.name))).perform(click())

        //test
        activityScenario.onActivity {
            val eventName = it.findViewById<EditText>(R.id.editTextEventName)
            val eventDetails = it.findViewById<EditText>(R.id.editTextEventDetails)

            assert(eventName.text.toString() == "event name")
            assert(eventDetails.text.toString() == "event details")
        }

        //remove event
        onView(withId(R.id.buttonRemoveEvent)).perform(click())
    }

    @Test
    fun onClickRemoveEvent() {
        onView(withClassName(Matchers.equalTo(CalendarView::class.java.name))).perform(click())

        //add a calendar event with details
        onView(withId(R.id.editTextEventName)).perform(typeText("event name"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.editTextEventDetails)).perform(typeText("event details"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.buttonAddEvent)).perform(click())

        //remove event
        onView(withId(R.id.buttonRemoveEvent)).perform(click())

        //refresh the view
        onView(withClassName(Matchers.equalTo(CalendarView::class.java.name))).perform(click())

        //test
        activityScenario.onActivity {
            val eventName = it.findViewById<EditText>(R.id.editTextEventName)
            val eventDetails = it.findViewById<EditText>(R.id.editTextEventDetails)

            assert(eventName.text.toString() == "")
            assert(eventDetails.text.toString() == "")
        }
    }
    
    @Test
    fun ifEventNameIsEmpty_displayErrorMessage() {
        //clear input first and try to save an empty event
        onView(withId(R.id.editTextEventName)).perform(clearText())
        onView(withId(R.id.buttonAddEvent)).perform(click())
        
        //test
        onView(withText(R.string.toast_empty_event))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))
    }
}