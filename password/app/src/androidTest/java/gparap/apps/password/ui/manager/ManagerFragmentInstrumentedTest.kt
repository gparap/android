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
package gparap.apps.password.ui.manager

import android.database.sqlite.SQLiteDatabase
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import gparap.apps.password.MainActivity
import gparap.apps.password.R
import gparap.apps.password.adapters.PasswordAdapter
import gparap.apps.password.data.DatabaseHelper
import gparap.apps.password.data.DatabaseManager
import gparap.apps.password.data.PasswordModel
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsNot.not
import org.hamcrest.core.StringEndsWith.endsWith
import org.junit.After
import org.junit.Before
import org.junit.Test


class ManagerFragmentInstrumentedTest {
    private lateinit var dbManager: DatabaseManager
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var query: SQLiteDatabase
    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private lateinit var rootView: View

    @Suppress("PropertyName")
    val TEST_VALUE = "this is a test password value"

    @Before
    fun setUp() {
        addAtLeastOnePasswordToDatabase()

        //goto manager fragment
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.navigation_manager)).perform(ViewActions.click())

        //get root view
        activityScenario.onActivity {
            rootView = it.window.decorView
        }

        //wait to load sqlite results
        Thread.sleep(500)
    }

    @Test
    fun recyclerView_switchShowHidePassword() {
        //get the number of items in the recycler view
        val recyclerViewChildCount = getRecyclerViewItemCount()

        //press switch widget to make (the last) password visible
        onView(withId(R.id.recyclerViewPasswords))
            .perform(
                actionOnItemAtPosition<PasswordAdapter.ViewHolder>(
                    recyclerViewChildCount - 1, recyclerViewAction(R.id.switchShowHidePassword)
                )
            )

        //test if the password becomes visible
        onView(
            allOf(
                withClassName(endsWith("EditText")),
                withText(`is`(TEST_VALUE))
            )
        ).check(matches(isPasswordVisible()))
    }

    @Test
    fun deletePassword() {
        //get the old number of items in the recycler view
        val recyclerViewChildCountOld = getRecyclerViewItemCount()

        //delete the test password value
        onView(withId(R.id.recyclerViewPasswords))
            .perform(
                actionOnItemAtPosition<PasswordAdapter.ViewHolder>(
                    recyclerViewChildCountOld - 1, recyclerViewAction(R.id.iconDeletePassword)
                )
            )

        //get the new number of items in the recycler view
        val recyclerViewChildCountNew = getRecyclerViewItemCount()

        assert(recyclerViewChildCountNew < recyclerViewChildCountOld)
    }

    @Test
    fun updatePassword_passwordTitleNotEmpty() {
        //clear password title
        onView(withId(R.id.recyclerViewPasswords))
            .perform(
                actionOnItemAtPosition<PasswordAdapter.ViewHolder>(
                    0, recyclerViewEditableAction(R.id.editTextPasswordTitle)
                )
            )

        //try to update
        onView(withId(R.id.recyclerViewPasswords))
            .perform(
                actionOnItemAtPosition<PasswordAdapter.ViewHolder>(
                    0, recyclerViewAction(R.id.iconSavePassword)
                )
            )

        onView(withText(R.string.toast_empty_title))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))

        // !!! testing must sleep as long as the duration of the toast message
        // !!! when we test more than one toasts
        Thread.sleep(2000)
    }

    @Test
    fun updatePassword_passwordValueNotEmpty() {
        //clear password value
        onView(withId(R.id.recyclerViewPasswords))
            .perform(
                actionOnItemAtPosition<PasswordAdapter.ViewHolder>(
                    0, recyclerViewEditableAction(R.id.editTextPasswordValue)
                )
            )

        //try to update
        onView(withId(R.id.recyclerViewPasswords))
            .perform(
                actionOnItemAtPosition<PasswordAdapter.ViewHolder>(
                    0, recyclerViewAction(R.id.iconSavePassword)
                )
            )

        onView(withText(R.string.toast_empty_password))
            .inRoot(withDecorView(not(rootView)))
            .check(matches(isDisplayed()))

        // !!! testing must sleep as long as the duration of the toast message
        // !!! when we test more than one toasts
        Thread.sleep(2000)
    }

    @After
    fun tearDown() {
        //remove test value from database
        query.execSQL("DELETE FROM " + DatabaseManager.TABLE_NAME + " WHERE title='title'")
        dbHelper.close()
    }

    /**
     * Checks if an EditText with inputType of TYPE_TEXT_VARIATION_PASSWORD is visible or not.
     */
    private fun isPasswordVisible(): Matcher<View?> {
        return object : BoundedMatcher<View?, EditText>(EditText::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("we are in visible password mode")
            }

            override fun matchesSafely(editText: EditText): Boolean {
                return editText.transformationMethod !is PasswordTransformationMethod
            }
        }
    }

    /**
     * Defines a custom view action for accessing the RecyclerView clickable widgets.
     */
    private fun recyclerViewAction(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
                return null
            }

            override fun getDescription(): String {
                return "Show/Hide Password Action Description"
            }

            override fun perform(uiController: UiController?, view: View?) {
                val v = view?.findViewById<View>(id)
                v?.performClick()
            }
        }
    }

    //..
    private fun recyclerViewEditableAction(id: Int): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View>? {
//                return allOf(isDisplayed(), isAssignableFrom(EditText::class.java))
                //return allOf(isAssignableFrom(EditText::class.java))
                return null
            }

            override fun getDescription(): String {
                return "Clear Text Action Description"
            }

            override fun perform(uiController: UiController?, view: View?) {
                val v = view?.findViewById<View>(id)
                (v as EditText).setText("")
            }
        }
    }

    /**
     * Makes sure that the database is not empty so the widgets will be displayed.
     */
    private fun addAtLeastOnePasswordToDatabase() {
        //initialize database
        dbManager = DatabaseManager(InstrumentationRegistry.getInstrumentation().targetContext)
        dbHelper = DatabaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)

        //open database for writing
        query = dbHelper.writableDatabase

        //insert a test password model into the database (in case it is empty)
        val model = PasswordModel(-1, "title", TEST_VALUE)
        dbManager.insertPassword(model)
    }

    /**
     * Gets the number of items in the recycler view
     */
    private fun getRecyclerViewItemCount(): Int {
        var count = 0
        activityScenario.onActivity {
            val recyclerView = it.findViewById<RecyclerView>(R.id.recyclerViewPasswords)
            count = recyclerView.childCount
        }
        return count
    }
}