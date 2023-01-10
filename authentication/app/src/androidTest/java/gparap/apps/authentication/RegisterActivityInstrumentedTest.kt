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
class RegisterActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(RegisterActivity::class.java)
    }

    @Test
    @SmallTest
    fun isVisible_editTextUsername() {
        onView(withId(R.id.editTextUsername)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_imageViewUsername() {
        onView(withId(R.id.imageViewUsername)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_editTextEmail() {
        onView(withId(R.id.editTextEmail_register)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_imageViewEmail() {
        onView(withId(R.id.imageViewEmail_register)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_editTextPassword() {
        onView(withId(R.id.editTextPassword_register)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_imageViewPassword() {
        onView(withId(R.id.imageViewPassword_register)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_editTextPasswordConfirm() {
        onView(withId(R.id.editTextPasswordConfirm_register)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_imageViewPasswordConfirm() {
        onView(withId(R.id.imageViewPasswordConfirm_register)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_buttonRegister() {
        onView(withId(R.id.buttonRegister)).check(matches(isDisplayed()))
    }
}