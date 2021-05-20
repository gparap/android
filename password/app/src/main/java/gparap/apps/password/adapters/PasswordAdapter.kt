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
package gparap.apps.password.adapters

import android.content.Context
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.password.R
import gparap.apps.password.data.DatabaseManager
import gparap.apps.password.data.PasswordModel

class PasswordAdapter(private val passwords: ArrayList<PasswordModel>) :
    RecyclerView.Adapter<PasswordAdapter.ViewHolder>() {
    private lateinit var context: Context
    private lateinit var dbManager: DatabaseManager

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        dbManager = DatabaseManager(context)

        //inflate cardview layout
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_password, parent, false)

        //create ViewHolder with inflated cardview layout
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title?.setText(passwords[position].title)
        holder.value?.setText(passwords[position].value)

        //toggle password visibility
        holder.showHidePassword?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                holder.value?.transformationMethod = null
            } else {
                holder.value?.transformationMethod = PasswordTransformationMethod()
            }
        }

        //delete password from database and remove from list
        holder.deletePassword?.setOnClickListener {
            dbManager.deletePassword(passwords[position].id)
            passwords.removeAt(position)
            this.notifyDataSetChanged()
        }

        //update password to database and list
        holder.updatePassword?.setOnClickListener {
            //create password model from user input
            val password = PasswordModel(
                passwords[position].id,
                holder.title?.text.toString(),
                holder.value?.text.toString()
            )
            //validate and update password to database and list
            if (holder.title?.text.toString().isNotBlank() &&
                holder.value?.text.toString().isNotBlank()
            ) {
                dbManager.updatePassword(password)
                passwords[position].title = holder.title?.text.toString()
                passwords[position].value = holder.value?.text.toString()
                this.notifyDataSetChanged()
            } else {
                if (holder.title?.text.toString().isBlank()) {
                    Toast.makeText(context, R.string.toast_empty_title, Toast.LENGTH_SHORT).show()
                } else if (holder.value?.text.toString().isBlank()) {
                    Toast.makeText(context, R.string.toast_empty_password, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return passwords.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //get cardview widgets
        val title: EditText? = itemView.findViewById(R.id.editTextPasswordTitle)
        val value: EditText? = itemView.findViewById(R.id.editTextPasswordValue)
        val showHidePassword: SwitchCompat? = itemView.findViewById(R.id.switchShowHidePassword)
        val deletePassword: ImageButton? = itemView.findViewById(R.id.iconDeletePassword)
        val updatePassword: ImageButton? = itemView.findViewById(R.id.iconSavePassword)
    }
}