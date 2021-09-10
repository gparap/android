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
package gparap.apps.flashlight

import android.content.Context.CAMERA_SERVICE
import android.hardware.camera2.CameraManager
import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.internal.inject.InstrumentationContext
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Exception

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var decorView: View

    @Before
    fun setUp() {
        //launch activity and retrieve the top-level window decor view
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity {
            decorView = it.window.decorView
        }
    }

    @Test
    fun isSwitcherVisible() {
        onView(withId(R.id.switcher)).check(matches(isDisplayed()))
    }

    @Test
    fun flashlightUnsupportedByDevice_displayErrorMessage() {
        //disable all cameras and try to turn flashlight on
        val cameraManager = InstrumentationRegistry.getInstrumentation().context
            .getSystemService(CAMERA_SERVICE) as CameraManager
        for (i in cameraManager.cameraIdList) {
            try {
                cameraManager.setTorchMode(i, false)
            } catch (e: Exception) {}
        }
        onView(withId(R.id.switcher)).perform(click())

        onView(withText(R.string.toast_unsupported_device))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()))
    }
}