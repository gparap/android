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
package gparap.apps.selfie_editor

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.core.IsNot.not
import org.junit.Assert.*
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private lateinit var decorView: View

    @Before
    fun setUp() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity {
            //get the top-level window view
            decorView = it.window.decorView
        }
    }

    @Test
    @Ignore("Grant permissions first..")
    fun isVisible_ImageButton() {
        onView(withId(R.id.selfie_capture_button)).check(matches(isDisplayed()))
    }

    @Test
    @Ignore("Grant permissions first..")
    fun takeSelfie_displayToast() {
        onView(withId(R.id.selfie_capture_button)).perform(click())
        onView(withText(R.string.toast_selfie_captured))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))
    }

    @Test
    @Ignore("Grant permissions first..")
    fun takeSelfie_displayDialogPrompt() {
        onView(withId(R.id.selfie_capture_button)).perform(click())
        Thread.sleep(TOAST_LONG_DELAY_MILLIS)
        onView(withText(R.string.dialog_title)).check(matches(isDisplayed()))
    }

    @Test
    @Ignore("Grant permissions first..")
    fun takeSelfie_openSavedImageInEditorActivity() {
        onView(withId(R.id.selfie_capture_button)).perform(click())
        Thread.sleep(TOAST_LONG_DELAY_MILLIS)
        onView(withText(R.string.dialog_yes)).perform(click())
        onView(withId(R.id.layout_activity_editor)).check(matches(isDisplayed()))
    }

    companion object {
        const val TOAST_LONG_DELAY_MILLIS = 3500L
    }
}