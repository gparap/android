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
package gparap.apps.todo_list.ui.todo_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import gparap.apps.todo_list.data.ToDoDatabase
import gparap.apps.todo_list.data.ToDoModel
import gparap.apps.todo_list.data.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ToDoRepository

    init {
        //init data access object
        val dao = ToDoDatabase.getInstance(application).ToDoDao()

        //init repository
        repository = ToDoRepository(dao)
    }

    fun getToDoList() : LiveData<List<ToDoModel>> {
        return repository.getToDoList
    }

    fun deleteToDoList(toDoList: List<ToDoModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteToDoList(toDoList)
        }
    }
}