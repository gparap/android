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

import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import gparap.apps.recipes.utils.AppConstants
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class RecipeDetailsActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<RecipeDetailsActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(RecipeDetailsActivity::class.java)
    }

    @Test
    fun isVisible_image_view_recipe_details() {
        onView(withId(R.id.image_view_recipe_details)).check(matches(isDisplayed()))
    }

    @Test
    fun isNotEmpty_Title() {
        destroyAndStartMainActivity()
        sleepAndClickRecipe()
        val activity = getRecipeDetailsActivity()
        val title = activity?.findViewById<TextView>(R.id.text_view_recipe_details_title)
        title?.text?.isNotEmpty()?.let { assert(it) }
    }

    @Test
    fun isVisible_web_view_recipe_details_license() {
        onView(withId(R.id.web_view_recipe_details_image_license)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_recipe_details_category_label() {
        onView(withId(R.id.text_view_recipe_details_category_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isNotEmpty_Category() {
        destroyAndStartMainActivity()
        sleepAndClickRecipe()
        val activity = getRecipeDetailsActivity()
        val title = activity?.findViewById<TextView>(R.id.text_view_recipe_details_category)
        title?.text?.isNotEmpty()?.let { assert(it) }
    }

    @Test
    fun isVisible_text_view_recipe_details_servings_label() {
        onView(withId(R.id.text_view_recipe_details_servings_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isNotEmpty_Servings() {
        destroyAndStartMainActivity()
        sleepAndClickRecipe()
        val activity = getRecipeDetailsActivity()
        val title = activity?.findViewById<TextView>(R.id.text_view_recipe_details_servings)
        title?.text?.isNotEmpty()?.let { assert(it) }
    }

    @Test
    fun isVisible_text_view_recipe_details_time_label() {
        onView(withId(R.id.text_view_recipe_details_time_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isNotEmpty_Time() {
        destroyAndStartMainActivity()
        sleepAndClickRecipe()
        val activity = getRecipeDetailsActivity()
        val title = activity?.findViewById<TextView>(R.id.text_view_recipe_details_time)
        title?.text?.isNotEmpty()?.let { assert(it) }
    }

    @Test
    fun isVisible_text_view_recipe_details_difficulty_label() {
        onView(withId(R.id.text_view_recipe_details_difficulty_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isNotEmpty_Difficulty() {
        destroyAndStartMainActivity()
        sleepAndClickRecipe()
        val activity = getRecipeDetailsActivity()
        val title = activity?.findViewById<TextView>(R.id.text_view_recipe_details_difficulty)
        title?.text?.isNotEmpty()?.let { assert(it) }
    }

    @Test
    fun isNotEmpty_Description() {
        destroyAndStartMainActivity()
        sleepAndClickRecipe()
        val activity = getRecipeDetailsActivity()
        val title = activity?.findViewById<TextView>(R.id.text_view_recipe_details_desc)
        title?.text?.isNotEmpty()?.let { assert(it) }
    }

    @Test
    fun isVisible_text_view_recipe_details_ingredients_label() {
        onView(withId(R.id.text_view_recipe_details_ingredients_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isNotEmpty_Ingredients() {
        destroyAndStartMainActivity()
        sleepAndClickRecipe()
        val activity = getRecipeDetailsActivity()
        val title = activity?.findViewById<TextView>(R.id.text_view_recipe_details_ingredients)
        title?.text?.isNotEmpty()?.let { assert(it) }
    }

    @Test
    fun isVisible_text_view_recipe_details_steps_label() {
        onView(withId(R.id.text_view_recipe_details_steps_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isNotEmpty_Steps() {
        destroyAndStartMainActivity()
        sleepAndClickRecipe()
        val activity = getRecipeDetailsActivity()
        val title = activity?.findViewById<TextView>(R.id.text_view_recipe_details_steps)
        title?.text?.isNotEmpty()?.let { assert(it) }
    }

    @Test
    fun isVisible_text_view_recipe_details_notes_label() {
        onView(withId(R.id.text_view_recipe_details_notes_label)).check(matches(isDisplayed()))
    }

    /* Destroy current scenario and launch main activity. */
    private fun destroyAndStartMainActivity() {
        activityScenario.moveToState(Lifecycle.State.DESTROYED)
        ActivityScenario.launch(MainActivity::class.java)
    }

    /* Wait for the web service response and Click the first recipe. */
    private fun sleepAndClickRecipe() {
        Thread.sleep(AppConstants.WEB_SERVICE_DELAY_LONG)
        onView(withId(R.id.image_view_random_recipe)).perform(click())
    }

    /* Return the resumed RecipeDetailsActivity. */
    private fun getRecipeDetailsActivity(): RecipeDetailsActivity? {
        var activity: RecipeDetailsActivity? = null
        getInstrumentation().runOnMainSync {
            val activities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED)
            activities.onEach {
                activity = it as RecipeDetailsActivity?
            }
        }
        return activity
    }
}