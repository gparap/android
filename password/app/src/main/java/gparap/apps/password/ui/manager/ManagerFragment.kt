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
package gparap.apps.password.ui.manager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.password.R
import gparap.apps.password.adapters.PasswordAdapter
import gparap.apps.password.data.DatabaseManager
import gparap.apps.password.data.PasswordModel

class ManagerFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_manager, container, false)

        //setup a password RecyclerView with adapter
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerViewPasswords)
        val passwords = ArrayList<PasswordModel>()
        val adapter = PasswordAdapter(passwords)
        recyclerView.adapter = adapter

        //fetch all passwords from database
        val databaseManager = DatabaseManager(this.requireContext())
        passwords.addAll(databaseManager.fetchPasswords())

        return rootView
    }
}