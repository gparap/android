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
package gparap.apps.wallpaper.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import gparap.apps.wallpaper.R
import gparap.apps.wallpaper.adapter.WallpaperAdapter
import org.hamcrest.Matchers.containsString
import org.junit.Before
import org.junit.Test

class WallpaperActivityInstrumentedTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)

        //click the first wallpaper to redirect to WallpaperActivity
        Thread.sleep(1667)
        onView(withId(R.id.recycler_view_main)).perform(
            RecyclerViewActions.actionOnItemAtPosition<WallpaperAdapter.WallpaperViewHolder>(0,
                click())
        )
    }

    @Test
    @LargeTest
    fun iVisible_image_view_wallpaper() {
        onView(withId(R.id.image_view_wallpaper)).check(matches(isDisplayed()))
    }

    @Test
    @LargeTest
    fun isVisible_floatingActionButton() {
        onView(withId(R.id.fab_menu)).check(matches(isDisplayed()))
    }

    @Test
    @LargeTest
    fun onClickFabMenuDetails_openDialogWithWallpaperDetails() {
        onView(withId(R.id.fab_menu)).perform(click())
        onView(withText(R.string.text_wallpaper_details)).perform(click())
        onView(withClassName(containsString(DetailsDialogFragment::class.simpleName)))
    }
}