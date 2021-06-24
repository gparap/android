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
import gparap.apps.todo_list.utils.Utils
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

    fun getToDoList(): List<ToDoModel> {
        return todosList
    }

    fun setTodosList(todosList: List<ToDoModel>) {
        this.todosList = todosList
        notifyDataSetChanged()
    }

    fun getToDoAtPosition(position: Int): ToDoModel {
        return todosList[position]
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

        //handle visibility of "deadline" timestamp and label
        // (based on if there is a date and/or time stamp)
        if (holder.textViewDeadline.text.isNotEmpty()){
            holder.textViewDeadline.visibility = View.VISIBLE
            holder.textViewDeadlineLabel.visibility = View.VISIBLE
        }else{
            holder.textViewDeadline.visibility = View.GONE
            holder.textViewDeadlineLabel.visibility = View.GONE
        }

        //helper for brevity
        var isDone = holder.checkBoxDone.isChecked

        strikeThroughToDoTextIfDone(holder, isDone)

        //update database with the To_Do's "isDone" checkbox value
        val database = getInstance(context!!)
        val dao = database.ToDoDao()
        val repository = ToDoRepository(dao)
        holder.checkBoxDone.setOnClickListener {
            try {
                //update database
                GlobalScope.launch(Dispatchers.IO) {
                    isDone = todosList[position].isDone
                    repository.editToDo(todosList[position].id, !isDone)
                }
                strikeThroughToDoTextIfDone(holder, !isDone)

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

    private fun strikeThroughToDoTextIfDone(holder: ToDoViewHolder, isDone: Boolean) {
        Utils.strikeText(holder.textViewToDo, isDone)
        Utils.strikeText(holder.textViewDeadline, isDone)
        Utils.strikeText(holder.textViewDeadlineLabel, isDone)
    }

    /**
     * Describes the view (and its widgets) inside the RecyclerView.
     */
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //get view widgets
        var textViewToDo: TextView = itemView.findViewById(R.id.textViewTodo)
        var textViewDeadline: TextView = itemView.findViewById(R.id.textViewDeadline)
        var textViewDeadlineLabel: TextView = itemView.findViewById(R.id.textViewDeadlineLabel)
        var checkBoxDone: CheckBox = itemView.findViewById(R.id.checkBoxDone)
    }
}