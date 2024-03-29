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

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

/** Data access object for manipulating objectives. */
@Dao
interface ObjectiveDao {

    /** Inserts a new objective into the database. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertObjective(objectiveModel: ObjectiveModel)

    /** Selects all objectives from the database. */
    @Query("SELECT * FROM objectives")
    fun getObjectives(): LiveData<List<ObjectiveModel>>

    /** Updates an existing objective in the database. */
    @Update
    suspend fun updateObjective(objectiveModel: ObjectiveModel)

    /** Deletes an objective from the database. */
    @Delete
    suspend fun deleteObjective(objectiveModel: ObjectiveModel)

    /** Deletes all objectives from the database. */
    @Query("DELETE FROM objectives")
    suspend fun deleteAllObjectives()
}