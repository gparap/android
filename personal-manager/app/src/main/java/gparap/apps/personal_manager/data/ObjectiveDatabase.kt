/*
 * Copyright 2024 gparap
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
package gparap.apps.personal_manager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/** Database schema containing the objectives. */
@Database(entities = [ObjectiveModel::class], version = 1, exportSchema = false)
abstract class ObjectiveDatabase : RoomDatabase() {

    abstract fun objectiveDao(): ObjectiveDao

    companion object {

        //database Singleton
        @Volatile
        private var INSTANCE: ObjectiveDatabase? = null

        //get the database instance
        fun getInstance(context: Context): ObjectiveDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        //create the database instance
        private fun buildDatabase(context: Context): ObjectiveDatabase {
            return Room.databaseBuilder(
                context,
                ObjectiveDatabase::class.java,
                "objectives_database.db"
            ).build()
        }
    }
}