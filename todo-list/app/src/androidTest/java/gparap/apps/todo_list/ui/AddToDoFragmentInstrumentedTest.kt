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
package gparap.apps.todo_list.ui

import android.widget.TimePicker
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers.*
import gparap.apps.todo_list.R
import gparap.apps.todo_list.ui.add_todo.AddToDoFragment
import org.hamcrest.Matchers.equalTo
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test

class AddToDoFragmentInstrumentedTest {
    @Before
    fun setUp() {
        launchFragmentInContainer(null, R.style.Theme_MaterialComponents) {
            AddToDoFragment()
        }
    }

    @Test
    fun onViewCreated_isFragmentVisible() {
        onView(withText(R.string.fragment_add_todo)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editTextAddToDo() {
        onView(withId(R.id.editTextAddToDo)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_fabSaveToDo() {
        onView(withId(R.id.fabSaveToDo)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_labelToDoTime() {
        onView(withId(R.id.labelDeadlineToDo)).check(matches(isDisplayed()))
    }

    @Test
    fun isInvisible_textViewToDoTimeSet() {
        onView(withId(R.id.textViewToDoTimeSet)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isVisible_buttonShowTimePickerDialog() {
        onView(withId(R.id.buttonShowTimePickerDialog)).check(matches(isDisplayed()))
    }

    @Test
    fun isInvisible_textViewToDoDateSet() {
        onView(withId(R.id.textViewToDoDateSet)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isVisible_buttonShowDatePickerDialog() {
        onView(withId(R.id.buttonShowDatePickerDialog)).check(matches(isDisplayed()))
    }

    @Test
    fun showDialog_timePicker() {
        onView(withId(R.id.buttonShowTimePickerDialog)).perform(click())
        onView(withClassName(equalTo(TimePicker::class.java.name))).check(matches(isDisplayed()))
    }

    @Test
    fun textViewToDoTimeSet_displayTimePicked() {
        val hours = 10
        val minutes = 10

        //show time picker dialog
        onView(withId(R.id.buttonShowTimePickerDialog)).perform(click())

        //pick time
        onView(withClassName(equalTo(TimePicker::class.java.name)))
            .perform(PickerActions.setTime(hours, minutes))
        onView(withId(android.R.id.button1)).perform(click())

        //test
        onView(withId(R.id.textViewToDoTimeSet)).check(matches(withText("$hours:$minutes")))
    }
}