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
package gparap.apps.wallpaper.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gparap.apps.wallpaper.R
import gparap.apps.wallpaper.data.WallpaperModel
import gparap.apps.wallpaper.ui.WallpaperActivity

class WallpaperAdapter : RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder>() {
    private lateinit var context: Context
    var wallpapers = ArrayList<WallpaperModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperViewHolder {
        //get context
        context = parent.context

        //create and return view
        return WallpaperViewHolder(LayoutInflater.from(context).inflate(
            R.layout.cardview_wallpaper, parent, false))
    }

    override fun onBindViewHolder(holder: WallpaperViewHolder, position: Int) {
        //display wallpaper image
        try {
            Glide.with(context)
                .load(wallpapers[position].imageUrl)
                .into(holder.wallpaper)
        } catch (e: Exception) {
        }

        //open wallpaper object in activity
        holder.wallpaper.setOnClickListener {
            val intent = Intent(context, WallpaperActivity::class.java)
            intent.putExtra("wallpaper", wallpapers[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return wallpapers.size
    }

    class WallpaperViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wallpaper: ImageView = itemView.findViewById(R.id.item_view_wallpaper)
    }
}
