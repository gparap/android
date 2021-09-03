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

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test

class MainActivityVisibilityTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isVisible_buttonFindBMI() {
        onView(withId(R.id.buttonFindBMI)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonClearFields() {
        onView(withId(R.id.buttonClearFields)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editTextHeight() {
        onView(withId(R.id.editTextHeight)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editTextWeight() {
        onView(withId(R.id.editTextWeight)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewAppLabel() {
        onView(withId(R.id.textViewAppLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewHeight() {
        onView(withId(R.id.textViewHeight)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewWeight() {
        onView(withId(R.id.textViewWeight)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewBMILabel() {
        onView(withId(R.id.textViewBMILabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewCategoryLabel() {
        onView(withId(R.id.textViewCategoryLabel)).check(matches(isDisplayed()))
    }

    @Test
    fun isNotVisible_textViewCategory() {
        onView(withId(R.id.textViewCategory)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isNotVisible_textViewBMI() {
        onView(withId(R.id.textViewBMI)).check(matches(not(isDisplayed())))
    }
}