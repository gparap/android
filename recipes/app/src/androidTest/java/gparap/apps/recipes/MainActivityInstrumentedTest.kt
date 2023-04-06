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
package gparap.apps.recipes

import android.content.pm.ActivityInfo
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import gparap.apps.recipes.utils.AppConstants
import org.junit.Assert.*
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
    fun bottomNavigation_navigateToHomeFragment() {
        //navigate to another fragment first
        onView(withId(R.id.categoriesFragment)).perform(click())

        //navigate to home
        onView(withId(R.id.homeFragment)).perform(click())
        onView(withId(R.id.layout_fragment_home)).check(matches(isDisplayed()))
    }

    @Test
    fun bottomNavigation_navigateToCategoriesFragment() {
        //navigate to home fragment first
        onView(withId(R.id.homeFragment)).perform(click())

        //navigate to categories
        onView(withId(R.id.categoriesFragment)).perform(click())
        onView(withId(R.id.layout_fragment_categories)).check(matches(isDisplayed()))
    }

    @Test
    fun bottomNavigation_navigateToFavoritesFragment() {
        //navigate to home fragment first
        onView(withId(R.id.homeFragment)).perform(click())

        //navigate to favorites
        onView(withId(R.id.favoritesFragment)).perform(click())
        onView(withId(R.id.layout_fragment_favorites)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_text_view_random_recipe() {
        navigateToFragment(R.id.homeFragment)
        onView(withId(R.id.text_view_random_recipe)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_card_view_random_recipe() {
        navigateToFragment(R.id.homeFragment)
        onView(withId(R.id.card_view_random_recipe)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_image_view_random_recipe() {
        navigateToFragment(R.id.homeFragment)
        onView(withId(R.id.image_view_random_recipe)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_text_view_featured_recipes() {
        navigateToFragment(R.id.homeFragment)
        onView(withId(R.id.text_view_featured_recipes)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_recycler_view_featured_recipes() {
        navigateToFragment(R.id.homeFragment)
        onView(withId(R.id.recycler_view_featured_recipes)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_recycle_view_recipe_categories() {
        navigateToFragment(R.id.categoriesFragment)
        onView(withId(R.id.recycle_view_recipe_categories)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_homeFragment_recycle_view_favorite_recipes() {
        navigateToFragment(R.id.favoritesFragment)
        onView(withId(R.id.recycle_view_favorite_recipes)).check(matches(isDisplayed()))
    }

    @Test
    fun isNotEmpty_categoriesRecyclerView() {
        navigateToFragment(R.id.categoriesFragment)
        waitForTheWebServiceResponse(AppConstants.WEB_SERVICE_DELAY_SHORT)
        try {
            activityScenario.onActivity {
                val recyclerView =
                    it.findViewById<RecyclerView>(R.id.recycle_view_recipe_categories)
                assert(recyclerView.childCount > 0)
            }
        } catch (_: java.lang.AssertionError) {
            //navigateToFragment(R.id.categoriesFragment)
            waitForTheWebServiceResponse(AppConstants.WEB_SERVICE_DELAY_LONG)
            activityScenario.onActivity {
                val recyclerView =
                    it.findViewById<RecyclerView>(R.id.recycle_view_recipe_categories)
                assert(recyclerView.childCount > 0)
            }
        }
    }

    @Test
    fun onDeviceOrientationChange_categoriesRecyclerViewDataPersist() {
        var childCountBeforeRotation = 0
        var childCountAfterRotation = 0

        //start with portrait orientation and get the RecyclerView's childCount
        setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        navigateToFragment(R.id.categoriesFragment)
        waitForTheWebServiceResponse(AppConstants.WEB_SERVICE_DELAY_LONG)
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recycle_view_recipe_categories)
            childCountBeforeRotation = recyclerView.childCount
        }

        //rotate the device and get the RecyclerView's childCount
        setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        waitForTheWebServiceResponse(AppConstants.WEB_SERVICE_DELAY_LONG)
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recycle_view_recipe_categories)
            childCountAfterRotation = recyclerView.childCount
        }

        assertEquals(childCountBeforeRotation, childCountAfterRotation)
    }

    private fun navigateToFragment(fragmentId: Int) {
        onView(withId(fragmentId)).perform(click())
    }

    private fun waitForTheWebServiceResponse(delay: Long) {
        when (delay) {
            AppConstants.WEB_SERVICE_DELAY_LONG -> Thread.sleep(AppConstants.WEB_SERVICE_DELAY_LONG)
            AppConstants.WEB_SERVICE_DELAY_SHORT -> Thread.sleep(AppConstants.WEB_SERVICE_DELAY_SHORT)
        }
    }

    private fun setScreenOrientation(orientation: Int) {
        activityScenario.onActivity {
            it.requestedOrientation = orientation
        }
    }
}