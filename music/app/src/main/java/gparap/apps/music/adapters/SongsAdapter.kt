/*
 * Copyright (c) 2023 gparap
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
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import gparap.apps.music.R
import gparap.apps.music.data.SongResponseModel
import gparap.apps.music.ui.SongActivity
import gparap.apps.music.utils.AppConstants
import gparap.apps.music.utils.Utils

class SongsAdapter : RecyclerView.Adapter<SongsAdapter.SongsViewHolder>() {
    private lateinit var context: Context
    private var songs = kotlin.collections.ArrayList<SongResponseModel>()
    private var mediaPlayer: MediaPlayer? = null
    private var songDialog: AlertDialog? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setSongs(songs: ArrayList<SongResponseModel>?) {
        if (songs != null) {
            this.songs = songs
            notifyDataSetChanged()
        }
    }

    class SongsViewHolder(itemView: View) : ViewHolder(itemView) {
        val iconPlay: ImageView = itemView.findViewById(R.id.imageViewPlaySong)
        val title: TextView = itemView.findViewById(R.id.textViewSongTitle)
        val duration: TextView = itemView.findViewById(R.id.textViewSongDuration)
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
        //play the song using the multimedia framework's primary API (MediaPlayer)
        holder.iconPlay.setOnClickListener {
            //kill any previous MediaPlayer instance
            if (mediaPlayer != null) {
                mediaPlayer!!.release()
            }

            //initialize new MediaPlayer object
            mediaPlayer = Utils.initMediaPlayer(songs[position].urls[0].downloadUrl)

            //play the song
            mediaPlayer!!.prepare()
            mediaPlayer!!.start()

            //display a dialog with the current song that is playing
            songDialog = Utils.createSongDialog(context, mediaPlayer, songs[position].songInfo[0].title)
            songDialog?.show()

            //stop the song automatically
            mediaPlayer?.setOnCompletionListener {
                mediaPlayer?.release()
                songDialog?.dismiss()
            }
        }

        //display song info
        holder.title.text = songs[position].songInfo[0].title
        holder.duration.text = songs[position].songInfo[0].duration

        //goto SongActivity
        holder.itemView.setOnClickListener {
            //create an intent for the SongActivity
            val intent = Intent(context, SongActivity::class.java)

            //get the song title and add it to the intent
            val appBarTitle = songs[position].songInfo[0].title
            intent.putExtra(AppConstants.EXTRAS_APPBAR_TITLE, appBarTitle)

            //add song extended data to the intent and goto SongActivity
            intent.putExtra(AppConstants.EXTRAS_SONG_INFO, songs[position].songInfo[0])
            intent.putExtra(AppConstants.EXTRAS_SONG_URLS, songs[position].urls[0])
            intent.putExtra(AppConstants.EXTRAS_SONG_FILE, songs[position].fileInfo[0])
            intent.putExtra(AppConstants.EXTRAS_SONG_ATTRIBUTES, songs[position].attributes[0])
            intent.putExtra(AppConstants.EXTRAS_SONG_CATEGORY, songs[position].category[0])
            intent.putExtra(AppConstants.EXTRAS_SONG_LICENCE, songs[position].licence[0])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return songs.size
    }
}