/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.quiz

import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var context: Context

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    @SmallTest
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.quiz", appContext.packageName)
    }

    @Test
    @SmallTest
    fun isVisible_spinner_categories() {
        onView(withId(R.id.spinner_categories)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_layout_introductory_text() {
        onView(withId(R.id.main_layout_intro)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isNotVisible_layout_quiz() {
        onView(withId(R.id.main_layout_quiz)).check(matches(not(isDisplayed())))
    }

    @Test
    @SmallTest
    fun isVisible_button_start_quiz() {
        onView(withId(R.id.button_start_quiz)).check(matches(isDisplayed()))
    }

    @Test
    @MediumTest
    fun onQuizStart_swapIntroWithQuizLayout() {
        selectCategoryAndStartQuiz(context.resources.getString(R.string.category_mathematics))
        onView(withId(R.id.main_layout_intro)).check(matches(not(isDisplayed())))
        onView(withId(R.id.main_layout_quiz)).check(matches(isDisplayed()))
    }

    @Test
    @MediumTest
    fun onQuizStart_getAllQuestionsOfQuizCategory() {
        var questions: List<String>? = null
        val category = context.resources.getString(R.string.category_animals)
        selectCategoryAndStartQuiz(category)
        activityScenario.onActivity {
            questions = it.getViewModel().getAllQuestions(category)
        }
        assertNotNull(questions)
    }

    private fun selectCategoryAndStartQuiz(category: String) {
        onView(withId(R.id.spinner_categories)).perform(click())
        Thread.sleep(300)
        onView(withText(category)).perform(click())
        Thread.sleep(300)
        onView(withId(R.id.button_start_quiz)).perform(click())
    }
}