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
package gparap.apps.shopping_list.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CategoryModel::class, ItemModel::class], version = 1, exportSchema = false)
abstract class ShoppingListDatabase : RoomDatabase() {
    abstract fun getCategoryDao(): CategoryDao
    abstract fun getItemDao(): ItemDao

    companion object {
        private var instance: ShoppingListDatabase? = null

        fun getInstance(context: Context): ShoppingListDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    ShoppingListDatabase::class.java,
                    "shopping_list_db"
                ).build()
            }
            return instance
        }
    }
}