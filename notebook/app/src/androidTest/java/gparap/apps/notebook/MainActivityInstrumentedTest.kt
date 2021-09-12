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
package gparap.apps.notebook

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isVisible_toolbar_main() {
        onView(withId(R.id.toolbar_main)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_recyclerViewNotebook() {
        onView(withId(R.id.recyclerViewNotebook)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonNoteAdd() {
        onView(withId(R.id.buttonNoteAdd)).check(matches(isDisplayed()))
    }

    @Test
    fun onClickRecyclerViewItem_openItemInNewActivity() {
        //!!! make sure there is at least on item before running the test
        onView(withId(R.id.recyclerViewNotebook))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<NoteAdapter.NoteViewHolder>(0,click()))
        onView(withId(R.id.layout_activity_note_add)).check(matches(isDisplayed()))
    }

    @Test
    fun onClickButtonAddNote_addNewINote() {
        onView(withId(R.id.buttonNoteAdd)).perform(click())
        onView(withId(R.id.layout_activity_note_add)).check(matches(isDisplayed()))
    }
}