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
package gparap.apps.open_book_library.utils

import android.content.ContentValues
import gparap.apps.open_book_library.data.AppDatabase

object Utils {
    /** Initialize the database table of featured books with the default data. */
    fun initFeaturedBooks(database: AppDatabase) {
        val db = database.writableDatabase

        //put the 1st book
        var contentValues = ContentValues()
        contentValues.put("title", "Autobiography of a Yogi")
        contentValues.put("author", "Paramahansa Yogananda")
        contentValues.put("genre", "Autobiography")
        contentValues.put("date", 1946)
        contentValues.put("pages", 758)
        contentValues.put("lang", "Hindi, English")
        contentValues.put("country", "India and the United States")
        contentValues.put("publisher", "Philosophical Library")
        contentValues.put("cover_url", "Autobiography-of-a-Yogi.jpg")
        contentValues.put("cover_artist", "")
        contentValues.put("attr_file", "")
        contentValues.put("attr_text", "")
        contentValues.put("is_asset", 1)
        contentValues.put("asset_name", "Autobiography_of_a_Yogi.pdf")
        contentValues.put("file_path", "")
        db.insert("books", null, contentValues)

        //put the 2nd book
        contentValues = ContentValues()
        contentValues.put("title", "Oliver Twist (1838) Volume 1")
        contentValues.put("author", "Charles Dickens")
        contentValues.put("genre", "novel")
        contentValues.put("date", 1838)
        contentValues.put("pages", 284)
        contentValues.put("lang", "English")
        contentValues.put("country", "England")
        contentValues.put("publisher", "Bentley's Miscellany")
        contentValues.put("cover_url", "Olivertwist_front.jpg")
        contentValues.put("cover_artist", "George Cruikshank")
        contentValues.put("attr_file", "")
        contentValues.put("attr_text", "")
        contentValues.put("is_asset", 1)
        contentValues.put("asset_name", "Oliver_Twist_(1838)_Volume_1.pdf")
        contentValues.put("file_path", "")
        db.insert("books", null, contentValues)

        //put the 3rd book
        contentValues = ContentValues()
        contentValues.put("title", "Oliver Twist (1838) Volume 2")
        contentValues.put("author", "Charles Dickens")
        contentValues.put("genre", "novel")
        contentValues.put("date", 1838)
        contentValues.put("pages", 257)
        contentValues.put("lang", "English")
        contentValues.put("country", "England")
        contentValues.put("publisher", "Bentley's Miscellany")
        contentValues.put("cover_url", "Olivertwist_front.jpg")
        contentValues.put("cover_artist", "George Cruikshank")
        contentValues.put("attr_file", "")
        contentValues.put("attr_text", "")
        contentValues.put("is_asset", 1)
        contentValues.put("asset_name", "Oliver_Twist_(1838)_Volume_2.pdf")
        contentValues.put("file_path", "")
        db.insert("books", null, contentValues)

        //put the 4th book
        contentValues = ContentValues()
        contentValues.put("title", "Oliver Twist (1838) Volume 3")
        contentValues.put("author", "Charles Dickens")
        contentValues.put("genre", "novel")
        contentValues.put("date", 1838)
        contentValues.put("pages", 272)
        contentValues.put("lang", "English")
        contentValues.put("country", "England")
        contentValues.put("publisher", "Bentley's Miscellany")
        contentValues.put("cover_url", "Olivertwist_front.jpg")
        contentValues.put("cover_artist", "George Cruikshank")
        contentValues.put("attr_file", "")
        contentValues.put("attr_text", "")
        contentValues.put("is_asset", 1)
        contentValues.put("asset_name", "Oliver_Twist_(1838)_Volume_3.pdf")
        contentValues.put("file_path", "")
        db.insert("books", null, contentValues)

        //put the 5th book
        contentValues = ContentValues()
        contentValues.put("title", "Pip")
        contentValues.put("author", "Ian Hay")
        contentValues.put("genre", "")
        contentValues.put("date", 1907)
        contentValues.put("pages", 373)
        contentValues.put("lang", "")
        contentValues.put("country", "United Kingdom")
        contentValues.put("publisher", "")
        contentValues.put("cover_url", "Pip_(novel).jpg")
        contentValues.put("cover_artist", "")
        contentValues.put("attr_file", "")
        contentValues.put("attr_text", "")
        contentValues.put("is_asset", 1)
        contentValues.put("asset_name", "")
        contentValues.put("asset_name", "Pip.pdf")
        contentValues.put("file_path", "")
        db.insert("books", null, contentValues)
    }
}