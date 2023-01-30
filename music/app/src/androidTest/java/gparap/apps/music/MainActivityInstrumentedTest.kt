/*
 * Copyright (c) 2023 gparap
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
import gparap.apps.music.ui.MainActivity
import org.hamcrest.core.IsNot.not
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
    fun isNotVisible_SongsLoadingProgressBar() {
        onView(withId(R.id.progressBarLoadingSongs)).check(matches(not(isDisplayed())))
    }

    @Test
    fun isVisible_welcomeText() {
        onView(withId(R.id.textViewWelcome_main)).check(matches(isDisplayed()))
        onView(withId(R.id.textViewWelcome_secondary)).check(matches(isDisplayed()))
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
        onView(withText(R.string.classical_music)).perform(click())
        onView(withText(R.string.medieval_period)).perform(click())

        //wait a little for web service response
        Thread.sleep(1667)

        //test here
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerViewSongs)
            assert(recyclerView.adapter?.itemCount!! > 0)
        }
    }

    @Test
    fun getMedievalSongs_appBarTitleUpdatedCorrectly() {
        //open main menu's "medieval music" option
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.classical_music)).perform(click())
        onView(withText(R.string.medieval_period)).perform(click())

        activityScenario.onActivity {
            assert(it.supportActionBar?.title == context.resources.getString(R.string.medieval_period))
        }
    }

    @Test
    fun getRenaissanceSongs_recyclerViewNotEmpty() {
        //open main menu's "renaissance music" option
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.classical_music)).perform(click())
        onView(withText(R.string.renaissance_period)).perform(click())

        //wait a little for web service response
        Thread.sleep(1667)

        //test here
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerViewSongs)
            assert(recyclerView.adapter?.itemCount!! > 0)
        }
    }

    @Test
    fun getRenaissanceSongs_appBarTitleUpdatedCorrectly() {
        //open main menu's "renaissance music" option
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.classical_music)).perform(click())
        onView(withText(R.string.renaissance_period)).perform(click())

        activityScenario.onActivity {
            assert(it.supportActionBar?.title == context.resources.getString(R.string.renaissance_period))
        }
    }

    @Test
    fun getBaroqueSongs_recyclerViewNotEmpty() {
        //open main menu's "baroque music" option
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.classical_music)).perform(click())
        onView(withText(R.string.baroque_period)).perform(click())

        //wait a little for web service response
        Thread.sleep(1667)

        //test here
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerViewSongs)
            assert(recyclerView.adapter?.itemCount!! > 0)
        }
    }

    @Test
    fun getBaroqueSongs_appBarTitleUpdatedCorrectly() {
        //open main menu's "baroque music" option
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.classical_music)).perform(click())
        onView(withText(R.string.baroque_period)).perform(click())

        activityScenario.onActivity {
            assert(it.supportActionBar?.title == context.resources.getString(R.string.baroque_period))
        }
    }

    @Test
    fun getClassicalSongs_recyclerViewNotEmpty() {
        //open main menu's "classical music" option
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.classical_music)).perform(click())
        onView(withText(R.string.classical_period)).perform(click())

        //wait a little for web service response
        Thread.sleep(1667)

        //test here
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerViewSongs)
            assert(recyclerView.adapter?.itemCount!! > 0)
        }
    }

    @Test
    fun getClassicalSongs_appBarTitleUpdatedCorrectly() {
        //open main menu's "classical music" option
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.classical_music)).perform(click())
        onView(withText(R.string.classical_period)).perform(click())

        activityScenario.onActivity {
            assert(it.supportActionBar?.title == context.resources.getString(R.string.classical_music))
        }
    }

    @Test
    fun getInstrumentalSongs_recyclerViewNotEmpty() {
        //open main menu's "instrumental music" option
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.instrumental_music)).perform(click())

        //wait a little for web service response
        Thread.sleep(1667)

        //test here
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerViewSongs)
            assert(recyclerView.adapter?.itemCount!! > 0)
        }
    }

    @Test
    fun getInstrumentalSongs_appBarTitleUpdatedCorrectly() {
        //open main menu's "instrumental music" option
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.instrumental_music)).perform(click())

        activityScenario.onActivity {
            assert(it.supportActionBar?.title == context.resources.getString(R.string.instrumental_music))
        }
    }

    @Test
    fun getTraditionalSongs_recyclerViewNotEmpty() {
        //open main menu's "traditional music" option
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.traditional_music)).perform(click())

        //wait a little for web service response
        Thread.sleep(1667)

        activityScenario.onActivity {
            assert(it.supportActionBar?.title == context.resources.getString(R.string.traditional_music))
        }
    }

    @Test
    fun getTraditionalSongs_appBarTitleUpdatedCorrectly() {
        //open main menu's "traditional music" option
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.traditional_music)).perform(click())

        //test here
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerViewSongs)
            assert(recyclerView.adapter?.itemCount!! > 0)
        }
    }

    @Test
    fun getFolkSongs_recyclerViewNotEmpty() {
        //open main menu's "folk music" option
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.folk_music)).perform(click())

        //wait a little for web service response
        Thread.sleep(1667)

        //test here
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerViewSongs)
            assert(recyclerView.adapter?.itemCount!! > 0)
        }
    }

    @Test
    fun getFolkSongs_appBarTitleUpdatedCorrectly() {
        //open main menu's "folk music" option
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.folk_music)).perform(click())

        //test here
        activityScenario.onActivity {
            assert(it.supportActionBar?.title == context.resources.getString(R.string.folk_music))
        }
    }

    @Test
    fun getWorldSongs_recyclerViewNotEmpty() {
        //open main menu's "world music" option
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.world_music)).perform(click())

        //wait a little for web service response
        Thread.sleep(1667)

        //test here
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerViewSongs)
            assert(recyclerView.adapter?.itemCount!! > 0)
        }
    }

    @Test
    fun getWorldSongs_appBarTitleUpdatedCorrectly() {
        //open main menu's "world music" option
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.world_music)).perform(click())

        activityScenario.onActivity {
            assert(it.supportActionBar?.title == context.resources.getString(R.string.world_music))
        }
    }

    @Test
    fun hideWelcomeTextWhenChoosingCategory() {
        //open main menu's category option
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.world_music)).perform(click())

        //assert welcome text is hidden
        onView(withId(R.id.textViewWelcome_main)).check(matches(not(isDisplayed())))
        onView(withId(R.id.textViewWelcome_secondary)).check(matches(not(isDisplayed())))
    }

    @Test
    fun showSongsLoadingProgressBarWhenChoosingCategory() {
        //progress must be hidden
        onView(withId(R.id.progressBarLoadingSongs)).check(matches(not(isDisplayed())))

        //open main menu's category option
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.world_music)).perform(click())

        //progress must be visible
        onView(withId(R.id.progressBarLoadingSongs)).check(matches(isDisplayed()))
    }
}