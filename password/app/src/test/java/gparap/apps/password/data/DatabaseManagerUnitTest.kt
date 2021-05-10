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
package gparap.apps.password.data

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class DatabaseManagerUnitTest {
    private lateinit var dbManager: DatabaseManager
    private lateinit var dbHelper: DatabaseHelper

    @Before
    fun setUp() {
        //initialize database
        dbManager = DatabaseManager(InstrumentationRegistry.getInstrumentation().targetContext)
        dbHelper = DatabaseHelper(InstrumentationRegistry.getInstrumentation().targetContext)
    }

    @Test
    fun insertPassword() {
        //open database for writing
        val query = dbHelper.writableDatabase

        //insert a test password model value into the database
        val model = PasswordModel(-1, "title", "value")
        val isModelInserted = dbManager.insertPassword(model)

        //test
        assert(isModelInserted)

        //remove test value from database
        query.rawQuery("DELETE FROM " + DatabaseManager.TABLE_NAME + " WHERE title='title'", null)
        dbHelper.close()
    }

    @Test
    fun fetchPasswords() {
        //open database for writing
        val query = dbHelper.writableDatabase

        //insert a few test password model values into the database
        var testPasswordsCount = 0
        var model = PasswordModel(-1, "title1", "value1")
        dbManager.insertPassword(model)
        testPasswordsCount++
        model = PasswordModel(-1, "title2", "value2")
        dbManager.insertPassword(model)
        testPasswordsCount++

        //fetch all passwords from database
        //!!! test only the fetched records length (assert that insertion in database is working ok)
        val passwords = ArrayList<PasswordModel>().apply {
            addAll(dbManager.fetchPasswords())
        }

        //test if there is at least two passwords in the database
        assert(passwords.size >= testPasswordsCount)

        //remove test values from database
        query.rawQuery("DELETE FROM " + DatabaseManager.TABLE_NAME + " WHERE title='title1'", null)
        query.rawQuery("DELETE FROM " + DatabaseManager.TABLE_NAME + " WHERE title='title2'", null)
        dbHelper.close()
    }
}