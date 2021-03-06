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
package gparap.apps.todo_list.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ToDoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToDo(todo: ToDoModel)

    @Query("SELECT * FROM todos")
    fun getToDoList(): LiveData<List<ToDoModel>>

    @Query("UPDATE todos SET done = :isDone WHERE id = :id")
    suspend fun updateToDo(id: Long, isDone: Boolean)

    @Update
    suspend fun updateToDo(todo: ToDoModel)

    @Delete
    suspend fun deleteToDo(todo: ToDoModel)

    @Delete
    suspend fun deleteToDoList(todos: List<ToDoModel>)
}