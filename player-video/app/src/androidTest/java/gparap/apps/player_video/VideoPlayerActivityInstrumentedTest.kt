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
package gparap.apps.player_video

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VideoPlayerActivityInstrumentedTest {
    @Before
    fun setUp() {
        ActivityScenario.launch(VideoPlayerActivity::class.java)
    }

    @Test
    fun isVisible_player_view() {
        onView(withId(R.id.player_view)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_image_button_play_video() {
        onView(withId(R.id.image_button_play_video)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_image_button_pause_video() {
        onView(withId(R.id.image_button_pause_video)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_image_button_stop_video() {
        onView(withId(R.id.image_button_stop_video)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_default_time_bar() {
        onView(withId(R.id.default_time_bar)).check(matches(isDisplayed()))
    }
}