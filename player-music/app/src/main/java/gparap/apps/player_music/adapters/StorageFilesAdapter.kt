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

import android.content.ContentUris
import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import gparap.apps.player_music.R
import gparap.apps.player_music.data.StorageFileModel

class StorageFilesAdapter : RecyclerView.Adapter<StorageFilesAdapter.StorageFilesViewHolder>() {
    var storageFiles = ArrayList<StorageFileModel>()
    private lateinit var context: Context
    private lateinit var mediaPlayer: MediaPlayer

    class StorageFilesViewHolder(itemView: View) : ViewHolder(itemView) {
        val storageFilename: TextView = itemView.findViewById(R.id.textViewStorageFilename)
        val playButton: ImageButton = itemView.findViewById(R.id.imageButtonPlayStorageFile)
        val stopButton: ImageButton = itemView.findViewById(R.id.imageButtonStopStorageFile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StorageFilesViewHolder {
        //get the context the view
        context = parent.context

        //inflate view
        val storageFileView = LayoutInflater.from(parent.context).inflate(
            R.layout.cardview_storage_file, parent, false
        )
        //create view
        return StorageFilesViewHolder(storageFileView)
    }

    override fun onBindViewHolder(holder: StorageFilesViewHolder, position: Int) {
        holder.storageFilename.text = storageFiles[position].filename

        //TODO: refactor code & check files
        //play the audio file
        holder.playButton.setOnClickListener {
            val id = storageFiles[position].id
            val path = storageFiles[position].filepath

            //get the URI of the storage file inside te device (SDK >= 29)
            var uri: Uri? = null
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                uri = ContentUris.withAppendedId(
                    android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id
                )
            }

            //create a MediaPlayer object and set its attributes
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                when {
                    android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.P ->
                        setDataSource(path)
                    android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q ->
                        setDataSource(context, uri!!)
                }
                setScreenOnWhilePlaying(true)
            }

            //play the storage file
            mediaPlayer.prepare()
            mediaPlayer.start()
        }

        //stop the audio file and release resources
        holder.stopButton.setOnClickListener {
            mediaPlayer.release()
        }
    }

    override fun getItemCount(): Int {
        return storageFiles.size
    }
}