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
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class NoteActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(NoteActivity::class.java)
    }

    @Test
    fun isVisible_toolbar_note_add() {
        onView(withId(R.id.toolbar_note_add)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editTextNoteTitle() {
        onView(withId(R.id.editTextNoteTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_editTextNoteDetails() {
        onView(withId(R.id.editTextNoteDetails)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonNoteSave() {
        onView(withId(R.id.buttonNoteSave)).check(matches(isDisplayed()))
    }

    @Test
    fun addNewNote() {
        val note = "test note".plus(Random.nextInt(0, 999999999).toString())
        onView(withId(R.id.editTextNoteTitle)).perform(typeText(note))
        closeSoftKeyboard()
        onView(withId(R.id.editTextNoteDetails)).perform(typeText("test note details"))
        closeSoftKeyboard()
        onView(withId(R.id.buttonNoteSave)).perform(click())
        onView(withText(note)).check(matches(isDisplayed()))
    }
}