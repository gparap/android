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

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import gparap.apps.personal_manager.ui.AddObjectiveActivity
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddObjectiveActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(AddObjectiveActivity::class.java)
    }

    @Test
    fun isVisible_add_objective_title() {
        onView(withId(R.id.add_objective_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_add_objective_description() {
        onView(withId(R.id.add_objective_description)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_add_objective_due_date() {
        onView(withId(R.id.add_objective_due_date)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_add_objective_submit_button() {
        onView(withId(R.id.add_objective_submit_button)).check(matches(isDisplayed()))
    }
}