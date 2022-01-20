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
package gparap.apps.horoscope.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.SmallTest
import gparap.apps.horoscope.R
import gparap.apps.horoscope.utils.AppConstants
import org.junit.Before
import org.junit.Test

class SplashActivityInstrumentedTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(SplashActivity::class.java)
    }

    @Test
    @SmallTest
    fun areVisible_splashScreenViews() {
        //!!! wait for the animation
        Thread.sleep(AppConstants.SPLASH_ANIMATION_DURATION)

        //!!! test everything here because of the splash
        onView(withId(R.id.textViewAppName)).check(matches(isDisplayed()))
        onView(withId(R.id.imageViewLogo)).check(matches(isDisplayed()))
        onView(withId(R.id.imageViewZodiac)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun afterSplash_gotoMainActivity() {
        //!!! wait for the splash
        Thread.sleep(AppConstants.SPLASH_SCREEN_DURATION)

        onView(withId(R.id.layout_activity_main)).check(matches(isDisplayed()))
    }
}