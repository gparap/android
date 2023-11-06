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
package gparap.apps.open_book_library.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context) : SQLiteOpenHelper(context, DATABASE, null, VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_DATABASE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_DATABASE_DATA)
        onCreate(db)
    }

    fun isDatabaseEmpty() : Boolean{
        val db = this.readableDatabase
        val cursor =  db.rawQuery("SELECT * FROM $TABLE_BOOKS", null)
        val isEmpty: Boolean = (cursor.count == 0)
        cursor.close()
        return isEmpty
    }

    fun insertBook(book: BookModel) : Boolean {
        val db = this.writableDatabase

        //create a set of values containing the book details
        val contentValues = ContentValues()
        contentValues.put("title", book.title)
        contentValues.put("author", book.author)
        contentValues.put("genre", book.genre)
        contentValues.put("date", book.date)
        contentValues.put("pages", book.pages)
        contentValues.put("lang", book.language)
        contentValues.put("country", book.country)
        contentValues.put("publisher", book.publisher)
        contentValues.put("cover_url", book.coverUrl)
        contentValues.put("cover_artist", book.coverArtist)
        contentValues.put("attr_file", book.attributionFile)
        contentValues.put("attr_text", book.attributionText)
        contentValues.put("is_asset", book.isAsset)
        contentValues.put("asset_name", book.assetName)
        contentValues.put("file_path", book.filePath)

        //insert values into the database
        val rowId = db.insert(TABLE_BOOKS, null, contentValues)

        //flag to check if values inserted correctly
        return rowId != -1L
    }

    companion object {
        private const val DATABASE = "OpenBookLibrary.db"
        private const val VERSION = 1
        private const val TABLE_BOOKS = "books"

        //SQL statement that creates the database
        private const val SQL_CREATE_DATABASE = "CREATE TABLE $TABLE_BOOKS (\n" +
                "  `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "  `title` text DEFAULT NULL,\n" +
                "  `author` text DEFAULT NULL,\n" +
                "  `genre` varchar(64) DEFAULT NULL,\n" +
                "  `date` int(4) DEFAULT NULL,\n" +
                "  `pages` int(4) DEFAULT NULL,\n" +
                "  `lang` varchar(64) DEFAULT NULL,\n" +
                "  `country` varchar(64) DEFAULT NULL,\n" +
                "  `publisher` varchar(256) DEFAULT NULL,\n" +
                "  `cover_url` varchar(256) DEFAULT NULL,\n" +
                "  `cover_artist` varchar(64) DEFAULT NULL,\n" +
                "  `attr_file` varchar(256) DEFAULT NULL,\n" +
                "  `attr_text` text DEFAULT NULL,\n" +
                "  `is_asset` tinyint(1) DEFAULT NULL,\n" +
                "  `asset_name` varchar(256) DEFAULT NULL,\n" +
                "  `file_path` varchar(256) DEFAULT NULL\n" +
                ");"

        //SQL statement that deletes all the database data
        private const val SQL_DELETE_DATABASE_DATA = "DROP TABLE IF EXISTS $TABLE_BOOKS;"
    }
}