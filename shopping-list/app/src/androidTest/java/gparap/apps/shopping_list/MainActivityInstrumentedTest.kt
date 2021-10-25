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
package gparap.apps.shopping_list

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.shopping_list", appContext.packageName)
    }

    @Test
    fun isVisible_recyclerViewCategories() {
        onView(withId(R.id.recycler_view_categories)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_fabAddCategory() {
        onView(withId(R.id.fab_add_shopping_category)).check(matches(isDisplayed()))
    }

    @Test
    fun onClickFabAddCategory_openDialogForAddingNew() {
        onView(withId(R.id.fab_add_shopping_category)).perform(click())
        onView(withId(R.id.layout_add_category)).check(matches(isDisplayed()))
    }
}