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
package gparap.apps.personal_manager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.personal_manager.adapters.ObjectivesAdapter
import gparap.apps.personal_manager.data.ObjectiveModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //create a test list of objectives
        val testObjectives = ArrayList<ObjectiveModel>()
        testObjectives.addAll(
            listOf(
                ObjectiveModel("objective 1", "desc 1", "2024, 31, 1", "2024, 31, 12"),
                ObjectiveModel("objective 2", "desc 2", "2024, 31, 2", "2024, 31, 12"),
                ObjectiveModel("objective 3", "desc 3", "2024, 31, 3", "2024, 31, 12"),
                ObjectiveModel("objective 4", "desc 4", "2024, 31, 4", "2024, 31, 12"),
                ObjectiveModel("objective 5", "desc 5", "2024, 31, 5", "2024, 31, 12")
            )
        )

        //create recycler view with adapter
        val adapterObjectives = ObjectivesAdapter().apply { objectives = testObjectives }
        val recyclerViewObjectives = findViewById<RecyclerView>(R.id.recycler_view_objectives)
        recyclerViewObjectives.layoutManager = LinearLayoutManager(this)
        recyclerViewObjectives.adapter = adapterObjectives

        //goto add objective activity
        findViewById<FloatingActionButton>(R.id.fab_add_objective).setOnClickListener {
            startActivity(Intent(this, AddObjectiveActivity::class.java))
        }
    }
}