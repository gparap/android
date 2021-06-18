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
import android.graphics.Paint
import android.text.TextUtils
import android.widget.TextView
import gparap.apps.todo_list.data.ToDoDatabase
import gparap.apps.todo_list.data.ToDoModel
import gparap.apps.todo_list.data.ToDoRepository
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    /**
     * Strikes and unstrikes through the text of a TextView.
     */
    fun strikeText(view: TextView, isToBeStrikedThrough: Boolean) {
        if (isToBeStrikedThrough) {
            view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }else {
            view.paintFlags = 0
        }
    }

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
     * Accepts a date string and returns a formatted string considering user locale.
     * ie:  input = date -> "07/06/2021"
     *      ouput = "6/7/21"     for Locale.US
     *      ouput = "07/06/2021" for Locale.UK
     *      ouput = "2021/6/7"   for Locale.CHINA
     */
    fun convertDateAsStringLocale(date: String): String {
        val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateParsed: Date = simpleDateFormat.parse(date)!!
        return DateFormat.getDateInstance(DateFormat.DATE_FIELD, Locale.getDefault()).format(dateParsed)
    }

    /**
     * Accepts a time string and returns a formatted string considering user locale.
     * ie:  input = time -> "14:06"
     *      ouput = "2:06 PM" (US locale)
     *      ouput = "2:06 pm" (UK locale)
     */
    fun convertTimeAsStringLocale(time: String): String {
        val simpleDateFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())

        //split time to hours and minutes
        val splitter = TextUtils.split(time, ":")
        val hour = splitter[0]
        val minute = splitter[1]

        //set helper calender with time
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour.toInt())
        calendar.set(Calendar.MINUTE, minute.toInt())

        //format time considering locale
        var timeFormatted = simpleDateFormat.format(calendar.time)

        //remove first "0" if exists (ie 01:50 AM -> 1:50 AM)
        if (timeFormatted[0] == '0')
            timeFormatted = timeFormatted.substring(1)

        return timeFormatted
    }

    /**
     * Accepts a time string and a date string and returns a formatted string considering user locale.
     * ie:  input = time -> "14:06",
     *              date -> "9/1/2021"
     *      ouput = "9/1/21 2:06 pm"
     *
     *  If the time is missing set now as the current time.
     *  If the date is missing set today as the current date.
     */
    fun convertTimeAndDateAsString(time: String, date: String): String {
        //convert timestamp as formatted string
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