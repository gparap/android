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
    fun isVisible_web_view_recipe_details_license() {
        onView(withId(R.id.web_view_recipe_details_license)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_recipe_details_title() {
        onView(withId(R.id.text_view_recipe_details_title)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_recipe_details_category_label() {
        onView(withId(R.id.text_view_recipe_details_category_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_recipe_details_servings_label() {
        onView(withId(R.id.text_view_recipe_details_servings_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_recipe_details_time_label() {
        onView(withId(R.id.text_view_recipe_details_time_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_recipe_details_difficulty_label() {
        onView(withId(R.id.text_view_recipe_details_difficulty_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_recipe_details_ingredients_label() {
        onView(withId(R.id.text_view_recipe_details_ingredients_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_recipe_details_steps_label() {
        onView(withId(R.id.text_view_recipe_details_steps_label)).check(matches(isDisplayed()))
    }

    @Test
    fun isVisible_text_view_recipe_details_notes_label() {
        onView(withId(R.id.text_view_recipe_details_notes_label)).check(matches(isDisplayed()))
    }

}