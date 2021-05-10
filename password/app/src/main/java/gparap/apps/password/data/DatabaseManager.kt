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
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class DatabaseManager(context: Context) {
    private val dbHelper = DatabaseHelper(context)
    private var db: SQLiteDatabase? = null

    /**
     * Inserts a password into the database.
     */
    fun insertPassword(model: PasswordModel): Boolean {
        db = dbHelper.writableDatabase

        //prepare value to insert
        val contentValues = ContentValues()
        contentValues.put("title", model.title)
        contentValues.put("value", model.value)

        //insert value into the database
        val newRowId = db?.insert(TABLE_NAME, null, contentValues)
        dbHelper.close()

        //if there is a new id then the values where inserted correctly
        return newRowId != -1L
    }

    /**
     * Fetches all passwords from database.
     */
    fun fetchPasswords(): ArrayList<PasswordModel> {
        db = dbHelper.readableDatabase

        //fetch all passwords
        val passwords = ArrayList<PasswordModel>()
        val cursor: Cursor? = db?.rawQuery("SELECT * FROM $TABLE_NAME", null)
        while (cursor?.moveToNext()!!) {
            //add record to list of passwords
            val id = cursor.getInt(cursor.getColumnIndex("id"))
            val title = cursor.getString(cursor.getColumnIndex("title"))
            val value = cursor.getString(cursor.getColumnIndex("value"))
            passwords.add(PasswordModel(id, title, value))
        }
        //close everything
        cursor.close()
        dbHelper.close()

        return passwords
    }

    companion object {
        const val TABLE_NAME = "Password"
    }
}