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
package gparap.apps.password.ui.evaluator

import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import gparap.apps.password.R
import org.hamcrest.core.IsNot.not
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test


class EvaluatorFragmentInstrumentedTest {
    private lateinit var fragmentScenario: FragmentScenario<EvaluatorFragment>
    private lateinit var rootView: View

    @Before
    fun setUp() {
        fragmentScenario = FragmentScenario.launchInContainer(EvaluatorFragment::class.java)

        //get root view
        fragmentScenario.onFragment {
            rootView = it.activity?.window?.decorView!!
        }
    }

    @Test
    fun typingPassword() {
        //type a password to test
        val expectedPassword = "abcd"
        onView(withId(R.id.editTextEvaluatedPassword)).perform(typeText(expectedPassword))
        closeSoftKeyboard()

        //get password value
        var password: EditText? = null
        fragmentScenario.onFragment {
            password = it.view?.findViewById(R.id.editTextEvaluatedPassword)
        }

        assertEquals(password?.text.toString(), expectedPassword)
    }

    @Test
    fun typingPassword_changesPasswordLength() {
        //type a password to test length
        val expectedLength = "4"
        onView(withId(R.id.editTextEvaluatedPassword)).perform(typeText("abcd"))
        closeSoftKeyboard()

        //get password value
        var passwordLength: TextView? = null
        fragmentScenario.onFragment {
            passwordLength = it.view?.findViewById(R.id.textViewEvaluatedPasswordLength)
        }

        assertEquals(passwordLength?.text.toString(), expectedLength)
    }

    @Test
    fun typingPassword_changesPasswordStrength() {
        //type a password to test strength
        val unchangedStrength = "WEAKEST"
        onView(withId(R.id.editTextEvaluatedPassword)).perform(typeText("abcd00AAA"))
        closeSoftKeyboard()

        //get password strength
        var passwordStrength: TextView? = null
        fragmentScenario.onFragment {
            passwordStrength = it.view?.findViewById(R.id.textViewEvaluatedPasswordStrength)
        }

        assertNotEquals(passwordStrength?.text, unchangedStrength)
    }

    @Test
    fun typingPassword_showsPasswordTitle() {
        //type a password to test title visibility
        onView(withId(R.id.editTextEvaluatedPassword)).perform(typeText("a"))
        closeSoftKeyboard()

        onView(withId(R.id.editTextEvaluatedPasswordTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun emptyingPassword_hidesPasswordTitle() {
        //make title visible first
        onView(withId(R.id.editTextEvaluatedPassword)).perform(typeText("abcd"))
        closeSoftKeyboard()

        //make title invisible
        onView(withId(R.id.editTextEvaluatedPassword)).perform(clearText())
        closeSoftKeyboard()

        onView(withId(R.id.editTextEvaluatedPasswordTitle)).check(matches(not(isDisplayed())))
    }

    @Test
    fun savingPassword_passwordIsEmpty() {
        onView(withId(R.id.buttonSaveEvaluatedPassword)).perform(click())
        onView(withText(R.string.toast_empty_password))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))

        // !!! testing must sleep as long as the duration of the toast message
        // !!! when we test more than one toasts
        Thread.sleep(2000)
    }

    @Test
    fun savingPassword_passwordTitleIsEmpty() {
        //show title first
        onView(withId(R.id.editTextEvaluatedPassword)).perform(typeText("aaa"))
        closeSoftKeyboard()

        onView(withId(R.id.buttonSaveEvaluatedPassword)).perform(click())
        onView(withText(R.string.toast_empty_title))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))

        // !!! testing must sleep as long as the duration of the toast message
        // !!! when we test more than one toasts
        Thread.sleep(2000)
    }

    @Test
    fun evaluatingPassword_showTickWhenPasswordIsStrongest() {
        //clear password first
        onView(withId(R.id.editTextEvaluatedPassword)).perform(clearText())

        //type the strong password possible
        onView(withId(R.id.editTextEvaluatedPassword))
            .perform(typeText("aaaaAAAA!@#$1234"))
        closeSoftKeyboard()

        onView(withId(R.id.imageViewPasswordEvaluationPassed)).check(matches(isDisplayed()))
    }
}