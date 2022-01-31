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
package gparap.apps.wallpaper

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Test

class WallpaperActivityInstrumentedTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(WallpaperActivity::class.java)
    }

    @Test
    @SmallTest
    fun isNotVisible_image_view_wallpaper() {
        onView(withId(R.id.image_view_wallpaper)).check(matches(not(isDisplayed())))
    }

    @Test
    @SmallTest
    fun isVisible_button_download_wallpaper() {
        onView(withId(R.id.button_download_wallpaper)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_button_set_wallpaper() {
        onView(withId(R.id.button_set_wallpaper)).check(matches(isDisplayed()))
    }
}