/*
 * Copyright 2024 gparap
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
package gparap.apps.personal_manager

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.longClick
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import gparap.apps.personal_manager.adapters.ObjectivesAdapter
import gparap.apps.personal_manager.ui.MainActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    //use these details for a test objective
    private val testObjectiveTitle = "test_title"
    private val testObjectiveDescription = "test_desc"
    private val testObjectiveDueDate = "test_due_date"

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isVisible_recycler_view_objectives() {
        onView(withId(R.id.recycler_view_objectives)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_fab_add_objective() {
        onView(withId(R.id.fab_add_objective)).check(matches(isDisplayed()))
    }

    @Test
    fun onAddObjectiveFabButtonClick_redirectToAddObjectiveActivity() {
        onView(withId(R.id.layout_activity_main)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_add_objective)).perform(click())
        onView(withId(R.id.layout_activity_add_objective)).check(matches(isDisplayed()))
    }

    @Test
    fun isCorrect_addNewObjective() {
        //get how many objective are there before inserting a new one
        val itemsBefore = getItems()

        //add a new test objective
        addObjective()

        //get how many objective are there after inserting a new one
        val itemsAfter = getItems()

        //test here if objective added successfully
        assert(itemsAfter > itemsBefore)
    }

    @Test
    fun isCorrect_openObjectiveForEditing() {
        //make sure there is only one test objective
        deleteObjectives()
        addObjective()

        //open the first objective
        onView(withId(R.id.recycler_view_objectives)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ObjectivesAdapter.ObjectivesViewHolder>(
                0,
                click()
            )
        )

        //test here
        onView(withId(R.id.layout_activity_update_objective)).check(matches(isDisplayed()))
        onView(withText(testObjectiveTitle)).check(matches(isDisplayed()))
        onView(withText(testObjectiveDescription)).check(matches(isDisplayed()))
        onView(withText(testObjectiveDueDate)).check(matches(isDisplayed()))
    }

    @Test
    fun icCorrect_updateObjective() {
        //make sure there is only one test objective
        deleteObjectives()
        addObjective()

        //open the first objective for editing
        onView(withId(R.id.recycler_view_objectives)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ObjectivesAdapter.ObjectivesViewHolder>(
                0,
                click()
            )
        )

        //update objective title
        val updatedTitle = testObjectiveTitle.plus(" updated")
        val updatedDescription = testObjectiveDescription.plus(" updated")
        val updatedDueDate = testObjectiveDueDate.plus(" updated")
        onView(withId(R.id.update_objective_title)).perform(clearText())
        onView(withId(R.id.update_objective_title)).perform(typeText(updatedTitle))
        onView(withId(R.id.update_objective_description)).perform(clearText())
        onView(withId(R.id.update_objective_description)).perform(typeText(updatedDescription))
        onView(withId(R.id.update_objective_due_date)).perform(clearText())
        onView(withId(R.id.update_objective_due_date)).perform(typeText(updatedDueDate))
        closeSoftKeyboard()
        onView(withId(R.id.update_objective_submit_button)).perform(click())
        pressBack()

        //test here
        onView(withText(updatedTitle)).check(matches(isDisplayed()))
        onView(withText(updatedDescription)).check(matches(isDisplayed()))
        onView(withText(updatedDueDate)).check(matches(isDisplayed()))
    }

    @Test
    fun isCorrect_deleteObjective() {
        //delete everything and add a test objective
        deleteObjectives()
        addObjective()

        //delete the objective
        onView(withId(R.id.recycler_view_objectives)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ObjectivesAdapter.ObjectivesViewHolder>(
                0,
                longClick()
            )
        )
        onView(withText(R.string.dialog_ok)).perform(click())

        //test if objective deleted
        val items = getItems()
        assert(items == 0)
    }

    @Test
    fun onMenuItemAddObjectiveClick_openAddOObjectiveActivity() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.text_add_objective)).perform(click())
        onView(withId(R.id.layout_activity_add_objective)).check(matches(isDisplayed()))
    }

    @Test
    fun onMenuItemDeleteObjectivesClick_deleteAllObjectives() {
        //add at least two test objectives
        //!!! never mind the same details
        addObjective()
        addObjective()

        //make sure objectives were added
        val itemsBefore = getItems()
        assert(itemsBefore > 0)

        //delete all objectives
        deleteObjectives()

        //test mass deletion here
        val itemsAfter = getItems()
        assert(itemsAfter == 0)
    }

    @Test
    fun onMenuItemAboutAppClick_showTheAboutAppSection() {
        assert(false) { "Not implemented yet." }
    }

    /** Returns the number of items in the recycler view. */
    private fun getItems(): Int {
        var items = 0
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recycler_view_objectives)
            val adapter = recyclerView.adapter
            items = adapter?.itemCount ?: 0
        }
        return items
    }

    /** Goes to add objective activity & Adds a new test objective. */
    private fun addObjective() {
        onView(withId(R.id.fab_add_objective)).perform(click())
        onView(withId(R.id.add_objective_title)).perform(typeText(testObjectiveTitle))
        onView(withId(R.id.add_objective_description)).perform(typeText(testObjectiveDescription))
        onView(withId(R.id.add_objective_due_date)).perform(typeText(testObjectiveDueDate))
        closeSoftKeyboard()
        onView(withId(R.id.add_objective_submit_button)).perform(click())
        pressBack()
    }

    /** Deletes all objectives from the database. */
    private fun deleteObjectives() {
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)
        onView(withText(R.string.text_delete_objectives)).perform(click())
        onView(withText(R.string.dialog_ok)).perform(click())
    }
}