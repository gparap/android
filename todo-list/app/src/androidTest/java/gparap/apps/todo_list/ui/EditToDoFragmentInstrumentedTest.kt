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

import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.UiDevice
import gparap.apps.todo_list.R
import gparap.apps.todo_list.ui.edit_todo.EditToDoFragment
import org.hamcrest.Matchers
import org.hamcrest.core.IsNot
import org.junit.Before

import org.junit.Test

class EditToDoFragmentInstrumentedTest {

    @Before
    fun setUp() {
        launchFragmentInContainer(null, R.style.Theme_MaterialComponents) {
            EditToDoFragment()
        }
    }

    @Test
    fun onViewCreated_isFragmentVisible() {
        Espresso.onView(ViewMatchers.withText(R.string.fragment_edit_todo))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun isVisible_editTextAddToDoUpdating() {
        Espresso.onView(ViewMatchers.withId(R.id.editTextToDoUpdating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun isVisible_fabUpdateToDoUpdating() {
        Espresso.onView(ViewMatchers.withId(R.id.fabUpdateToDo))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun isVisible_labelToDoTimeUpdating() {
        Espresso.onView(ViewMatchers.withId(R.id.labelDeadlineToDoUpdating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun isVisible_textViewToDoDeadlineUpdating() {
        Espresso.onView(ViewMatchers.withId(R.id.textViewToDoDeadlineUpdating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun isInvisible_textViewToDoTimeSetUpdating() {
        Espresso.onView(ViewMatchers.withId(R.id.textViewToDoTimeSetUpdating))
            .check(ViewAssertions.matches(IsNot.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun isVisible_buttonShowTimePickerDialogUpdating() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonShowTimePickerDialogUpdating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun isInvisible_textViewToDoDateSetUpdating() {
        Espresso.onView(ViewMatchers.withId(R.id.textViewToDoDateSetUpdating))
            .check(ViewAssertions.matches(IsNot.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun isVisible_buttonShowDatePickerDialogUpdating() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonShowDatePickerDialogUpdating))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun showDialog_timePickerUpdating() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonShowTimePickerDialogUpdating))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(TimePicker::class.java.name)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun showDialog_datePickerUpdating() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonShowDatePickerDialogUpdating))
            .perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker::class.java.name)))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun textViewToDoTimeSetUpdating_displayTimePicked() {
        val hours = 10
        val minutes = 10
        pickTime(hours, minutes)

        //test
        Espresso.onView(ViewMatchers.withId(R.id.textViewToDoTimeSetUpdating))
            .check(ViewAssertions.matches(ViewMatchers.withText("$hours:$minutes")))
    }

    @Test
    fun textViewToDoDateSetUpdating_displayDatePicked() {
        val year = 2021
        val month = 1
        val day = 1
        pickDate(year, month, day)

        //test
        Espresso.onView(ViewMatchers.withId(R.id.textViewToDoDateSetUpdating))
            .check(ViewAssertions.matches(ViewMatchers.withText("$day/$month/$year")))
    }

    @Test
    fun doesValuePersistOrientationChanges_ToDoTimeSetUpdating() {
        val hours = 15
        val minutes = 15
        pickTime(hours, minutes)

        //change screen orientation
        val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        uiDevice.setOrientationLeft()
        Thread.sleep(667)

        Espresso.onView(ViewMatchers.withId(R.id.textViewToDoTimeSetUpdating))
            .check(ViewAssertions.matches(ViewMatchers.withText("$hours:$minutes")))
    }

    @Test
    fun doesValuePersistOrientationChanges_ToDoDateSetUpdating() {
        val year = 2022
        val month = 2
        val day = 2
        pickDate(year, month, day)

        //change screen orientation
        val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        uiDevice.setOrientationLeft()
        Thread.sleep(667)

        Espresso.onView(ViewMatchers.withId(R.id.textViewToDoDateSetUpdating))
            .check(ViewAssertions.matches(ViewMatchers.withText("$day/$month/$year")))
    }

    private fun pickTime(hours: Int, minutes: Int) {
        //show time picker dialog
        Espresso.onView(ViewMatchers.withId(R.id.buttonShowTimePickerDialogUpdating))
            .perform(ViewActions.click())

        //pick time
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(TimePicker::class.java.name)))
            .perform(PickerActions.setTime(hours, minutes))
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }

    private fun pickDate(year: Int, month: Int, day: Int) {
        //show date picker dialog
        Espresso.onView(ViewMatchers.withId(R.id.buttonShowDatePickerDialogUpdating))
            .perform(ViewActions.click())

        //pick date
        Espresso.onView(ViewMatchers.withClassName(Matchers.equalTo(DatePicker::class.java.name)))
            .perform(PickerActions.setDate(year, month, day))
        Espresso.onView(ViewMatchers.withId(android.R.id.button1)).perform(ViewActions.click())
    }
}