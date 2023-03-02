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

import android.view.View
import android.widget.ScrollView
import androidx.core.view.get
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.gms.ads.AdView
import gparap.apps.music.ui.MainActivity
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SongActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testIfAppBarIsTheSameAsTheSelectedSong() {
        //!!! it is always the 1st song in the songs API
        val songTitle = "Achaidh Cheide (ISRC USUAN1100340)"

        //open main menu's "world music" option
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.world_music)).perform(click())

        //wait a little for web service response and click the first song
        Thread.sleep(1667)
        onView(withText(songTitle)).perform(click())

        onView(withId(androidx.appcompat.R.id.action_bar)).check(
            matches(hasDescendant(withText(songTitle)))
        )
    }

    @Test
    fun testIfEmptyFieldAndItsLabelAreHidden() {
        //!!! it is always the 1st song in the songs API
        val songTitle = "Achaidh Cheide (ISRC USUAN1100340)"

        //open main menu's "world music" option
        val context = InstrumentationRegistry.getInstrumentation().context
        Espresso.openActionBarOverflowOrOptionsMenu(context)
        onView(withText(R.string.world_music)).perform(click())

        //wait a little for web service response and click the first song
        Thread.sleep(1667)
        onView(withText(songTitle)).perform(click())

        onView(withId(R.id.text_view_links_image_link)).check(matches(not(isDisplayed())))
        onView(withId(R.id.label_links_image_link)).check(matches(not(isDisplayed())))
    }
}