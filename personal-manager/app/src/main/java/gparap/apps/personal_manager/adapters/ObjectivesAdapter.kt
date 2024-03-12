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

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import gparap.apps.personal_manager.R
import gparap.apps.personal_manager.data.ObjectiveModel
import gparap.apps.personal_manager.ui.MainActivity
import gparap.apps.personal_manager.ui.UpdateObjectiveActivity
import gparap.apps.personal_manager.utils.AppConstants.INTENT_EXTRA_OBJECTIVE_DESCRIPTION
import gparap.apps.personal_manager.utils.AppConstants.INTENT_EXTRA_OBJECTIVE_DUE_DATE
import gparap.apps.personal_manager.utils.AppConstants.INTENT_EXTRA_OBJECTIVE_ID
import gparap.apps.personal_manager.utils.AppConstants.INTENT_EXTRA_OBJECTIVE_INCEPTION_DATE
import gparap.apps.personal_manager.utils.AppConstants.INTENT_EXTRA_OBJECTIVE_TITLE
import kotlinx.coroutines.launch

class ObjectivesAdapter : Adapter<ObjectivesAdapter.ObjectivesViewHolder>() {
    var objectives = ArrayList<ObjectiveModel>()
    private var context: Context? = null
    private var objectiveListener: ObjectiveListener? = null

    fun setObjectivesAdapterListener(objectiveListener: ObjectiveListener) {
        this.objectiveListener = objectiveListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectivesViewHolder {
        //create the inflated item view
        context = parent.context
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
        holder.dueDate.text = objectives[position].dueDate.toString()
        holder.inceptionDate.text = objectives[position].inceptionDate.toString()

        //open objective in new activity for editing
        holder.itemView.setOnClickListener {
            val intent = Intent(context, UpdateObjectiveActivity::class.java)
            intent.putExtra(INTENT_EXTRA_OBJECTIVE_ID, objectives[position].id)
            intent.putExtra(INTENT_EXTRA_OBJECTIVE_TITLE, objectives[position].title)
            intent.putExtra(INTENT_EXTRA_OBJECTIVE_DESCRIPTION, objectives[position].description)
            intent.putExtra(INTENT_EXTRA_OBJECTIVE_DUE_DATE, objectives[position].dueDate.time)
            intent.putExtra(
                INTENT_EXTRA_OBJECTIVE_INCEPTION_DATE,
                objectives[position].inceptionDate.time
            )
            context?.startActivity(intent)
        }

        //delete objective
        holder.itemView.setOnLongClickListener { _ ->
            //display a confirmation dialog before deletion
            context?.let {
                AlertDialog.Builder(it)
                    .setTitle(R.string.text_delete_objective)
                    .setMessage(R.string.dialog_msg_delete_objective)
                    .setPositiveButton(R.string.dialog_ok) { _, _ ->
                        (it as MainActivity).lifecycleScope.launch {
                            //create objective
                            val objective = ObjectiveModel(
                                objectives[position].title,
                                objectives[position].description,
                                objectives[position].dueDate,
                                objectives[position].inceptionDate
                            )
                            objective.id = objectives[position].id

                            //delete objective callback
                            objectiveListener?.onLongClickPress(objective)
                        }
                    }
                    .setNegativeButton(R.string.dialog_cancel) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .create().show()
            }
            return@setOnLongClickListener true
        }
    }

    class ObjectivesViewHolder(itemView: View) : ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.cardview_objective_title)
        val description: TextView = itemView.findViewById(R.id.cardview_objective_description)
        val dueDate: TextView = itemView.findViewById(R.id.cardview_objective_due_date)
        val inceptionDate: TextView = itemView.findViewById(R.id.cardview_objective_inception_date)
    }

    /** Callback for handling the AlertDialog's positive button inside the Adapter. */
    interface ObjectiveListener {
        fun onLongClickPress(objectiveModel: ObjectiveModel)
    }
}