/*
 * Copyright 2024 gparap
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
package gparap.apps.personal_manager.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.personal_manager.R
import gparap.apps.personal_manager.adapters.ObjectivesAdapter
import gparap.apps.personal_manager.data.ObjectiveModel
import gparap.apps.personal_manager.viewmodels.ObjectiveViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerViewObjectives: RecyclerView
    private lateinit var adapterObjectives: ObjectivesAdapter
    private lateinit var viewModel: ObjectiveViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get the ViewModel in the scope of of this activity
        viewModel = ViewModelProvider(this)[ObjectiveViewModel::class.java]

        //display objectives fetched from the database
        viewModel.getObjectives().observe(this) {
            //create recycler view with adapter
            adapterObjectives = ObjectivesAdapter().apply {
                objectives = it as ArrayList<ObjectiveModel>
            }
            recyclerViewObjectives = findViewById(R.id.recycler_view_objectives)
            recyclerViewObjectives.layoutManager = LinearLayoutManager(this)
            recyclerViewObjectives.adapter = adapterObjectives

            //set a listener for deleting a selected objective with the long click event
            adapterObjectives.setObjectivesAdapterListener(object :
                ObjectivesAdapter.ObjectiveListener {
                override fun onLongClickPress(objectiveModel: ObjectiveModel) {
                    viewModel.deleteObjective(objectiveModel)
                }
            })
        }

        //goto add objective activity
        findViewById<FloatingActionButton>(R.id.fab_add_objective).setOnClickListener {
            startActivity(Intent(this, AddObjectiveActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //goto add objective activity
            R.id.menu_item_add_objective -> {
                startActivity(Intent(this, AddObjectiveActivity::class.java))
            }
            //delete all objectives
            R.id.menu_item_delete_objectives -> {
                //display a confirmation dialog before deletion
                AlertDialog.Builder(this)
                    .setTitle(R.string.text_delete_objectives)
                    .setMessage(R.string.dialog_msg_delete_objectives)
                    .setPositiveButton(R.string.dialog_ok) { _, _ ->
                        //delete objectives from the database
                        viewModel.deleteAllObjectives()
                    }
                    .setNegativeButton(R.string.dialog_cancel) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create().show()
            }
            //display the "about app" text
            R.id.menu_item_about_app -> {
                TODO("Not implemented yet")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}