package gparap.apps.todo_list.ui.add_todo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddToDoViewModel : ViewModel() {
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