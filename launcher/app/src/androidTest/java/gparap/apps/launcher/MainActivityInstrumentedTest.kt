/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.launcher

import android.view.View
import android.widget.GridView
import android.widget.ImageView
import androidx.core.view.size
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    @SmallTest
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.launcher", appContext.packageName)
    }

    @Test
    @SmallTest
    fun isVisible_frameLayout() {
        onView(withId(R.id.frame_layout_apps)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_gridView() {
        onView(withId(R.id.grid_view_apps_bottom)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isGridPopulatedWithAppLaunchers() {
        activityScenario.onActivity {
            val grid = it.findViewById<GridView>(R.id.grid_view_apps_bottom)
            assert(grid.size > 0)
        }
    }

    @Test
    @LargeTest
    @Ignore("!!! This test in not recommended. Use with care...")
    fun launchExternalApplicationFromGrid() {
        activityScenario.onActivity {
            val grid = it.findViewById<GridView>(R.id.grid_view_apps_bottom)

            val imageView: View = grid.requireViewById<ImageView>(R.id.image_view_app_icon)
            imageView.performClick()

        }
        try {
            Espresso.pressBackUnconditionally()

        } catch (e: Exception) {
            if (e is androidx.test.espresso.NoActivityResumedException) {
                assert(true)
            }
        }
    }
}