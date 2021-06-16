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
package gparap.apps.todo_list.utils

import android.content.Context
import gparap.apps.todo_list.data.ToDoDatabase
import gparap.apps.todo_list.data.ToDoModel
import gparap.apps.todo_list.data.ToDoRepository
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    /**
     * Helper that creates, initializes and returns a to-do model object.
     */
    fun getUpdatedToDo(
        id: Long, todoText: String, timeSet: String, dateSet: String
    ): ToDoModel {
        //update to-do model
        val todo = ToDoModel()
        todo.id = id
        todo.todo = todoText
        todo.deadlineTime = timeSet
        todo.deadlineDate = dateSet

        return todo
    }

    /**
     * Initializes the database using application environment.
     * Returns the repository.
     */
    fun getRepository(context: Context): ToDoRepository {
        val database = ToDoDatabase.getInstance(context)
        val dao = database.ToDoDao()
        return ToDoRepository(dao)
    }

    /**
     * Accepts a time string and a date string
     * and returns a formatted string considering user locale.
     * ie:  input = time -> "02:06",
     *              date -> "9/1/2021"
     *      ouput = "9/1/21 2:06 pm"
     */
    fun convertTimeAndDateAsString(time: String, date: String): String {
        val timedateString = "$time $date"
        val simpleDateFormat = SimpleDateFormat("hh:mm dd/MM/yyyy", Locale.getDefault())
        val dateParsed: Date = simpleDateFormat.parse(timedateString)!!
        return DateFormat.getInstance().format(dateParsed)
    }

    /**
     * Accepts an integer and fills in zeros in front, if it is less than 10.
     * Returns integer as string.
     * ie: 1 -> "01", 9 -> "09", etc.
     */
    fun fillInZeroInFront(number: Int): String {
        if (number < 10) {
            return "0$number"
        }
        return "$number"
    }
}