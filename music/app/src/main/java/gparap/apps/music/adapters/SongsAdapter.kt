/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.music.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import gparap.apps.music.R
import gparap.apps.music.data.SongResponseModel

class SongsAdapter : RecyclerView.Adapter<SongsAdapter.SongsViewHolder>() {
    private lateinit var context: Context
    private var songs = ArrayList<SongResponseModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setSongs(songs: ArrayList<SongResponseModel>?) {
        if (songs != null) {
            this.songs = songs
            notifyDataSetChanged()
        }
    }

    class SongsViewHolder(itemView: View) : ViewHolder(itemView) {
        val icon: ImageView = itemView.findViewById(R.id.imageViewSongIcon)
        val title: TextView = itemView.findViewById(R.id.textViewSongTitle)
        val duration: TextView = itemView.findViewById(R.id.textViewSongDuration)
        val size: TextView = itemView.findViewById(R.id.textViewSongSize)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongsViewHolder {
        //get parent context
        context = parent.context

        //create item view
        return SongsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    parent.context.resources.getLayout(R.layout.cardview_song), parent, false
                )
        )
    }

    override fun onBindViewHolder(holder: SongsViewHolder, position: Int) {
        holder.icon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_launcher_foreground))
        holder.title.text = songs[position].songInfo[0].title
        holder.duration.text = songs[position].songInfo[0].duration
        holder.size.text = songs[position].fileInfo[0].size
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}