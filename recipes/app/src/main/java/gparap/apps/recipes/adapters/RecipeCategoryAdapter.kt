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
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gparap.apps.recipes.CategoryRecipesActivity
import gparap.apps.recipes.R
import gparap.apps.recipes.data.RecipeCategoryModel
import gparap.apps.recipes.utils.AppConstants

class RecipeCategoryAdapter :
    RecyclerView.Adapter<RecipeCategoryAdapter.RecipeCategoryViewHolder>() {
    private var recipeCategories = ArrayList<RecipeCategoryModel>()
    private lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun setRecipeCategories(recipeCategories: ArrayList<RecipeCategoryModel>) {
        this.recipeCategories = recipeCategories
        notifyDataSetChanged()
    }

    class RecipeCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryName: TextView = itemView.findViewById(R.id.text_view_recipe_category)
        val categoryImage: ImageView = itemView.findViewById(R.id.image_view_recipe_category)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeCategoryViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        val itemView = layoutInflater.inflate(
            context.resources.getLayout(R.layout.cardview_category), parent, false
        )
        return RecipeCategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return recipeCategories.size
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: RecipeCategoryViewHolder, position: Int) {
        holder.categoryName.text = recipeCategories[position].name

        val categoryImageUri = recipeCategories[position].imageUri
        Picasso.get().load(categoryImageUri).into(holder.categoryImage)

        //open the category recipes activity
        holder.categoryName.setOnClickListener {
            val intent = Intent(this.context, CategoryRecipesActivity::class.java)
            intent.putExtra(AppConstants.CATEGORY_NAME_EXTRA, holder.categoryName.text.toString())
            context.startActivity(intent)
        }
    }
}