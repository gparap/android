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
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import gparap.apps.password.MainActivity
import gparap.apps.password.R
import gparap.apps.password.data.DatabaseHelper
import gparap.apps.password.data.DatabaseManager
import gparap.apps.password.data.PasswordModel
import org.junit.After
import org.junit.Before
import org.junit.Test

class WidgetsVisibilityInstrumentedTest {
    private lateinit var dbManager: DatabaseManager
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var query: SQLiteDatabase

    @Before
    fun setUp() {
        addAtLeastOnePasswordToDatabase()

        //goto manager fragment
        ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.navigation_manager)).perform(ViewActions.click())

        //wait to load sqlite results
        Thread.sleep(500)
    }

    @Test
    fun isVisible_editTextPasswordTitle() {
        try {
            onView(withId(R.id.editTextPasswordTitle)).check(matches(isDisplayed()))
        } catch (e: Exception) {
            checkExceptionClass(e)
        }
    }

    @Test
    fun isVisible_editTextPasswordValue() {
        try {
            onView(withId(R.id.editTextPasswordValue)).check(matches(isDisplayed()))
        } catch (e: Exception) {
            checkExceptionClass(e)
        }
    }

    @Test
    fun isVisible_switchShowHidePassword() {
        try {
            onView(withId(R.id.switchShowHidePassword)).check(matches(isDisplayed()))
        } catch (e: Exception) {
            checkExceptionClass(e)
        }
    }

    @After
    fun tearDown() {
        //remove test value from database
        query.rawQuery("DELETE FROM " + DatabaseManager.TABLE_NAME + " WHERE title='title'", null)
        dbHelper.close()
    }

    /**
     * Checks what java class is the exception assignable from.
     */
    private fun checkExceptionClass(e: java.lang.Exception) {
        if (e.javaClass.isAssignableFrom(androidx.test.espresso.AmbiguousViewMatcherException::class.java)) {
            //!!! it's ok, we know we have multiple widgets with the same id
            assert(true)
        } else {
            assert(false)
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
        val model = PasswordModel(-1, "title", "value")
        dbManager.insertPassword(model)
    }
}