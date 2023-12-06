/*
 * Copyright 2023 gparap
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
package gparap.apps.player_video.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import gparap.apps.player_video.R
import gparap.apps.player_video.data.VideoModel

class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {
    var videos = ArrayList<VideoModel>()
    private lateinit var context: Context

    class VideoViewHolder(itemView: View) : ViewHolder(itemView) {
        val videoPlay = itemView.findViewById<ImageView>(R.id.image_button_video_play)
        val videoTitle = itemView.findViewById<TextView>(R.id.text_view_video_title)
        val videoDescription = itemView.findViewById<TextView>(R.id.text_view_video_description)
        val videoLength = itemView.findViewById<TextView>(R.id.text_view_video_length)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context)
            .inflate(context.resources.getLayout(R.layout.cardview_video_item), parent, false)
        return VideoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        //display the video image
        Glide.with(context).load(videos[position].path).into(
            holder.videoPlay
        )

        //display the video details
        holder.videoTitle.text = videos[position].title
        holder.videoDescription.text = videos[position].description
        holder.videoLength.text = videos[position].length
    }
}