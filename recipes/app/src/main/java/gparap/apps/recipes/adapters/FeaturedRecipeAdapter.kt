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
package gparap.apps.recipes.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import gparap.apps.recipes.R
import gparap.apps.recipes.data.RecipeModel

class FeaturedRecipeAdapter : RecyclerView.Adapter<FeaturedRecipeAdapter.RecipeViewHolder>() {
    private var featuredRecipes = ArrayList<RecipeModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setFeaturedRecipes(featuredRecipes: ArrayList<RecipeModel>) {
        this.featuredRecipes = featuredRecipes
        notifyDataSetChanged()
    }

    class RecipeViewHolder(itemView: View) : ViewHolder(itemView) {
        var recipeImage: ImageView = itemView.findViewById(R.id.image_view_recipe)
        var recipeText: TextView = itemView.findViewById(R.id.text_view_recipe)
    }

    override fun onCreateViewHolder(parent: ViewGroup,viewType: Int): RecipeViewHolder {
        //create the item view
        LayoutInflater.from(parent.context).inflate(
            parent.context.resources.getLayout(R.layout.cardview_recipe), parent, false
        ).apply {
            return RecipeViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        //display recipe image
        Picasso.get()
            .load(featuredRecipes[position].image[0].url)
            .placeholder(R.drawable.ic_image_placeholder_24)
            .into(holder.recipeImage)

        //display recipe text
        holder.recipeText.text = featuredRecipes[position].title
    }

    override fun getItemCount(): Int {
        return featuredRecipes.size
    }
}