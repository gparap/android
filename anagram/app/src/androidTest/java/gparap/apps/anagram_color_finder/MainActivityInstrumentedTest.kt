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
package gparap.apps.anagram_color_finder

import android.view.View
import androidx.test.core.app.ActivityScenario
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
    private lateinit var decorView: View

    @Before
    fun setUp() {
        //launch activity
        activityScenario = ActivityScenario.launch(MainActivity::class.java)

        //get the top-level window decor view
        activityScenario.onActivity {
            decorView = it.window.decorView
        }
    }

    @Test
    fun isVisible_textViewAnagram() {
        onView(withId(R.id.textViewAnagram)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editTextAnswer() {
        onView(withId(R.id.editTextAnswer)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonSubmit() {
        onView(withId(R.id.buttonSubmit)).check(matches(isDisplayed()))
    }

    @Test
    fun showToast_answerIsEmpty() {
        //clear text and submit empty answer
        onView(withId(R.id.editTextAnswer)).perform(clearText())
        onView(withId(R.id.editTextAnswer)).perform(closeSoftKeyboard())
        onView(withId(R.id.buttonSubmit)).perform(click())

        onView(withText(R.string.toast_enter_answer))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    fun displayDialogWithAnswerResults() {
        //write any text and submit answer
        onView(withId(R.id.editTextAnswer)).perform(typeText("whatever"))
        onView(withId(R.id.editTextAnswer)).perform(closeSoftKeyboard())
        onView(withId(R.id.buttonSubmit)).perform(click())

        onView(withId(R.id.dialog_user_answer_result)).check(matches(isDisplayed()))
    }
}