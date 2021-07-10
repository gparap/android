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
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.isDialog
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
        val todoAdded = "Adding a new to-do..."

        //create and save a new to-do
        onView(withId(R.id.fabAddToDo)).perform(click())
        onView(withId(R.id.editTextToDo)).perform(typeText(todoAdded))
        closeSoftKeyboard()
        onView(withId(R.id.fabSaveToDo)).perform(click())

        Thread.sleep(667)
        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.scrollTo<ToDoAdapter.ToDoViewHolder>(
                hasDescendant(
                    withText(
                        todoAdded
                    )
                )
            )
        )
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
    fun editToDo_isPickedToDoDisplayedInEditToDo() {
        createToDoForTesting("Todo0")
        deleteToDoList()
        val firstToDoText = "Todo1"
        createToDoForTesting(firstToDoText)

        //pick first to-do in the list
        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ToDoAdapter.ToDoViewHolder>(0, click())
        )

        onView(withId(R.id.editTextToDoUpdating)).check(matches(withText(firstToDoText)))
    }

    @Test
    fun onFragmentEditToDo_showToastMessageIfToDoTextIsEmpty() {
        val firstToDoText = "Todo1"
        createToDoForTesting(firstToDoText)

        //wait to clear toast message
        Thread.sleep(1667)

        //pick first to-do in the list
        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ToDoAdapter.ToDoViewHolder>(0, click())
        )

        //make sure edit text is empty
        onView(withId(R.id.editTextToDoUpdating)).perform(clearText())
        closeSoftKeyboard()

        //wait to clear toast message
        Thread.sleep(1667)

        //save empty to-do
        onView(withId(R.id.fabUpdateToDo)).perform(click())

        onView(withText(R.string.toast_todo_empty))
            .inRoot(withDecorView(not(`is`(rootView))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun editToDo_isToDoEditedInRecyclerView() {
        createToDoForTesting("Todo0")
        deleteToDoList()
        val firstToDoText = "Todo1"
        val editedToDoText = "Todo11"
        createToDoForTesting(firstToDoText)

        //pick first to-do in the list
        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ToDoAdapter.ToDoViewHolder>(0, click())
        )

        //edit the to-do text and update
        onView(withId(R.id.editTextToDoUpdating)).perform(clearText())
        onView(withId(R.id.editTextToDoUpdating)).perform(typeText(editedToDoText))
        closeSoftKeyboard()
        onView(withId(R.id.fabUpdateToDo)).perform(click())

        onView(withText(editedToDoText)).check(matches(isDisplayed()))
    }

    @Test
    fun swipeLeftAToDo_isDeleteConfirmationDialogDisplayed() {
        createToDoForTesting("Todo1")

        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ToDoAdapter.ToDoViewHolder>(0, swipeLeft())
        )

        onView(withText(R.string.dialog_delete_todo)).check(matches(isDisplayed()))
    }

    @Test
    fun swipeRightAToDo_isDeleteConfirmationDialogDisplayed() {
        createToDoForTesting("Todo1")

        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ToDoAdapter.ToDoViewHolder>(0, swipeRight())
        )

        onView(withText(R.string.dialog_delete_todo)).check(matches(isDisplayed()))
    }

    @Test
    fun swipeLeftAToDo_deleteToDo() {
        deleteToDoList()
        val testingToDo = "Todo1"
        createToDoForTesting(testingToDo)

        //swipe to delete
        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ToDoAdapter.ToDoViewHolder>(0, swipeLeft())
        )

        //confirm deletion
        onView(withText(R.string.delete)).inRoot(isDialog()).perform(click())
        Thread.sleep(667)

        //test
        onView(withId(R.id.recyclerViewToDo)).check(matches(not(hasDescendant(withText(testingToDo)))))
    }

    @Test
    fun swipeRightAToDo_deleteToDo() {
        deleteToDoList()
        val testingToDo = "Todo2"
        createToDoForTesting(testingToDo)

        //swipe to delete
        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ToDoAdapter.ToDoViewHolder>(0, swipeRight())
        )

        //confirm deletion
        onView(withText(R.string.delete)).inRoot(isDialog()).perform(click())
        Thread.sleep(667)

        //test
        onView(withId(R.id.recyclerViewToDo)).check(matches(not(hasDescendant(withText(testingToDo)))))
    }

    @Test
    fun swipeLeftAToDo_cancelToDoDeleting() {
        val testingToDo = "Todo3"
        createToDoForTesting(testingToDo)

        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItem<ToDoAdapter.ToDoViewHolder>(
                hasDescendant(withText(testingToDo)), swipeLeft()
            )
        )

        //confirm deletion
        onView(withText(R.string.cancel)).inRoot(isDialog()).perform(click())
        Thread.sleep(667)

        //test
        try {
            onView(withId(R.id.recyclerViewToDo)).check(matches(hasDescendant(withText(testingToDo))))
        } catch (e: Exception) {
            assert(false)
        } finally {
            deleteTestingToDo(testingToDo)
        }
    }

    @Test
    fun swipeRightAToDo_cancelToDoDeleting() {
        val testingToDo = "Todo4"
        createToDoForTesting(testingToDo)

        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItem<ToDoAdapter.ToDoViewHolder>(
                hasDescendant(withText(testingToDo)), swipeRight()
            )
        )

        //confirm deletion
        onView(withText(R.string.cancel)).inRoot(isDialog()).perform(click())
        Thread.sleep(667)

        //test
        try {
            onView(withId(R.id.recyclerViewToDo)).check(matches(hasDescendant(withText(testingToDo))))
        } catch (e: Exception) {
            assert(false)
        } finally {
            deleteTestingToDo(testingToDo)
        }
    }

    @Test
    fun actionbarOptionsMenu_deleteToDo() {
        createToDoForTesting("Todo0")
        deleteToDoList()
        val testingToDo = "Delete me to-do"
        createToDoForTesting(testingToDo)

        //pick to-do for editing
        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItem<ToDoAdapter.ToDoViewHolder>(
                hasDescendant(withText(testingToDo)), click()
            )
        )

        //delete to-do
        onView(withId(R.id.action_menu_delete)).perform(click())
        onView(withText(R.string.delete)).inRoot(isDialog()).perform(click())

        onView(withId(R.id.recyclerViewToDo)).check(matches(not(hasDescendant(withText(testingToDo)))))
    }

    @Test
    fun actionbarOptionsMenu_deleteToDoList() {
        createToDoForTesting("Todo0")
        deleteToDoList()
        //add 2 new todos to list
        val testingToDo1 = "To-do 1"
        val testingToDo2 = "To-do 2"
        createToDoForTesting(testingToDo1)
        createToDoForTesting(testingToDo2)
        var todosCount = 2

        deleteToDoList()

        //get the total number of todos
        activityScenario.onActivity {
            todosCount = it.findViewById<RecyclerView>(R.id.recyclerViewToDo).adapter?.itemCount!!
        }

        assert(todosCount == 0)
    }

    @Test
    fun actionbarOptionsMenu_deleteToDoListCancelation() {
        createToDoForTesting("Todo0")
        deleteToDoList()

        //add 2 new todos to list
        val testingToDo1 = "To-do 1"
        val testingToDo2 = "To-do 2"
        createToDoForTesting(testingToDo1)
        createToDoForTesting(testingToDo2)
        var todosCount = 2

        //delete to-do list
        onView(withId(R.id.action_menu_delete)).perform(click())
        onView(withText(R.string.cancel)).inRoot(isDialog()).perform(click())

        //get the total number of todos
        activityScenario.onActivity {
            todosCount = it.findViewById<RecyclerView>(R.id.recyclerViewToDo).adapter?.itemCount!!
        }

        assert(todosCount == 2)
    }

    private fun deleteToDoList() {
        onView(withId(R.id.action_menu_delete)).perform(click())
        onView(withText(R.string.delete)).inRoot(isDialog()).perform(click())
    }

    private fun createToDoForTesting(todo: String) {
        onView(withId(R.id.fabAddToDo)).perform(click())
        onView(withId(R.id.editTextToDo)).perform(typeText(todo))
        closeSoftKeyboard()
        onView(withId(R.id.fabSaveToDo)).perform(click())
    }

    private fun deleteTestingToDo(todo: String) {
        onView(withId(R.id.recyclerViewToDo)).perform(
            RecyclerViewActions.actionOnItem<ToDoAdapter.ToDoViewHolder>(
                hasDescendant(withText(todo)), swipeLeft()
            )
        )
    }
}