package gparap.apps.todo_list.ui.add_todo

import android.app.Application
import androidx.lifecycle.*
import gparap.apps.todo_list.data.ToDoDatabase
import gparap.apps.todo_list.data.ToDoModel
import gparap.apps.todo_list.data.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddToDoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ToDoRepository

    init {
        //init data access object
        val dao = ToDoDatabase.getInstance(application.applicationContext).ToDoDao()

        //init repository
        repository = ToDoRepository(dao)
    }

    //add new to-do
    fun addToDo(todo: ToDoModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addToDo(todo)
        }
    }

    //get to-do list
    fun getToDoList(): LiveData<List<ToDoModel>> {
        return repository.getToDoList
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