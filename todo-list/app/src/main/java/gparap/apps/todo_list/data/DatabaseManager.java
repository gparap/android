package gparap.apps.todo_list.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

import gparap.apps.todo_list.model.ToDoModel;

/**
 * Created by gparap on 2020-10-16.
 */
public class DatabaseManager extends SQLiteOpenHelper {
    final static String DATABASE_NAME = "todoDB";
    final static int DATABASE_VERSION = 1;
    final static String DATABASE_TABLE = "ToDo";
    private SQLiteDatabase database;
    long rowId;         //helper for database selection
    boolean isDeleted;  //helper for database deletion

    public DatabaseManager(@Nullable Context context, @Nullable SQLiteDatabase.CursorFactory factory) {
        //create helper object to manage database
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //create table
        db.execSQL("CREATE TABLE " + DATABASE_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, done INTEGER, todo TEXT, time TEXT, date TEXT)");
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
     * Opens database for reading operations.
     */
    private void openReadDB(){
        database = this.getReadableDatabase();
    }

    /**
     * Opens database for writing operations.
     */
    private void openWriteDB(){
        database = this.getWritableDatabase();
    }

    /**
     * Inserts a new To_Do inside database (where "done" = false by default)
     *
     * @param todoOBJ object that holds the To_Do values
     * @return boolean for the result of insertion
     */
    public long insertTodo(ToDoModel todoOBJ) {
        rowId = -1;

        //values to insert to database
        final ContentValues contentValues = new ContentValues();
        contentValues.put("done", 0);
        contentValues.put("todo", todoOBJ.getTodo());
        contentValues.put("time", todoOBJ.getTime());
        contentValues.put("date", todoOBJ.getDate());

        //run database operations in a separate thread asynchronously
        final FutureTask<Long> task = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                //open database
                openWriteDB();

                //insert values to database
                database.beginTransaction();
                try {
                    rowId = database.insert(DATABASE_TABLE, null, contentValues);
                    database.setTransactionSuccessful();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } finally {
                    database.endTransaction();
                }
            }
        }, rowId);
        task.run();

        //return inserted row id
        return rowId;
    }

    /**
     * Updates the To_Do status (if it is done or note)
     *
     * @param id     to_do id inside database table
     * @param isDone if to_do is done or not
     */
    public void updateToDo(final long id, boolean isDone) {
        //values to update
        final ContentValues contentValues = new ContentValues();
        contentValues.put("done", isDone);

        //run database operations in a separate thread asynchronously
        FutureTask<Nullable> task = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                //open database
                openWriteDB();

                //update isDone status of To_Do
                database.beginTransaction();
                try {
                    database.update(DATABASE_TABLE, contentValues, "id=?", new String[]{String.valueOf(id)});
                    database.setTransactionSuccessful();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } finally {
                    database.endTransaction();
                }
            }
        }, null);
        task.run();
    }

    /**
     * Returns all todos from database.
     *
     * @return List<ToDoModel>
     */
    public List<ToDoModel> selectAllToDos() {
        final List<ToDoModel> todosList = new ArrayList<>();

        //run database operations in a separate thread asynchronously
        final FutureTask<List<ToDoModel>> task = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                //open database
                openReadDB();

                //select all todos from database
                Cursor cursor = database.query(DATABASE_TABLE, null, null, null, null, null, null);
                if (cursor != null) {
                    //loop through all to_do rows
                    while (cursor.moveToNext()) {
                        //create a new to_do
                        ToDoModel todo = new ToDoModel();
                        todo.setId(cursor.getInt(cursor.getColumnIndex("id")));
                        todo.setTodo(cursor.getString(cursor.getColumnIndex("todo")));
                        todo.setTime(cursor.getString(cursor.getColumnIndex("time")));
                        todo.setDate(cursor.getString(cursor.getColumnIndex("date")));
                        todo.setDone(cursor.getInt(cursor.getColumnIndex("done")));

                        //add to_do to list
                        todosList.add(todo);
                    }
                    //release resources
                    cursor.close();
                }
            }
        }, todosList);
        task.run();

        //return all todos
        return todosList;
    }

    /**
     * Deletes a To_Do from database.
     *
     * @param id row id
     */
    public boolean deleteToDo(final long id) {
        isDeleted = false;

        //run database operations in a separate thread asynchronously
        final FutureTask<Boolean> task = new FutureTask<>(new Runnable() {
            @Override
            public void run() {
                //open database
                openWriteDB();

                //delete row
                database.beginTransaction();
                try {
                    database.delete(DATABASE_TABLE, "id=?", new String[]{String.valueOf(id)});
                    database.setTransactionSuccessful();
                    isDeleted = true;
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } finally {
                    database.endTransaction();
                }
            }
        }, isDeleted);
        task.run();

        //confirm deletion
        return isDeleted;
    }
}
