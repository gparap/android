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
package gparap.apps.puzzle_scramble_squares

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.core.IsNot.not
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class PuzzleActivityInstrumentedTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(PuzzleActivity::class.java)
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.puzzle_scramble_squares", appContext.packageName)
    }

    @Test
    fun isNotVisible_image_view_scrambled_squares() {
        onView(withId(R.id.image_view_scrambled_squares))
            .check(matches(not(isDisplayed())))
    }
}