/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.quiz.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import gparap.apps.quiz.R
import gparap.apps.quiz.utils.AppConstants

class QuizDatabase(
    private val context: Context?,
    factory: SQLiteDatabase.CursorFactory?,
) : SQLiteOpenHelper(context, AppConstants.DATABASE_NAME, factory, AppConstants.DATABASE_VERSION) {

    private var db: SQLiteDatabase? = null

    override fun onCreate(db: SQLiteDatabase?) {
        this.db = db

        //create database tables
        val categories = context?.resources?.getStringArray(R.array.string_array_categories)
        for (category in categories!!) {
            if (!category.equals("categories")) {
                createTable(category)
            }
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    private fun createTable(tableName: String) {
        db?.execSQL("CREATE TABLE "
            .plus(tableName)
            .plus(" (id INTEGER PRIMARY KEY AUTOINCREMENT, ")
            .plus(" difficulty TEXT, ")
            .plus(" question TEXT, ")
            .plus(" answer TEXT, ")
            .plus(" choices TEXT);")
        )
    }
}