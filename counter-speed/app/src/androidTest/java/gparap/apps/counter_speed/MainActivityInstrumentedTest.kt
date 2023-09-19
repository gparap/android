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
package gparap.apps.counter_speed

import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @get:Rule
    var permissionRule: GrantPermissionRule =
        GrantPermissionRule.grant(android.Manifest.permission.ACCESS_FINE_LOCATION)

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.counter_speed", appContext.packageName)
    }

    @Test
    fun isVisible_textView_speed_label() {
        onView(withId(R.id.textView_speed_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_textView_speed() {
        onView(withId(R.id.textView_speed)).check(matches(isDisplayed()))
    }

    //!!! Leave the route on when testing on emulator
    @Test
    fun onLocationChanges_SpeedChanges() {
        var speedBefore = ""
        var speedAfter = ""

        //hang on a while
        Thread.sleep(667)

        //get the previous speed
        activityScenario.onActivity {
            val textViewSpeed = it.findViewById<TextView>(R.id.textView_speed)
            speedBefore = textViewSpeed.text.toString()
        }

        //hang on a while
        Thread.sleep(1667)

        //get the next speed
        activityScenario.onActivity {
            val textViewSpeed = it.findViewById<TextView>(R.id.textView_speed)
            speedAfter = textViewSpeed.text.toString()
        }

        assertNotEquals(speedBefore, speedAfter)
    }
}