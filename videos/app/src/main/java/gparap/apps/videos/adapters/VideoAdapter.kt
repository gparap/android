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
package gparap.apps.videos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import gparap.apps.videos.R
import gparap.apps.videos.models.SadApiVideoModel

class VideoAdapter : Adapter<VideoAdapter.VideoViewHolder>() {
    var videos: ArrayList<SadApiVideoModel> = ArrayList()
    private var context: Context? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        //get context for Glide
        context = parent.context

        //inflate view
        val view = LayoutInflater.from(parent.context).inflate(
            parent.context.resources.getLayout(R.layout.cardview_video), parent, false
        )

        //create and return itemView
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        //load video thumbnail
        Glide.with(context!!)
            .load(videos[position].thumbnails[0].url)
            .into(holder.imageViewVideo)

        //display video details
        holder.title.text = videos[position].title
        holder.channel.text = videos[position].channel
        holder.date.text = videos[position].date
    }

    override fun getItemCount(): Int {
        return videos.size
    }

    class VideoViewHolder(itemView: View) : ViewHolder(itemView) {
        val imageViewVideo = itemView.findViewById<ImageView>(R.id.image_view_video)
        val imageViewCreator = itemView.findViewById<ImageView>(R.id.image_view_creator)
        val title = itemView.findViewById<TextView>(R.id.text_view_video_title)
        val channel = itemView.findViewById<TextView>(R.id.text_view_video_creator_channel)
        val date = itemView.findViewById<TextView>(R.id.text_view_video_date_created)
    }
}