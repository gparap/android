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

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import gparap.apps.password.R
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test

class WidgetsVisibilityInstrumentedTest {

    @Before
    fun setUp() {
        FragmentScenario.launchInContainer(EvaluatorFragment::class.java)
    }

    @Test
    fun isInvisible_editTextEvaluatedPasswordTitle() {
        onView(withId(R.id.editTextEvaluatedPasswordTitle)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isVisible_editTextEvaluatedPassword() {
        onView(withId(R.id.editTextEvaluatedPassword)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_evaluatedPasswordLengthLabel() {
        onView(withId(R.id.textViewEvaluatedPasswordLengthLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_evaluatedPasswordLength() {
        onView(withId(R.id.textViewEvaluatedPasswordLength)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_evaluatedPasswordStrengthInfo() {
        onView(withId(R.id.textViewEvaluatedPasswordStrength)).check(matches(isDisplayed()))
    }

    @Test
    fun isInvisible_imageViewPasswordEvaluationPassed() {
        onView(withId(R.id.imageViewPasswordEvaluationPassed)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isVisible_buttonSaveEvaluatedPassword() {
        onView(withId(R.id.buttonSaveEvaluatedPassword)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_passwordEvaluationUserTips() {
        onView(withId(R.id.passwordEvaluationUserTips)).check(matches(isDisplayed()))
    }
}