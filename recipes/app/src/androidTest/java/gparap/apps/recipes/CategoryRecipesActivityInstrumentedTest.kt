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

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import gparap.apps.recipes.utils.AppConstants
import org.hamcrest.core.IsNot.not
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CategoryRecipesActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<CategoryRecipesActivity>

    @Before
    fun setUp() {
        //create an intent with the category name
        val intent = Intent(
            ApplicationProvider.getApplicationContext(),
            CategoryRecipesActivity::class.java
        )
        intent.putExtra(AppConstants.CATEGORY_NAME_EXTRA, "Breakfast")

        //launch activity with intent
        activityScenario = ActivityScenario.launch(intent)
    }

    @Test
    fun isVisible_RecyclerView() {
        onView(withId(R.id.recycler_view_category_recipes)).check(matches(isDisplayed()))
    }

    @Test
    fun isNotVisible_ProgressBar() {
        onView(withId(R.id.progress_category_recipes)).check(matches(not(isDisplayed())))
    }

    @Test
    fun categoryIsSelected_categoryIsNotEmpty() {
        //wait for web service response
        Thread.sleep(AppConstants.WEB_SERVICE_DELAY_SHORT)

        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recycler_view_category_recipes)
            assertTrue(recyclerView.adapter?.itemCount!! > 0)
        }
    }
}