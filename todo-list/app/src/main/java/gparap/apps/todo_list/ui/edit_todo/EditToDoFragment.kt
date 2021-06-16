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

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.todo_list.R
import gparap.apps.todo_list.data.ToDoModel
import gparap.apps.todo_list.ui.pickers.DatePickerFragment
import gparap.apps.todo_list.ui.pickers.TimePickerFragment
import gparap.apps.todo_list.utils.Utils

class EditToDoFragment : Fragment() {
    private lateinit var viewModel: EditToDoViewModel
    private val fragmentArgs by navArgs<EditToDoFragmentArgs>()
    private lateinit var todoText: EditText
    private lateinit var timeSet: TextView
    private lateinit var dateSet: TextView
    private lateinit var toolbar: MaterialToolbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        setupActionBarOptionsMenu(view)
        viewModel = ViewModelProvider(this).get(EditToDoViewModel::class.java)

        //get widgets
        todoText = view.findViewById(R.id.editTextToDoUpdating)
        timeSet = view.findViewById(R.id.textViewToDoTimeSetUpdating)
        dateSet = view.findViewById(R.id.textViewToDoDateSetUpdating)

        //populate widgets from fragment arguments
        todoText.setText(fragmentArgs.existingToDo.todo)
        timeSet.text = fragmentArgs.existingToDo.deadlineTime
        dateSet.text = fragmentArgs.existingToDo.deadlineDate

        //display dialogs to set the time and date for to-do
        setTimeForToDo(view)
        setDateForToDo(view)

        //observe to-do time set
        viewModel.getToDoTime().observe(viewLifecycleOwner, {
            timeSet.text = it
        })

        //observe to-do date set
        viewModel.getToDoDate().observe(viewLifecycleOwner, {
            dateSet.text = it
        })

        //add a to-do to the database
        val buttonSave = view.findViewById<FloatingActionButton>(R.id.fabUpdateToDo)
        buttonSave.setOnClickListener {
            saveToDo(view)
        }
    }

    private fun saveToDo(view: View) {
        //validate to-do text
        if (TextUtils.isEmpty(todoText.text)) {
            Toast.makeText(requireContext(), R.string.toast_todo_empty, Toast.LENGTH_SHORT).show()
            return
        }

        //update to-do model
        val todo = getUpdatedToDo()
        //TODO: if only one (time or date) is set, set the other to default (now or today)
        if (todo.deadlineTime.isNotEmpty() && todo.deadlineDate.isNotEmpty()) {
            todo.deadlineTimeStamp = Utils.convertTimeAndDateAsString(
                todo.deadlineTime, todo.deadlineDate
            )
        }

        //save to-do to database and return to list
        viewModel.saveToDo(todo)
        Toast.makeText(requireContext(), R.string.toast_todo_saved, Toast.LENGTH_SHORT).show()
        Navigation.findNavController(view)
            .navigate(R.id.action_editToDoFragment_to_toDoListFragment)
    }

    @SuppressLint("SetTextI18n")
    private fun setDateForToDo(view: View) {
        //callback for handling the date set for a to-do
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            @Suppress("NAME_SHADOWING") val month = month + 1
            viewModel.setToDoDate("$day/$month/$year")
        }

        //display date picker and set date for the to-do
        val buttonShowDatePickerDialog =
            view.findViewById<Button>(R.id.buttonShowDatePickerDialogUpdating)
        buttonShowDatePickerDialog.setOnClickListener {
            val datePickerDialogFragment: DialogFragment = DatePickerFragment(dateSetListener)
            datePickerDialogFragment.show(
                activity?.supportFragmentManager!!,
                "DatePickerDialogUpdating"
            )
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setTimeForToDo(view: View) {
        //callback for handling the time set for a to-do
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            viewModel.setToDoTime(
                Utils.fillInZeroInFront(hourOfDay).plus(":").plus(Utils.fillInZeroInFront(minute))
            )
        }

        //display time picker and set time for the to-do
        val buttonShowTimePickerDialog =
            view.findViewById<Button>(R.id.buttonShowTimePickerDialogUpdating)
        buttonShowTimePickerDialog.setOnClickListener {
            val timePickerDialogFragment: DialogFragment = TimePickerFragment(timeSetListener)
            timePickerDialogFragment.show(
                activity?.supportFragmentManager!!,
                "TimePickerDialogUpdating"
            )
        }
    }

    private fun setupActionBar() {
        toolbar = view?.findViewById(R.id.toolbar)!!

        //!!! do not remove try..catch block
        //!!!   ('cause of an espresso bug, instrumented tests fail)
        try {
            //setup action bar back button with navigation component
            val navController = Navigation.findNavController(requireView())
            val appBarConfiguration = AppBarConfiguration(navController.graph)
            toolbar.setupWithNavController(navController, appBarConfiguration)
        } catch (e: Exception) {
        }

        toolbar.title = resources.getString(R.string.fragment_edit_todo)

        //TODO: handle light/dark themes (later in polishing)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbar.setTitleTextColor(resources.getColor(R.color.colorWhite, null))
        } else {
            toolbar.setTitleTextColor(Color.WHITE)
        }
    }

    private fun setupActionBarOptionsMenu(view: View) {
        //inflate menu resource (with option to delete a to-do) into this toolbar
        toolbar.inflateMenu(R.menu.toolbar_menu)

        //handle menu's delete option
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_menu_delete -> {
                    //delete the to-do and goto to-do list
                    viewModel.deleteToDo(getUpdatedToDo())
                    Navigation.findNavController(view).navigate(
                        R.id.action_editToDoFragment_to_toDoListFragment
                    )
                    true
                }
                else -> false
            }
        }
    }

    private fun getUpdatedToDo(): ToDoModel {
        //update to-do model
        val todo = ToDoModel()
        todo.id = fragmentArgs.existingToDo.id
        todo.todo = todoText.text.toString()
        todo.deadlineTime = timeSet.text.toString()
        todo.deadlineDate = dateSet.text.toString()

        return todo
    }
}