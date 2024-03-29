package gparap.apps.authentication

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/*
 * Copyright 2023 gparap
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
@RunWith(AndroidJUnit4::class)
class LoginActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(LoginActivity::class.java)
    }

    @Test
    @SmallTest
    fun isVisible_editTextEmail() {
        onView(withId(R.id.editTextEmail)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_imageViewEmail() {
        onView(withId(R.id.imageViewEmail)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_editTextPassword() {
        onView(withId(R.id.editTextPassword)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_imageViewPassword() {
        onView(withId(R.id.imageViewPassword)).check(matches(isDisplayed()))
    }
    @Test
    @SmallTest
    fun isVisible_textViewGoToRegistration() {
        onView(withId(R.id.textViewGoToRegistration)).check(matches(isDisplayed()))
    }
    @Test
    @SmallTest
    fun isVisible_buttonLogin() {
        onView(withId(R.id.buttonLogin)).check(matches(isDisplayed()))
    }
}