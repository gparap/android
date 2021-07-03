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

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.todo_list.R
import gparap.apps.todo_list.adapter.ToDoAdapter
import gparap.apps.todo_list.data.ToDoModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Callback for swiping (left or right) to delete a to-do.
 */
class SwipeHelperCallback(
    dragDirs: Int, swipeDirs: Int, private var adapter: ToDoAdapter, var context: Context
) : ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val todo: ToDoModel = adapter.getToDoAtPosition(viewHolder.bindingAdapterPosition)

        //create delete confirmation dialog
        val builder = AlertDialog.Builder(context, R.style.AlertDialogTheme)
            .setTitle(todo.todo)
            .setMessage(R.string.dialog_delete_todo)
            .setPositiveButton(R.string.delete) { _, _ ->
                GlobalScope.launch(Dispatchers.IO) {
                    Utils.getRepository(context).deleteToDo(todo)
                }
                Toast.makeText(context, R.string.toast_todo_deleted, Toast.LENGTH_SHORT).show()
                adapter.notifyItemChanged(viewHolder.bindingAdapterPosition)
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                adapter.notifyItemChanged(viewHolder.bindingAdapterPosition)

                //close dialog
                dialog.dismiss()
            }

        //display dialog
        val dialog = builder.create()
        dialog.show()
    }
}