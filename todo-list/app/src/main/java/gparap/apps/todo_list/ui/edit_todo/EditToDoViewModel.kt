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
package gparap.apps.todo_list.ui.edit_todo

import android.app.Application
import androidx.lifecycle.*
import gparap.apps.todo_list.data.ToDoDatabase
import gparap.apps.todo_list.data.ToDoModel
import gparap.apps.todo_list.data.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ToDoRepository

    init {
        //init data access object
        val dao = ToDoDatabase.getInstance(application.applicationContext).ToDoDao()

        //init repository
        repository = ToDoRepository(dao)
    }

    fun saveToDo(todo: ToDoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.editToDo(todo)
        }
    }

    //store and manage time set for a to-do
    private var todoTime: MutableLiveData<String> = MutableLiveData()
    fun getToDoTime(): LiveData<String> {
        return todoTime
    }

    fun setToDoTime(time: String) {
        todoTime.value = time
    }

    //store and manage date set for a to-do
    private val todoDate: MutableLiveData<String> = MutableLiveData()
    fun getToDoDate(): LiveData<String> {
        return todoDate
    }

    fun setToDoDate(date: String) {
        todoDate.value = date
    }
}