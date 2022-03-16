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
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import gparap.apps.quiz.utils.AppConstants
import org.hamcrest.core.IsNot.not
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var context: Context
    private lateinit var decorView: View

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        context = InstrumentationRegistry.getInstrumentation().targetContext
        activityScenario.onActivity {
            decorView = it.window.decorView
        }
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
            questions = it.getViewModel().getSelectedCategoryQuestions()
        }
        assertNotNull(questions)
    }

    @Test
    @MediumTest
    fun onQuizStart_getAllMultipleChoicesOfQuestion() {
        var choices: List<String>? = null
        val category = context.resources.getString(R.string.category_animals)
        selectCategoryAndStartQuiz(category)
        activityScenario.onActivity {
            choices = it.getViewModel().getMultipleChoices()
        }
        assertNotNull(choices)
    }

    @Test
    @MediumTest
    fun onQuizStart_displayAllMultipleChoicesOfQuestionAsRadioButtonOptions() {
        //select a category and start the quiz
        val category = context.resources.getString(R.string.category_animals)
        selectCategoryAndStartQuiz(category)

        //get radio button option texts
        var radioButtonOption1: String? = null
        var radioButtonOption2: String? = null
        var radioButtonOption3: String? = null
        var radioButtonOption4: String? = null
        activityScenario.onActivity {
            it.findViewById<RadioButton>(R.id.radio_button_choice_one).apply {
                radioButtonOption1 = this.text.toString()
            }
            it.findViewById<RadioButton>(R.id.radio_button_choice_two).apply {
                radioButtonOption2 = this.text.toString()
            }
            it.findViewById<RadioButton>(R.id.radio_button_choice_three).apply {
                radioButtonOption3 = this.text.toString()
            }
            it.findViewById<RadioButton>(R.id.radio_button_choice_four).apply {
                radioButtonOption4 = this.text.toString()
            }
        }

        //test here all 4 radio button's texts
        assertNotNull(radioButtonOption1)
        assertNotNull(radioButtonOption2)
        assertNotNull(radioButtonOption3)
        assertNotNull(radioButtonOption4)
    }

    @Test
    @MediumTest
    fun onQuizStart_getQuestionDifficulty() {
        var difficulty: String? = null
        val category = context.resources.getString(R.string.category_animals)
        selectCategoryAndStartQuiz(category)
        activityScenario.onActivity {
            difficulty = it.getViewModel().getQuestionDifficulty()
        }
        assertNotNull(difficulty)
    }

    @Test
    @MediumTest
    fun onSubmitQuestion_getQuestionDifficulty() {
        var difficulty: String? = null
        val category = context.resources.getString(R.string.category_animals)
        selectCategoryAndStartQuiz(category)

        //submit the first answer
        onView(withId(R.id.radio_button_choice_one)).perform(click())
        onView(withId(R.id.button_submit_answer)).perform(click())

        activityScenario.onActivity {
            difficulty = it.getViewModel().getQuestionDifficulty()
        }
        assertNotNull(difficulty)
    }

    @Test
    @MediumTest
    fun onNextQuestionButtonClick_getNextQuestion() {
        var questionCurr: String? = null
        var questionNext: String? = null
        val category = context.resources.getString(R.string.category_animals)
        selectCategoryAndStartQuiz(category)

        //get current question
        activityScenario.onActivity {
            questionCurr = it.getViewModel().getSelectedCategoryNextQuestion()
        }

        //get next question
        onView(withId(R.id.button_next_question)).perform(click())
        Thread.sleep(300)
        activityScenario.onActivity {
            questionNext = it.getViewModel().getSelectedCategoryNextQuestion()
        }

        assertNotEquals(questionNext, questionCurr)
    }

    @Test
    @MediumTest
    fun onPreviousQuestionButtonClick_getNextQuestion() {
        var questionCurr: String? = null
        var questionNext: String? = null
        val category = context.resources.getString(R.string.category_animals)
        selectCategoryAndStartQuiz(category)

        //go a little forward into questions
        onView(withId(R.id.button_next_question)).perform(click())
        onView(withId(R.id.button_next_question)).perform(click())
        onView(withId(R.id.button_next_question)).perform(click())

        //get current question
        activityScenario.onActivity {
            questionCurr = it.getViewModel().getSelectedCategoryPreviousQuestion()
        }

        //get previous question
        onView(withId(R.id.button_prev_question)).perform(click())
        Thread.sleep(300)
        activityScenario.onActivity {
            questionNext = it.getViewModel().getSelectedCategoryPreviousQuestion()
        }

        assertNotEquals(questionNext, questionCurr)
    }

    @Test
    @LargeTest
    fun onNextQuestionButtonClick_showErrorMessageWhenWeReachTheEndOfTheQuiz() {
        //wait for a possible toast message to fade away
        Thread.sleep(Toast.LENGTH_LONG.toLong())

        //select category
        val category = context.resources.getString(R.string.category_animals)
        selectCategoryAndStartQuiz(category)

        //go forward into the quiz until you pass the end
        for (i in 1..AppConstants.QUIZ_QUESTIONS_COUNT + 1) {
            onView(withId(R.id.button_next_question)).perform(click())
        }

        onView(withText(R.string.toast_next_question_error))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    @LargeTest
    fun onPreviousQuestionButtonClick_showMessageWhenWeReachTheStartOfTheQuiz() {
        //wait for a possible toast message to fade away
        Thread.sleep(Toast.LENGTH_LONG.toLong())

        //select category
        val category = context.resources.getString(R.string.category_animals)
        selectCategoryAndStartQuiz(category)

        //try to go beyond the start of the quiz
        onView(withId(R.id.button_prev_question)).perform(click())

        onView(withText(R.string.toast_prev_question_error))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))

    }

    @Test
    @LargeTest
    fun onUserAnswerSubmitButton_showMessageWhenTheUserSelectedNothing() {
        //wait for a possible toast message to fade away
        Thread.sleep(Toast.LENGTH_LONG.toLong())

        //select category
        val category = context.resources.getString(R.string.category_animals)
        selectCategoryAndStartQuiz(category)

        //try to submit without selecting an answer
        onView(withId(R.id.button_submit_answer)).perform(click())

        onView(withText(R.string.toast_select_answer_error))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    @LargeTest
    fun onUserAnswerSubmitButton_userQuizAnswerIsNotEmpty() {
        //wait for a possible toast message to fade away
        Thread.sleep(Toast.LENGTH_LONG.toLong())

        //select category
        val category = context.resources.getString(R.string.category_animals)
        selectCategoryAndStartQuiz(category)

        //submit the first answer
        onView(withId(R.id.radio_button_choice_one)).perform(click())
        onView(withId(R.id.button_submit_answer)).perform(click())

        //get the user first answer
        var userAnswer: String? = null
        activityScenario.onActivity {
            userAnswer = it.getViewModel().getUserQuizAnswers().value?.get(0).toString()
        }

        assert(!userAnswer.isNullOrEmpty())
    }

    private fun selectCategoryAndStartQuiz(category: String) {
        onView(withId(R.id.spinner_categories)).perform(click())
        Thread.sleep(300)
        onView(withText(category)).perform(click())
        Thread.sleep(300)
        onView(withId(R.id.button_start_quiz)).perform(click())
    }
}