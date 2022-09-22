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
package gparap.apps.paidagogaki_gr.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import gparap.apps.paidagogaki_gr.PostActivity
import gparap.apps.paidagogaki_gr.R
import gparap.apps.paidagogaki_gr.data.PostModel

/** Adapter & ViewHolder for Blog's posts */
class PostAdapter : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private lateinit var context: Context
    private var posts: MutableList<PostModel> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setPosts(posts: List<PostModel>) {
        this.posts = posts.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        context = parent.context
        return PostViewHolder(
            LayoutInflater.from(context).inflate(
                context.resources.getLayout(R.layout.cardview_post), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        //set image
        holder.image.setImageDrawable(AppCompatResources.getDrawable(
            context, R.drawable.ic_android_48dp
        ))

        //get text and set title TODO: fix ASCII chars
        var titleText: String =
            posts[position].title.asJsonObject.entrySet().elementAt(0).value.toString()
        titleText = titleText.drop(1).dropLast(1)
        holder.title.text = titleText

        //get post content
        var postContent: String =
            posts[position].content.asJsonObject.entrySet().elementAt(0).value.toString()
        postContent = postContent.drop(1).dropLast(1)

        //open post in new view
        holder.itemView.setOnClickListener {
            val intent = Intent(context, PostActivity::class.java)
            intent.putExtra("blog_post_title", titleText)
            intent.putExtra("blog_post_content", postContent)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class PostViewHolder(itemView: View) : ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageViewBrand)
        val title: TextView = itemView.findViewById(R.id.textViewPostTitle)
    }
}