/*
 * Copyright 2022 gparap
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
package gparap.apps.player_music.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import gparap.apps.player_music.R
import gparap.apps.player_music.data.StorageFileModel

class StorageFilesAdapter : RecyclerView.Adapter<StorageFilesAdapter.StorageFilesViewHolder>() {
    var storageFiles = ArrayList<StorageFileModel>()

    class StorageFilesViewHolder(itemView: View) : ViewHolder(itemView){
        val storageFilename: TextView = itemView.findViewById(R.id.textViewStorageFilename)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageFilesViewHolder {
        //inflate view
        val storageFileView = LayoutInflater.from(parent.context).inflate(
            R.layout.cardview_storage_file, parent, false
        )
        //create view
        return StorageFilesViewHolder(storageFileView)
    }

    override fun onBindViewHolder(holder: StorageFilesViewHolder, position: Int) {
        holder.storageFilename.text = storageFiles[position].filename
    }

    override fun getItemCount(): Int {
        return storageFiles.size
    }
}