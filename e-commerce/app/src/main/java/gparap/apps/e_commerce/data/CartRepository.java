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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import gparap.apps.e_commerce.utils.AppConstants;

public class CartRepository implements CartDao {
    private final CartItemsDbHelper dbHelper;
    private final SQLiteDatabase cartDB;

    public CartRepository(Context context) {
        //create a database helper to access the carts database
        dbHelper = new CartItemsDbHelper(context);

        //open the cart database for writing
        cartDB = dbHelper.getWritableDatabase();
    }

    @Override
    public long addItemToCart(CartItemModel cartItem) {
        //create a set of values that describe the selected cart item
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstants.COLUMN_NAME_ITEM_ID, cartItem.getId());
        contentValues.put(AppConstants.COLUMN_NAME_ITEM_CATEGORY_ID, cartItem.getCategoryId());
        contentValues.put(AppConstants.COLUMN_NAME_ITEM_NAME, cartItem.getName());
        contentValues.put(AppConstants.COLUMN_NAME_ITEM_COST, cartItem.getPrice());
        contentValues.put(AppConstants.COLUMN_NAME_ITEM_IMAGE_URL, cartItem.getImageUrl());
        contentValues.put(AppConstants.COLUMN_NAME_ITEM_QUANTITY, 1);

        //insert the selected cart item as a new row in the database and get
        //noinspection UnnecessaryLocalVariable
        long rowId = cartDB.insert(AppConstants.TABLE_NAME_CART_ITEMS, null, contentValues);

        //get the row ID of the newly inserted row, or -1 if an error occurred
        return rowId;
    }

    @Override
    public int getCartItemsCount() {
        int count;
        Cursor cursor = cartDB.rawQuery("SELECT * FROM cart_items", null);
        count = cursor.getCount();
        cursor.close();
        return count;
    }

    @Override
    public ArrayList<CartItemModel> getCartItems() {
        ArrayList<CartItemModel> cartItems = new ArrayList<>();

        //select all the cart items
        Cursor cursor = cartDB.rawQuery("SELECT * FROM cart_items", null);
        while (cursor.moveToNext()) {
            //create the cart item
            CartItemModel cartItem = new CartItemModel(
                    cursor.getInt(cursor.getColumnIndexOrThrow(AppConstants.COLUMN_NAME_ITEM_ID)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(AppConstants.COLUMN_NAME_ITEM_CATEGORY_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(AppConstants.COLUMN_NAME_ITEM_NAME)),
                    cursor.getFloat(cursor.getColumnIndexOrThrow(AppConstants.COLUMN_NAME_ITEM_COST)),
                    cursor.getString(cursor.getColumnIndexOrThrow(AppConstants.COLUMN_NAME_ITEM_IMAGE_URL)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(AppConstants.COLUMN_NAME_ITEM_QUANTITY))
            );
            //add cart item to list
            cartItems.add(cartItem);
        }
        cursor.close();

        //return cart items
        return cartItems;
    }

    @Override
    public void removeCartItem(int id) {
        cartDB.delete(AppConstants.TABLE_NAME_CART_ITEMS, "id=" + id, null);
    }

    @Override
    public void updateCartItemQuantity(int id, int quantity) {
        //construct the database query
        String table = AppConstants.TABLE_NAME_CART_ITEMS;
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstants.COLUMN_NAME_ITEM_QUANTITY, quantity);
        String whereClause = "id=" + id;

        //update database
        cartDB.update(table, contentValues, whereClause, null);
    }

    @Override
    public void emptyCart() {
        cartDB.delete(AppConstants.TABLE_NAME_CART_ITEMS, null, null);
    }

    /**
     * Closes any open database object.
     */
    public void closeDatabase() {
        dbHelper.close();
    }
}
