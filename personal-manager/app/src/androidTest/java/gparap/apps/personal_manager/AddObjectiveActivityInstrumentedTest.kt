/*
 * Copyright 2024 gparap
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
package gparap.apps.personal_manager

import android.view.View
import android.widget.Toast
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import gparap.apps.personal_manager.ui.AddObjectiveActivity
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddObjectiveActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<AddObjectiveActivity>
    private lateinit var decorView: View

    @Before
    fun setUp() {
        //get the current scenario
        activityScenario = ActivityScenario.launch(AddObjectiveActivity::class.java)

        //get the current root view
        activityScenario.onActivity {
            decorView = it.window.decorView
        }
    }

    @Test
    fun isVisible_add_objective_title() {
        onView(withId(R.id.add_objective_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_add_objective_description() {
        onView(withId(R.id.add_objective_description)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_add_objective_due_date_label() {
        onView(withId(R.id.add_objective_due_date_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_add_objective_due_date() {
        onView(withId(R.id.add_objective_due_date)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_add_objective_submit_button() {
        onView(withId(R.id.add_objective_submit_button)).check(matches(isDisplayed()))
    }

    @Test
    fun validateObjective_titleIsEmpty_showErrorMessage() {
        //clear title, fill in the description field and try to submit
        onView(withId(R.id.add_objective_title)).perform(clearText())
        closeSoftKeyboard()
        onView(withId(R.id.add_objective_description)).perform(typeText("whatever"))
        closeSoftKeyboard()
        onView(withId(R.id.add_objective_submit_button)).perform(click())

        //test error message
        onView(withText(R.string.validation_title_empty))
            .inRoot(withDecorView(not(`is`(decorView))))
            .check(matches(isDisplayed()))

        //wait for the toast to disappear from view
        Thread.sleep(Toast.LENGTH_LONG.toLong())
    }

    @Test
    fun validateObjective_titleIsOnlyDigits_showErrorMessage() {
        //clear title and fill with digits, fill in the description field and try to submit
        onView(withId(R.id.add_objective_title)).perform(clearText())
        onView(withId(R.id.add_objective_title)).perform(typeText("111"))
        closeSoftKeyboard()
        onView(withId(R.id.add_objective_description)).perform(typeText("whatever"))
        closeSoftKeyboard()
        onView(withId(R.id.add_objective_submit_button)).perform(click())

        //test error message
        onView(withText(R.string.validation_title_digits_only))
            .inRoot(withDecorView(not(`is`(decorView))))
            .check(matches(isDisplayed()))

        //wait for the toast to disappear from view
        Thread.sleep(Toast.LENGTH_LONG.toLong())
    }

    @Test
    fun validateObjective_DescriptionIsEmpty_showErrorMessage() {
        //clear description, fill in the title field and try to submit
        onView(withId(R.id.add_objective_title)).perform(typeText("whatever"))
        closeSoftKeyboard()
        onView(withId(R.id.add_objective_description)).perform(clearText())
        closeSoftKeyboard()
        onView(withId(R.id.add_objective_submit_button)).perform(click())

        //test error message
        onView(withText(R.string.validation_description_empty))
            .inRoot(withDecorView(not(`is`(decorView))))
            .check(matches(isDisplayed()))

        //wait for the toast to disappear from view
        Thread.sleep(Toast.LENGTH_LONG.toLong())
    }

    @Test
    fun validateObjective_DescriptionIsOnlyDigits_showErrorMessage() {
        //clear description and fill with digits, fill in the title field and try to submit
        onView(withId(R.id.add_objective_title)).perform(typeText("whatever"))
        closeSoftKeyboard()
        onView(withId(R.id.add_objective_description)).perform(clearText())
        onView(withId(R.id.add_objective_description)).perform(typeText("111"))
        closeSoftKeyboard()
        onView(withId(R.id.add_objective_submit_button)).perform(click())

        //test error message
        onView(withText(R.string.validation_description_digits_only))
            .inRoot(withDecorView(not(`is`(decorView))))
            .check(matches(isDisplayed()))

        //wait for the toast to disappear from view
        Thread.sleep(Toast.LENGTH_LONG.toLong())
    }

    @Test
    fun validateObjective_DueDateSameAsInceptionDate_showErrorMessage() {
        //clear everything, fill in title & description and try to submit
        //!!! assert that the built in calendar displays always "today", unless it is set to other
        onView(withId(R.id.add_objective_title)).perform(typeText("whatever"))
        closeSoftKeyboard()
        onView(withId(R.id.add_objective_description)).perform(clearText())
        onView(withId(R.id.add_objective_description)).perform(typeText("whatever"))
        closeSoftKeyboard()
        onView(withId(R.id.add_objective_submit_button)).perform(click())

        //test error message
        onView(withText(R.string.validation_due_date_smaller))
            .inRoot(withDecorView(not(`is`(decorView))))
            .check(matches(isDisplayed()))

        //wait for the toast to disappear from view
        Thread.sleep(Toast.LENGTH_LONG.toLong())
    }
}