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
package gparap.apps.music

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Before
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
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.music", appContext.packageName)
    }

    @Test
    fun isVisible_recyclerViewSongs() {
        onView(withId(R.id.recyclerViewSongs)).check(matches(isDisplayed()))
    }

    @Test
    fun getMedievalSongs_recyclerViewNotEmpty() {
        //open main menu's "medieval music" option
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.medieval_period)).perform(click())

        //wait a little for web service response
        Thread.sleep(1667)

        //test here
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerViewSongs)
            assert(recyclerView.adapter?.itemCount!! > 0)
        }
    }
}