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
package gparap.apps.barcode

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun onCreate_isBottomNavigationBarVisible() {
        onView(withId(R.id.bottom_nav_view)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateToScannerFragment() {
        onView(withId(R.id.navigation_scanner)).perform(click())
        onView(withId(R.id.action_bar)).check(matches(hasDescendant(withText(R.string.title_scanner))))
    }

    @Test
    fun navigateToGeneratorFragment() {
        onView(withId(R.id.navigation_generator)).perform(click())
        onView(withId(R.id.action_bar)).check(matches(hasDescendant(withText(R.string.title_generator))))
    }
}