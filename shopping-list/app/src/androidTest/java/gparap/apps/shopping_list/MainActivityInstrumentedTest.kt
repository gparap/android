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
package gparap.apps.shopping_list

import android.view.View
import androidx.core.view.size
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class MainActivityInstrumentedTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    @SmallTest
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("gparap.apps.shopping_list", appContext.packageName)
    }

    @Test
    @SmallTest
    fun isVisible_recyclerViewCategories() {
        onView(withId(R.id.recycler_view_categories)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun isVisible_fabAddCategory() {
        onView(withId(R.id.fab_add_shopping_category)).check(matches(isDisplayed()))
    }

    @Test
    @SmallTest
    fun onClickFabAddCategory_openDialogForAddingNew() {
        onView(withId(R.id.fab_add_shopping_category)).perform(click())
        onView(withId(R.id.layout_add_category)).check(matches(isDisplayed()))
    }

    @Test
    @MediumTest
    fun onClickFabAddCategory_addNewCategory() {
        val testCategory = "add a test category"

        //add a test category
        onView(withId(R.id.fab_add_shopping_category)).perform(click())
        onView(withId(R.id.edit_text_add_category_name)).perform(typeText(testCategory))
        closeSoftKeyboard()
        onView(withText(R.string.dialog_button_ok)).perform(click())

        //test here
        onView(withText(testCategory)).check(matches(isDisplayed()))

        //!!!   important (don't mess up the database)
        deleteTestCategory()
    }

    @Test
    @MediumTest
    fun onClickImageViewEditCategoryOnRecyclerViewItem_updateExistingCategory() {
        val testCategory = "test category"
        val testCategoryEdited = "test category edited"

        //add a test category
        onView(withId(R.id.fab_add_shopping_category)).perform(click())
        onView(withId(R.id.edit_text_add_category_name)).perform(typeText(testCategory))
        closeSoftKeyboard()
        onView(withText(R.string.dialog_button_ok)).perform(click())

        //edit the test category
        onView(withId(R.id.recycler_view_categories)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                getRecyclerViewItemsCount() - 1,
                getImageViewClickAction(R.id.image_view_edit_category)
            )
        )
        onView(withId(R.id.edit_text_edit_category_name)).perform(clearText())
        onView(withId(R.id.edit_text_edit_category_name)).perform(typeText(testCategoryEdited))
        closeSoftKeyboard()
        onView(withText(R.string.dialog_button_ok)).perform(click())

        //test here
        onView(withText(testCategoryEdited)).check(matches(isDisplayed()))

        //!!!   important (don't mess up the database)
        deleteTestCategory()
    }

    @Test
    @MediumTest
    fun onClickRecyclerViewCategory_openCategoryItemDetails() {
        val testCategory = "test category"

        //add a test category
        onView(withId(R.id.fab_add_shopping_category)).perform(click())
        onView(withId(R.id.edit_text_add_category_name)).perform(typeText(testCategory))
        closeSoftKeyboard()
        onView(withText(R.string.dialog_button_ok)).perform(click())

        //click on test category to see its items
        val position = getRecyclerViewItemsCount() - 1
        onView(withId(R.id.recycler_view_categories)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                click()
            )
        )

        //test here
        onView(withId(R.id.layout_activity_item)).check(matches(isDisplayed()))
        onView(withText(testCategory)).check(matches(isDisplayed()))

        //!!!   important (don't mess up the database)
        Espresso.pressBackUnconditionally()
        deleteTestCategory()
    }

    private fun deleteTestCategory() {
        //get the position of the last item on the RecyclerView
        val position = getRecyclerViewItemsCount() - 1

        //press delete test category button
        onView(withId(R.id.recycler_view_categories)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                position,
                getImageViewClickAction(R.id.image_view_delete_category)
            )
        )

        //delete test category
        onView(withText(R.string.dialog_button_ok)).perform(click())
    }

    private fun getImageViewClickAction(viewId: Int): ViewAction {
        return ImageViewClickAction(viewId)
    }

    private fun getRecyclerViewItemsCount(): Int {
        var items = 0
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recycler_view_categories)
            items = recyclerView.size
        }
        return items
    }
}

/** Perform a click action on the delete category ImageView. */
class ImageViewClickAction(private val viewId: Int) : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return click().constraints
    }

    override fun getDescription(): String {
        return "click delete category ImageView"
    }

    override fun perform(uiController: UiController?, view: View?) {
        click().perform(uiController, view?.findViewById(viewId))
    }
}
