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
package gparap.apps.news

import android.widget.ListView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
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
    fun isVisible_listViewStories() {
        onView(withId(R.id.listViewStories)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_buttonLoadMoreStories() {
        onView(withId(R.id.buttonLoadMoreStories)).check(matches(isDisplayed()))
    }

    @Test
    fun onAppLoad_loadStories(){
        var storiesCount = 0

        //get stories count
        activityScenario.onActivity { activityScenarioRule ->
            val listView = activityScenarioRule.findViewById<ListView>(R.id.listViewStories)
            storiesCount = listView.adapter?.count!!
        }

        assert(storiesCount > 0)
    }

    @Test
    fun onButtonClick_loadMoreStories(){
        var storiesCountBefore = 0
        var storiesCountAfter = 0

        //get stories count before loading more of them
        activityScenario.onActivity { activityScenarioRule ->
            val listView = activityScenarioRule.findViewById<ListView>(R.id.listViewStories)
            storiesCountBefore = listView.adapter?.count!!
        }

        //load more stories (and wait a little to load)
        onView(withId(R.id.buttonLoadMoreStories)).perform(click())
        Thread.sleep(5000)

        //get stories count after loading more of them
        activityScenario.onActivity { activityScenarioRule ->
            val listView = activityScenarioRule.findViewById<ListView>(R.id.listViewStories)
            storiesCountAfter = listView.adapter?.count!!
        }

        assert(storiesCountAfter > storiesCountBefore)
    }

    @Test
    fun onMenuOptionRefreshClick_removeStoriesAndLoadFromTheStart() {
        var storiesCountRefresh = 0
        var storiesCountLoadMore = 0

        //load more stories (and wait a little to load)
        onView(withId(R.id.buttonLoadMoreStories)).perform(click())

        //get stories count after loading more of them
        activityScenario.onActivity { activityScenarioRule ->
            val listView = activityScenarioRule.findViewById<ListView>(R.id.listViewStories)
            storiesCountLoadMore = listView.adapter?.count!!
        }

        //refresh stories
        Espresso.openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().context)
        onView(withText(R.string.refresh)).perform(click())

        //get stories count after refreshing them
        activityScenario.onActivity { activityScenarioRule ->
            val listView = activityScenarioRule.findViewById<ListView>(R.id.listViewStories)
            storiesCountRefresh = listView.adapter?.count!!
        }

        assert(storiesCountRefresh < storiesCountLoadMore)
    }

}