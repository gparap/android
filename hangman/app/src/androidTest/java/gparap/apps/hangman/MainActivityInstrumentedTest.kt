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
package gparap.apps.hangman

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isVisible_imageViewGallows() {
        onView(withId(R.id.imageViewGallows)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textViewUsedLetters() {
        onView(withId(R.id.textViewUsedLetters)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_layoutAlphabet() {
        onView(withId(R.id.layoutAlphabet)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonStart() {
        onView(withId(R.id.buttonStart)).check(matches(isDisplayed()))
    }
}