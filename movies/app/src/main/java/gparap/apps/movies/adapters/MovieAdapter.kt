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
package gparap.apps.movies.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import gparap.apps.movies.R
import gparap.apps.movies.model.MovieModel
import com.bumptech.glide.request.RequestOptions

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {
    private var context: Context? = null
    var movies = ArrayList<MovieModel>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value

            //the data set has changed
            Handler(Looper.getMainLooper()).post {
                notifyDataSetChanged()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        context = parent.context

        //create view
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.cardview_movies, parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        //customize loading with placeholder
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_baseline_image_24)
            .fitCenter()
            .centerCrop()

        //display movie image
        Glide.with(context!!)
            .applyDefaultRequestOptions(requestOptions)
            .load(movies[position].imageUrl)
            .into(holder.image!!)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView? = itemView.findViewById(R.id.image_view_movie)
    }
}