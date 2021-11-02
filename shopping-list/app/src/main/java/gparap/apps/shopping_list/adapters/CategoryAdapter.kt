/*
 * Copyright 2021 gparap
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
package gparap.apps.shopping_list.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.shopping_list.R
import gparap.apps.shopping_list.data.CategoryModel

class CategoryAdapter(private val callback: CategoryAdapterCallback) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
    var categories = ArrayList<CategoryModel>()
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        context = parent.context
        val layoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.cardview_categories, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        //display category info
        holder.categoryName.text = categories[position].name.toString()
        holder.itemsCount.text = context.resources.getString(R.string.text_items_count)
            .plus(categories[position].itemsCount.toString())

        //handle itemView clicks
        holder.itemView.setOnClickListener {
            callback.onCategoryViewClickListener(categories[position])
        }

        //handle edit button clicks
        holder.editCategory.setOnClickListener {
            callback.onEditCategoryButtonClickListener(categories[position])
        }

        //handle delete button clicks
        holder.deleteCategory.setOnClickListener {
            callback.onDeleteCategoryButtonViewClickListener(categories[position])
        }
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryName = itemView.findViewById<TextView>(R.id.text_view_category)!!
        var itemsCount = itemView.findViewById<TextView>(R.id.text_view_category_items_count)!!
        val editCategory = itemView.findViewById<ImageView>(R.id.image_view_edit_category)!!
        val deleteCategory = itemView.findViewById<ImageView>(R.id.image_view_delete_category)!!
    }

    /** Callback for listening to RecyclerView's item clicks */
    interface CategoryAdapterCallback {
        fun onCategoryViewClickListener(category: CategoryModel)
        fun onEditCategoryButtonClickListener(category: CategoryModel)
        fun onDeleteCategoryButtonViewClickListener(category: CategoryModel)
    }
}