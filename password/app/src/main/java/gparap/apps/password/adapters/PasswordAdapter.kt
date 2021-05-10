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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.password.R
import gparap.apps.password.data.PasswordModel

class PasswordAdapter(private val passwords: ArrayList<PasswordModel>) :
    RecyclerView.Adapter<PasswordAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //inflate cardview layout
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_password, parent, false)

        //create ViewHolder with inflated cardview layout
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //give password title and value a place inside RecyclerView
        holder.title?.setText(passwords[position].title)
        holder.value?.setText(passwords[position].value)
    }

    override fun getItemCount(): Int {
        return passwords.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: EditText? = itemView.findViewById(R.id.editTextPasswordTitle)
        val value: EditText? = itemView.findViewById(R.id.editTextPasswordValue)
    }
}