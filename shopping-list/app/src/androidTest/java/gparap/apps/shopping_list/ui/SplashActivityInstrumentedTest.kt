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
package gparap.apps.shopping_list.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import gparap.apps.shopping_list.R
import gparap.apps.shopping_list.utils.AppConstants
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class SplashActivityInstrumentedTest {

    @Before
    fun setUp() {
        ActivityScenario.launch(SplashActivity::class.java)
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.shopping_list", appContext.packageName)
    }

    @Test
    fun isSplashScreenLogoVisible() {
        onView(withId(R.id.image_view_splash)).check(matches(isDisplayed()))
    }

    @Test
    fun gotoCategoryActivityAfterSplashing() {
        Thread.sleep(AppConstants.delayMills)
        onView(withId(R.id.layout_activity_category)).check(matches(isDisplayed()))
    }
}