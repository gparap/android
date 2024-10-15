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
package gparap.apps.personal_finances

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun isVisible_imageView_topBackgroundGradient() {
        onView(withId(R.id.imageView_topBackgroundGradient)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_appLogo() {
        onView(withId(R.id.imageView_appLogo)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_appTitle() {
        onView(withId(R.id.textView_appTitle)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_sectionTitle_current() {
        onView(withId(R.id.textView_sectionTitle_current)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_section_1_title() {
        onView(withId(R.id.textView_section_1_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_section_1_background() {
        onView(withId(R.id.imageView_section_1_background)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_section_2_title() {
        onView(withId(R.id.textView_section_2_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_section_2_background() {
        onView(withId(R.id.imageView_section_2_background)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_section_3_title() {
        onView(withId(R.id.textView_section_3_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_section_3_background() {
        onView(withId(R.id.imageView_section_3_background)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_section_4_title() {
        onView(withId(R.id.textView_section_4_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_imageView_section_4_background() {
        onView(withId(R.id.imageView_section_4_background)).check(matches(isDisplayed()))
    }

    @Test
    fun isCorrect_redirectToAddTransactionActivity() {
        onView(withId(R.id.fab_addTransaction)).perform(click())
        onView(withId(R.id.layout_activity_add_transaction)).check(matches(isDisplayed()))
    }
}