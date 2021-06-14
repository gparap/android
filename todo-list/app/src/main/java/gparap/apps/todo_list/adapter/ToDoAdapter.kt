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
package gparap.apps.todo_list.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.todo_list.R
import gparap.apps.todo_list.adapter.ToDoAdapter.ToDoViewHolder
import gparap.apps.todo_list.data.ToDoDatabase.Companion.getInstance
import gparap.apps.todo_list.data.ToDoModel
import gparap.apps.todo_list.data.ToDoRepository
import gparap.apps.todo_list.ui.todo_list.ToDoListFragmentDirections
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*

class ToDoAdapter : RecyclerView.Adapter<ToDoViewHolder>() {
    private var todosList: List<ToDoModel>
    private var context: Context? = null

    init {
        todosList = ArrayList()
    }

    fun setTodosList(todosList: List<ToDoModel>) {
        this.todosList = todosList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        context = parent.context

        //create new to-do list view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.todo_view, parent, false)
        return ToDoViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        //bind ViewHolder contents with list elements
        holder.textViewToDo.text = todosList[position].todo
        holder.textViewDeadline.text = todosList[position].deadlineTimeStamp
        holder.checkBoxDone.isChecked = todosList[position].isDone

        //update database with the To_Do's "isDone" checkbox value
        val database = getInstance(context!!)
        val dao = database.ToDoDao()
        val repository = ToDoRepository(dao)
        holder.checkBoxDone.setOnClickListener {
            try {
                //update database
                GlobalScope.launch(Dispatchers.IO) {
                    val isChecked = todosList[position].isDone
                    repository.editToDo(todosList[position].id, !isChecked)
                }
            } catch (e: IndexOutOfBoundsException) {
                e.printStackTrace()
            }
        }

        //navigate to edit fragment and pass existing to-do
        holder.itemView.setOnClickListener {
            val navAction = ToDoListFragmentDirections.actionToDoListFragmentToEditToDoFragment(
                todosList[position]
            )
            Navigation.findNavController(it).navigate(navAction)
        }
    }

    override fun getItemCount(): Int {
        //get number of todos
        return todosList.size
    }

    /**
     * Describes the view (and its widgets) inside the RecyclerView.
     */
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //get view widgets
        var textViewToDo: TextView = itemView.findViewById(R.id.textViewTodo)
        var textViewDeadline: TextView = itemView.findViewById(R.id.textViewDeadline)
        var checkBoxDone: CheckBox = itemView.findViewById(R.id.checkBoxDone)
    }
}