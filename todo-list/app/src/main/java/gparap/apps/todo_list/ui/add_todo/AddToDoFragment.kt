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
package gparap.apps.todo_list.ui.add_todo

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.todo_list.R
import gparap.apps.todo_list.data.ToDoModel
import gparap.apps.todo_list.ui.pickers.DatePickerFragment
import gparap.apps.todo_list.ui.pickers.TimePickerFragment
import gparap.apps.todo_list.utils.Utils


class AddToDoFragment : Fragment() {
    private lateinit var viewModel: AddToDoViewModel
    private lateinit var textViewToDoTimeSet: TextView
    private lateinit var textViewToDoDateSet: TextView
    private lateinit var fragmentViewRef: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentViewRef = view
        setupActionBar()

        //get existing (or create new) ViewModel associated with the given Fragment
        viewModel = ViewModelProvider(this).get(AddToDoViewModel::class.java)

        //display dialogs to set the time and date for to-do
        setTimeForToDo()
        setDateForToDo()

        //observe to-do time set
        textViewToDoTimeSet = view.findViewById(R.id.textViewToDoTimeSet)
        viewModel.getToDoTime().observe(viewLifecycleOwner, {
            textViewToDoTimeSet.text = it
        })

        //observe to-do date set
        textViewToDoDateSet = view.findViewById(R.id.textViewToDoDateSet)
        viewModel.getToDoDate().observe(viewLifecycleOwner, {
            textViewToDoDateSet.text = it
        })

        //add a to-do to the database
        val buttonSave = view.findViewById<FloatingActionButton>(R.id.fabSaveToDo)
        buttonSave.setOnClickListener {
            saveToDo()
        }
    }

    private fun saveToDo() {
        //get the to-do text
        val todoText = fragmentViewRef.findViewById<EditText>(R.id.editTextToDo)
        if (TextUtils.isEmpty(todoText.text)) {
            Toast.makeText(requireContext(), R.string.toast_todo_empty, Toast.LENGTH_SHORT).show()
            return
        }

        //create and init a new to-do
        val todo = ToDoModel()
        todo.todo = todoText.text.toString()
        todo.deadlineTime = textViewToDoTimeSet.text.toString()
        todo.deadlineDate = textViewToDoDateSet.text.toString()
        //set the deadline timestamp for the to-do
        if (todo.deadlineTime.isNotEmpty() && todo.deadlineDate.isNotEmpty()) {
            todo.deadlineTimeStamp = Utils.convertTimeAndDateAsString(
                todo.deadlineTime, todo.deadlineDate
            )
        } else if (todo.deadlineTime.isNotEmpty() && todo.deadlineDate.isEmpty()) {
            todo.deadlineTimeStamp = Utils.convertTimeAsStringLocale(todo.deadlineTime)
        } else if (todo.deadlineTime.isEmpty() && todo.deadlineDate.isNotEmpty()) {
            todo.deadlineTimeStamp = Utils.convertDateAsStringLocale(todo.deadlineDate)
        }

        //save to-do to database and return to list
        viewModel.addToDo(todo)
        Toast.makeText(requireContext(), R.string.toast_todo_saved, Toast.LENGTH_SHORT).show()
        Navigation.findNavController(fragmentViewRef)
            .navigate(R.id.action_addToDoFragment_to_toDoListFragment)
    }

    @SuppressLint("SetTextI18n")
    private fun setDateForToDo() {
        //callback for handling the date set for a to-do
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            @Suppress("NAME_SHADOWING") val month = month + 1
            viewModel.setToDoDate("$day/$month/$year")
        }

        //display date picker and set date for the to-do
        val buttonShowDatePickerDialog =
            fragmentViewRef.findViewById<Button>(R.id.buttonShowDatePickerDialog)
        buttonShowDatePickerDialog.setOnClickListener {
            val datePickerDialogFragment: DialogFragment = DatePickerFragment(dateSetListener)
            datePickerDialogFragment.show(activity?.supportFragmentManager!!, "DatePickerDialog")
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setTimeForToDo() {
        //callback for handling the time set for a to-do
        val timeSetListener = OnTimeSetListener { _, hourOfDay, minute ->
            viewModel.setToDoTime(
                Utils.fillInZeroInFront(hourOfDay).plus(":").plus(Utils.fillInZeroInFront(minute))
            )
        }

        //display time picker and set time for the to-do
        val buttonShowTimePickerDialog =
            fragmentViewRef.findViewById<Button>(R.id.buttonShowTimePickerDialog)
        buttonShowTimePickerDialog.setOnClickListener {
            val timePickerDialogFragment: DialogFragment = TimePickerFragment(timeSetListener)
            timePickerDialogFragment.show(activity?.supportFragmentManager!!, "TimePickerDialog")
        }
    }

    private fun setupActionBar() {
        val toolbar = view?.findViewById<MaterialToolbar>(R.id.toolbar)
        if (toolbar != null) {

            //!!! do not remove try..catch block
            //!!!   ('cause of an espresso bug, instrumented tests fail)
            try {
                //setup action bar back button with navigation component
                val navController = Navigation.findNavController(requireView())
                val appBarConfiguration = AppBarConfiguration(navController.graph)
                toolbar.setupWithNavController(navController, appBarConfiguration)
            } catch (e: Exception) {
            }

            toolbar.title = resources.getString(R.string.fragment_add_todo)

            //TODO: handle light/dark themes (later in polishing)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                toolbar.setTitleTextColor(resources.getColor(R.color.colorWhite, null))
            } else {
                toolbar.setTitleTextColor(Color.WHITE)
            }
        }
    }
}