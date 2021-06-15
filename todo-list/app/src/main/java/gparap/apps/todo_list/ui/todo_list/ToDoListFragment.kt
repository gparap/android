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

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.todo_list.R
import gparap.apps.todo_list.adapter.ToDoAdapter
import gparap.apps.todo_list.utils.SwipeHelperCallback

class ToDoListFragment : Fragment(R.layout.fragment_todo_list) {
    private lateinit var viewModel: ToDoListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar()
        viewModel = ViewModelProvider(this).get(ToDoListViewModel::class.java)

        //navigate to add a new to-do
        val fabAddToDo = view.findViewById<FloatingActionButton>(R.id.fabAddToDo)
        fabAddToDo.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_toDoListFragment_to_addToDoFragment)
        }

        //setup RecyclerView with adapter
        val adapter = ToDoAdapter()
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewToDo)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //setup RecyclerView with swipe-to-delete functionality
        val swipeHelperCallback: ItemTouchHelper.Callback =
            SwipeHelperCallback(
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
                adapter,
                requireContext()
            )
        val itemTouchHelper = ItemTouchHelper(swipeHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        //observe to-do list
        viewModel.getToDoList().observe(viewLifecycleOwner, {
            adapter.setTodosList(it)
        })
    }

    private fun setupActionBar() {
        val toolbar = view?.findViewById<MaterialToolbar>(R.id.toolbar)
        if (toolbar != null) {
            toolbar.title = resources.getString(R.string.fragment_todo_list_title)

            //TODO: handle light/dark themes (later in polishing)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                toolbar.setTitleTextColor(resources.getColor(R.color.colorWhite, null))
            } else {
                toolbar.setTitleTextColor(Color.WHITE)
            }
        }
    }
}