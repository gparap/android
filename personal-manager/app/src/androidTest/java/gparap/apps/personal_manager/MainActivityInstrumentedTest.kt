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
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

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
        //objectives in the recycler view
        var itemsBefore = 0
        var itemsAfter = 0

        //get how many objective are there before inserting a new one
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recycler_view_objectives)
            val adapter = recyclerView.adapter
            itemsBefore = adapter?.itemCount ?: 0
        }

        //goto add objective activity
        onView(withId(R.id.fab_add_objective)).perform(click())

        //add a new test objective
        onView(withId(R.id.add_objective_title)).perform(typeText("test title"))
        onView(withId(R.id.add_objective_description)).perform(typeText("test desc"))
        onView(withId(R.id.add_objective_due_date)).perform(typeText("test due date"))
        closeSoftKeyboard()
        onView(withId(R.id.add_objective_submit_button)).perform(click())
        pressBack()

        //get how many objective are there after inserting a new one
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recycler_view_objectives)
            val adapter = recyclerView.adapter
            itemsAfter = adapter?.itemCount ?: 0
        }

        //test here if objective added successfully
        assert(itemsAfter > itemsBefore)
    }
}