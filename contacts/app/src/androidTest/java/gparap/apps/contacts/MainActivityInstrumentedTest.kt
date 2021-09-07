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
package gparap.apps.contacts

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test

class MainActivityInstrumentedTest {
    @Before
    @Throws(Exception::class)
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isVisible_buttonShowContacts() {
        onView(withId(R.id.buttonShowContacts)).check(matches(isDisplayed()))
    }

    @Test
    fun isNotVisible_recyclerViewContacts() {
        onView(withId(R.id.recyclerViewContacts)).check(matches(not(isDisplayed())))
    }

    @Test
    //!!! in this test, we take for granted that the phone has contacts.
    fun onButtonClick_showContacts() {
        onView(withId(R.id.buttonShowContacts)).perform(click())

        onView(withId(R.id.recyclerViewContacts)).check(matches(isDisplayed()))
        try {
            onView(withId(R.id.imageButtonCall)).check(matches(isDisplayed()))
        } catch (e: androidx.test.espresso.AmbiguousViewMatcherException) {
            assert(true)
        }

    }
}