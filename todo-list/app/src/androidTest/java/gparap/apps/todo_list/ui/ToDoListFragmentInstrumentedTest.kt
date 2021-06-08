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
package gparap.apps.todo_list.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import gparap.apps.todo_list.R
import gparap.apps.todo_list.ui.todo_list.ToDoListFragment
import org.junit.Before
import org.junit.Test

class ToDoListFragmentInstrumentedTest {
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        //create a TestNavHostController
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())

        //launch fragment
        val fragmentScenario = launchFragmentInContainer(null, R.style.Theme_MaterialComponents) {
            ToDoListFragment()
        }

        //setup fragment with NavController
        fragmentScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun onViewCreated_isFragmentVisible() {
        onView(withText(R.string.fragment_todo_list_title)).check(matches(isDisplayed()))
    }

    @Test
    fun onAddToDoButtonClick_gotoAddToDoFragment() {
        onView(withId(R.id.fabAddToDo)).perform(click())
        assert(navController.currentDestination!!.id == R.id.addToDoFragment)
    }
}