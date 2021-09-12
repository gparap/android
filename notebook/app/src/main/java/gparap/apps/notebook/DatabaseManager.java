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
package gparap.apps.notebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

@SuppressWarnings("Convert2Lambda")
public class DatabaseManager extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "notebookDB";
    private final static String DATABASE_TABLE = "notebook";
    private final static Integer DATABASE_VERSION = 1;
    private SQLiteDatabase database;
    long rowId; //helper for database operations

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create database table
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, details TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public synchronized void close() {
        //close open database
        super.close();
    }

    /**
     * Inserts a new Note into database.
     *
     * @param note note id
     * @return boolean insertion ok or not
     */
    public boolean insert(Note note) {
        rowId = -1;

        //init values from note object
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("details", note.getDetails());

        FutureTask<Long> task = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                //open database
                database = getWritableDatabase();

                try {
                    database.beginTransaction();
                    database.setTransactionSuccessful();

                    //insert new note
                    rowId = database.insert(DATABASE_TABLE, null, contentValues);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } finally {
                    database.endTransaction();
                }
            }
        }, rowId);
        task.run();

        //return ok or not
        return rowId != -1;
    }

    public List<Note> selectAllNotes() {
        List<Note> notes = new ArrayList<>();

        FutureTask<List<Note>> task = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                //open database
                database = getReadableDatabase();

                //select all notes from database
                Cursor cursor = database.query(DATABASE_TABLE, null, null, null, null, null, null);
                if (cursor != null) {
                    //loop through all rows
                    while (cursor.moveToNext()) {
                        try {
                            //create a new note for each row in database
                            Note note = new Note();
                            note.setId(cursor.getLong(cursor.getColumnIndex("id")));
                            note.setTitle(cursor.getString(cursor.getColumnIndex("title")));
                            note.setDetails(cursor.getString(cursor.getColumnIndex("details")));

                            //add to list
                            notes.add(note);
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                    }
                    //free resources
                    cursor.close();
                }
            }
        }, notes);
        task.run();

        return notes;
    }

    public boolean update(Note note) {
        rowId = -1;

        //init values from note object
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", note.getTitle());
        contentValues.put("details", note.getDetails());

        FutureTask<Nullable> task = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                //open database
                database = getWritableDatabase();

                try {
                    database.beginTransaction();
                    database.setTransactionSuccessful();

                    //update note
                    rowId = database.update(DATABASE_TABLE, contentValues, "id=?", new String[]{String.valueOf(note.getId())});
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } finally {
                    database.endTransaction();
                }
            }
        }, null);
        task.run();

        //updated ok or not
        return rowId != -1;
    }

    public boolean delete(long id) {
        rowId = -1;

        //delete note from database
        FutureTask<Long> task = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                //open database
                database = getWritableDatabase();

                try {
                    //delete from database
                    database.beginTransaction();
                    rowId = database.delete(DATABASE_TABLE, "id=?", new String[]{String.valueOf(id)});
                    database.setTransactionSuccessful();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } finally {
                    database.endTransaction();
                }
            }
        }, rowId);
        task.run();

        //deletion was ok or not
        return rowId != 0 || rowId != -1;
    }
}
