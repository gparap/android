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
import gparap.apps.shopping_list.data.ItemModel

class ItemAdapter(private val callback: ItemAdapterCallback) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    /**Shopping category items*/
    var items = ArrayList<ItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.cardview_items, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        //display item info
        holder.itemName.text = items[position].name.toString()

        //handle itemView clicks
        holder.itemView.setOnClickListener {
            callback.onItemViewClickListener(items[position])
        }

        //handle edit button clicks
        holder.editCategory.setOnClickListener {
            callback.onEditItemButtonClickListener(items[position])
        }

        //handle delete button clicks
        holder.deleteCategory.setOnClickListener {
            callback.onDeleteItemButtonViewClickListener(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName = itemView.findViewById<TextView>(R.id.text_view_item)!!
        val editCategory = itemView.findViewById<ImageView>(R.id.image_view_edit_item)!!
        val deleteCategory = itemView.findViewById<ImageView>(R.id.image_view_delete_item)!!
    }

    /** Callback for listening to RecyclerView's item clicks */
    interface ItemAdapterCallback {
        fun onItemViewClickListener(item: ItemModel)
        fun onEditItemButtonClickListener(item: ItemModel)
        fun onDeleteItemButtonViewClickListener(item: ItemModel)
    }
}