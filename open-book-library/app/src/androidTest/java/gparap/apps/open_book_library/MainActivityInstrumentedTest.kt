/*
 * Copyright 2023 gparap
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
package gparap.apps.open_book_library

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.open_book_library", appContext.packageName)
    }

    @Test
    fun onFeaturedBooksClick_navigateToFeaturedBooksFragment() {
        onView(withId(R.id.featuredBooksFragment)).perform(click())
        onView(withId(R.id.layout_fragment_featured_books)).check(matches(isDisplayed()))
    }

    @Test
    fun onBookLibraryClick_navigateToBookLibraryFragment() {
        onView(withId(R.id.bookLibraryFragment)).perform(click())
        onView(withId(R.id.layout_fragment_book_library)).check(matches(isDisplayed()))
    }

    @Test
    fun onOpenLibraryClick_navigateToOpenLibraryFragment() {
        onView(withId(R.id.openLibraryFragment)).perform(click())
        onView(withId(R.id.layout_fragment_open_library)).check(matches(isDisplayed()))
    }
}