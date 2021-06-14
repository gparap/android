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
package gparap.apps.todo_list

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import gparap.apps.todo_list.adapter.ToDoAdapter
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityInstrumentedTest {
    private lateinit var rootView: View
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        activityScenarioRule.scenario.onActivity { activity ->
            //get the root view
            rootView = activity.window.decorView
        }

        //launch activity
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isWidgetVisible_customActionBar() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun isWidgetVisible_recyclerView() {
        onView(withId(R.id.recyclerViewToDo)).check(matches(isDisplayed()))
    }

    @Test
    fun isWidgetVisible_floatingActionButton() {
        onView(withId(R.id.fabAddToDo)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateUp_fromAddToDoFragment_toToDoListFragment() {
        onView(withId(R.id.fabAddToDo)).perform(click())
        Espresso.pressBack()
        onView(withId(R.id.fragment_todo_list_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateUp_fromEditToDoFragment_toToDoListFragment() {
        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ToDoAdapter.ToDoViewHolder>(0, click())
        )
        Espresso.pressBack()
        onView(withId(R.id.fragment_todo_list_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun onFragmentAddToDo_showToastMessageIfToDoTextIsEmpty() {
        //goto AddToDoFragment
        onView(withId(R.id.fabAddToDo)).perform(click())

        //make sure edit text is empty
        onView(withId(R.id.editTextToDo)).perform(clearText())
        closeSoftKeyboard()

        //save empty to-do
        onView(withId(R.id.fabSaveToDo)).perform(click())

        onView(withText(R.string.toast_todo_empty))
            .inRoot(withDecorView(not(`is`(rootView))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun addToDo_isToDoAddedInRecyclerView() {
        val todoAdded = "adding a new to-do..."

        //create and save a new to-do
        onView(withId(R.id.fabAddToDo)).perform(click())
        onView(withId(R.id.editTextToDo)).perform(typeText(todoAdded))
        closeSoftKeyboard()
        onView(withId(R.id.fabSaveToDo)).perform(click())

        Thread.sleep(667)
        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.scrollTo<ToDoAdapter.ToDoViewHolder>(hasDescendant(withText(todoAdded)))
        )
    }

    @Test
    fun editToDo_isPickedToDoDisplayedInEditToDo() {
        //!!! if database is empty add manually a to-do ,
        //!!!   don't do it here.
        val firstToDoText = "todo1"

        //pick first to-do in the list
        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ToDoAdapter.ToDoViewHolder>(0, click())
        )

        onView(withId(R.id.editTextToDoUpdating)).check(matches(withText(firstToDoText)))
    }
}