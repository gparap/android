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
package gparap.apps.personal_manager.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import gparap.apps.personal_manager.R
import gparap.apps.personal_manager.data.ObjectiveModel

class ObjectivesAdapter : Adapter<ObjectivesAdapter.ObjectivesViewHolder>() {
    var objectives = ArrayList<ObjectiveModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectivesViewHolder {
        //create the inflated item view
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.cardview_objective, parent, false)
        return ObjectivesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return objectives.size
    }

    override fun onBindViewHolder(holder: ObjectivesViewHolder, position: Int) {
        //display the objective details
        holder.title.text = objectives[position].title
        holder.description.text = objectives[position].description
        //TODO: revert back to Date values
        holder.dueDate.text = objectives[position].dueDate
        holder.inceptionDate.text = objectives[position].inceptionDate
    }

    class ObjectivesViewHolder(itemView: View) : ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.cardview_objective_title)
        val description: TextView = itemView.findViewById(R.id.cardview_objective_description)
        val dueDate: TextView = itemView.findViewById(R.id.cardview_objective_due_date)
        val inceptionDate: TextView = itemView.findViewById(R.id.cardview_objective_inception_date)
    }
}