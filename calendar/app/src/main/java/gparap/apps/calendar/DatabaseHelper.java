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
package gparap.apps.calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    private final String TABLE_NAME = "Events";
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(event_date TEXT, event_name TEXT, event_details TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * Inserts a row into the database.
     *
     * @param contentValues calendar event values
     */
    public void dbInsert(ContentValues contentValues, String id) {
        sqLiteDatabase = this.getWritableDatabase();

        //delete previous value
        String eventDate = contentValues.getAsString("event_date");
        dbDelete(id, eventDate);

        //insert new value
        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    /**
     * Selects a row from the database.
     *
     * @param value selection value
     * @return event name and details
     */
    public HashMap<String, String> dbSelect(String value) {
        sqLiteDatabase = this.getReadableDatabase();
        HashMap<String, String> hashMap = new HashMap<>();

        //columns needed after query
        String[] columnsNeeded = {"event_name", "event_details"};

        //selection filter and arguments
        String selection = "event_date = ?";
        String[] selectionArgs = {value};

        //query database and get event values
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, columnsNeeded, selection, selectionArgs, null, null, null);
        if (cursor.getCount() > 0) {
            //we only have one row
            cursor.moveToFirst();

            //add values
            hashMap.put("event_name", cursor.getString(cursor.getColumnIndex("event_name")));
            hashMap.put("event_details", cursor.getString(cursor.getColumnIndex("event_details")));
        }
        cursor.close();

        //return values
        return hashMap;
    }

    /**
     * Deletes a row from the database.
     *
     * @param key   column name to select
     * @param value select filter value
     */
    public void dbDelete(String key, String value) {
        sqLiteDatabase = this.getWritableDatabase();

        //delete row
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + key + "='" + value + "'");
    }

    @Override
    public synchronized void close() {
        super.close();
    }
}
