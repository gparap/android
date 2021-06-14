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

import android.graphics.Color
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import gparap.apps.todo_list.R

class EditToDoFragment : Fragment() {
    private lateinit var viewModel: EditToDoViewModel
    private val fragmentArgs by navArgs<EditToDoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        viewModel = ViewModelProvider(this).get(EditToDoViewModel::class.java)

        //get widgets
        val todoText = view.findViewById<EditText>(R.id.editTextToDoUpdating)
        val timeSet = view.findViewById<TextView>(R.id.textViewToDoTimeSetUpdating)
        val dateSet = view.findViewById<TextView>(R.id.textViewToDoDateSetUpdating)

        //populate widgets from fragment arguments
        todoText.setText(fragmentArgs.existingToDo.todo)
        timeSet.text = fragmentArgs.existingToDo.deadlineTime
        dateSet.text = fragmentArgs.existingToDo.deadlineDate
    }

    private fun setupActionBar() {
        val toolbar = view?.findViewById<MaterialToolbar>(R.id.toolbar)
        if (toolbar != null) {

            //!!! do not remove try..catch block
            //!!!   ('cause of an espresso bug, instrumented tests fail)
            try{
                //setup action bar back button with navigation component
                val navController = Navigation.findNavController(requireView())
                val appBarConfiguration = AppBarConfiguration(navController.graph)
                toolbar.setupWithNavController(navController, appBarConfiguration)
            }
            catch (e: Exception){}

            toolbar.title = resources.getString(R.string.fragment_edit_todo)

            //TODO: handle light/dark themes (later in polishing)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                toolbar.setTitleTextColor(resources.getColor(R.color.colorWhite, null))
            } else {
                toolbar.setTitleTextColor(Color.WHITE)
            }
        }
    }
}