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

import android.content.ContentValues
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
        val queryInsert = dbHelper.writableDatabase

        //prepare value to insert to database
        val contentValues = ContentValues()
        val model = PasswordModel(-1, "title", "value")
        contentValues.put("title", model.title)
        contentValues.put("value", model.value)

        //insert value into database
        val newRowId = queryInsert.insert(DatabaseManager.TABLE_NAME, null, contentValues)
        dbHelper.close()

        //test if there is a new id
        assert(newRowId != -1L)
    }
}