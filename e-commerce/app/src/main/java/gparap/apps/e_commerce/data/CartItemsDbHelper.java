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
package gparap.apps.e_commerce.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import gparap.apps.e_commerce.utils.AppConstants;

public class CartItemsDbHelper extends SQLiteOpenHelper {
    //statement to create database table for cart items
    private static final String SQL_CREATE_CART_ITEM_ENTRIES =
            "CREATE TABLE " + AppConstants.TABLE_NAME_CART_ITEMS + " (" +
            AppConstants.COLUMN_NAME_ITEM_ID + " INTEGER PRIMARY KEY," +
            AppConstants.COLUMN_NAME_ITEM_CATEGORY_ID + " INTEGER," +
            AppConstants.COLUMN_NAME_ITEM_NAME + " TEXT," +
            AppConstants.COLUMN_NAME_ITEM_COST + " DECIMAL(8,2)," +
            AppConstants.COLUMN_NAME_ITEM_DISCOUNT + " INTEGER," +
            AppConstants.COLUMN_NAME_ITEM_IMAGE_URL + " TEXT," +
            AppConstants.COLUMN_NAME_ITEM_QUANTITY + " INTEGER)";

    //statement to delete database table for cart items
    private static final String SQL_DELETE_CART_ITEM_ENTRIES =
            "DROP TABLE IF EXISTS " + AppConstants.TABLE_NAME_CART_ITEMS;

    public CartItemsDbHelper(@Nullable Context context) {
        super(context, AppConstants.DATABASE_CART_NAME, null, AppConstants.DATABASE_CART_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CART_ITEM_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_CART_ITEM_ENTRIES);
        onCreate(db);
    }
}
