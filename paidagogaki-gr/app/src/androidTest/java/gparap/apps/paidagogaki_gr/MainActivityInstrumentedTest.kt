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
package gparap.apps.paidagogaki_gr

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import gparap.apps.paidagogaki_gr.adapters.PostAdapter
import org.junit.Assert
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
        Assert.assertEquals("gparap.apps.paidagogaki_gr", appContext.packageName)
    }

    @Test
    fun isVisible_recycleViewMain() {
        onView(withId(R.id.recycleViewMain)).check(matches(isDisplayed()))
    }

    @Test
    fun hasPosts_recycleViewMain() {
        waitForWebServiceResponse()
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recycleViewMain)
            assert(recyclerView.adapter?.itemCount!! > 0)
        }
    }

    @Test
    fun onRecyclerViewItemClick_openItemInNewActivity() {
        waitForWebServiceResponse()
        onView(withId(R.id.recycleViewMain)).perform(
            RecyclerViewActions.actionOnItemAtPosition<PostAdapter.PostViewHolder>(0, click())
        )
        onView(withId(R.id.layout_activity_post)).check(matches(isDisplayed()))
    }

    /* !!! if response is late increase the time !!! */
    private fun waitForWebServiceResponse() {
        Thread.sleep(2667)
    }
}