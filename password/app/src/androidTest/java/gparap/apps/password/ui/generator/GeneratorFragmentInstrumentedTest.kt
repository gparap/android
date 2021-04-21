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
package gparap.apps.password.ui.generator

import androidx.fragment.app.testing.FragmentScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.core.IsNot.not
import org.junit.After
import org.junit.Before
import org.junit.Test

class GeneratorFragmentInstrumentedTest {

    @Before
    fun setUp() {
        FragmentScenario.launchInContainer(GeneratorFragment::class.java)
    }

    @Test
    fun isInvisible_textViewPasswordGenerated() {
        onView(withId(gparap.apps.password.R.id.textViewPasswordGenerated))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun isVisible_buttonGeneratePassword() {
        onView(withId(gparap.apps.password.R.id.buttonGeneratePassword)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_radioGroupPasswordLength() {
        onView(withId(gparap.apps.password.R.id.radioGroupPasswordLength)).check(matches(isDisplayed()))
    }

    @Test
    fun isInvisible_editTextCustomLengthPassword() {
        onView(withId(gparap.apps.password.R.id.textViewPasswordGenerated))
            .check(matches(not(isDisplayed())))
    }
}