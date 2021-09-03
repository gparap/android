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
package gparap.apps.calculator_bmi

import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var rootView: View

    @Before
    fun setUp() {
        //launch activity and get the top-level window decor view
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity {
            rootView = it.window.decorView
        }
    }

    @Test
    fun displayErrorMessage_heightIsEmpty() {
        //clear height input
        onView(withId(R.id.editTextHeight)).perform(clearText())
        Espresso.closeSoftKeyboard()

        //enter a correct weight input
        onView(withId(R.id.editTextWeight)).perform(typeText("75"))
        Espresso.closeSoftKeyboard()

        //test
        onView(withId(R.id.buttonFindBMI)).perform(click())
        onView(withText(R.string.toast_enter_height))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))

        //delay for other toasts (Toast.SHORT value)
        Thread.sleep(1000)
    }

    @Test
    fun displayErrorMessage_weightIsEmpty() {
        //clear weight input
        onView(withId(R.id.editTextWeight)).perform(clearText())
        Espresso.closeSoftKeyboard()

        //enter a correct height input
        onView(withId(R.id.editTextHeight)).perform(typeText("1.75"))
        Espresso.closeSoftKeyboard()

        //test
        onView(withId(R.id.buttonFindBMI)).perform(click())
        onView(withText(R.string.toast_enter_weight))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))

        //delay for other toasts (Toast.SHORT value)
        Thread.sleep(1000)
    }

    @Test
    fun displayErrorMessage_heightIsWrong() {
        //enter a wrong height input
        onView(withId(R.id.editTextHeight)).perform(typeText("175"))
        Espresso.closeSoftKeyboard()

        //enter a correct weight input
        onView(withId(R.id.editTextWeight)).perform(typeText("75"))
        Espresso.closeSoftKeyboard()

        //test
        onView(withId(R.id.buttonFindBMI)).perform(click())
        onView(withText(R.string.toast_enter_correct_height))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))

        //delay for other toasts (Toast.SHORT value)
        Thread.sleep(1000)
    }

    @Test
    fun displayErrorMessage_weightIsWrong() {
        //enter a wrong weight input
        onView(withId(R.id.editTextWeight)).perform(typeText("1175"))
        Espresso.closeSoftKeyboard()

        //enter a correct height input
        onView(withId(R.id.editTextHeight)).perform(typeText("1.75"))
        Espresso.closeSoftKeyboard()

        //test
        onView(withId(R.id.buttonFindBMI)).perform(click())
        onView(withText(R.string.toast_enter_correct_weight))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))

        //delay for other toasts (Toast.SHORT value)
        Thread.sleep(1000)
    }

    @Test
    fun clearInputFields() {
        //clear height and weight inputs
        onView(withId(R.id.editTextHeight)).perform(typeText("1.75"))
        onView(withId(R.id.editTextWeight)).perform(typeText("75"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.buttonClearFields)).perform(click())

        //get the values of height and weight
        var height = ""
        var weight = ""
        activityScenario.onActivity {
            height = it.findViewById<EditText>(R.id.editTextHeight).text.toString()
            weight = it.findViewById<EditText>(R.id.editTextWeight).text.toString()
        }

        //test
        assert(height.isEmpty())
        assert(weight.isEmpty())
    }

    @Test
    fun computeBMI() {
        //clear bmi and category textView values
        onView(withId(R.id.buttonClearFields)).perform(click())

        //enter correct values for height/weight and compute bmi
        onView(withId(R.id.editTextHeight)).perform(typeText("1.75"))
        onView(withId(R.id.editTextWeight)).perform(typeText("75"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.buttonFindBMI)).perform(click())

        //get the values of bmi and category
        var bmi = ""
        var category = ""
        activityScenario.onActivity {
            bmi = it.findViewById<TextView>(R.id.textViewBMI).text.toString()
            category = it.findViewById<TextView>(R.id.textViewCategory).text.toString()
        }

        //test
        assert(bmi.isNotEmpty())
        assert(category.isNotEmpty())
    }
}