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
package gparap.apps.shopping_list.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.shopping_list.R
import gparap.apps.shopping_list.adapters.ItemAdapter
import gparap.apps.shopping_list.data.ItemModel
import gparap.apps.shopping_list.utils.AppConstants
import gparap.apps.shopping_list.utils.ItemDialogUtils
import gparap.apps.shopping_list.viewmodels.ItemActivityViewModel
import kotlin.properties.Delegates

class ItemActivity :
    AppCompatActivity(), ItemDialogUtils.DialogCallback, ItemAdapter.ItemAdapterCallback {
    private var categoryId by Delegates.notNull<Int>()
    private lateinit var dialog: AlertDialog
    private lateinit var viewModel: ItemActivityViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        //create the ViewModel of the activity
        viewModel = ViewModelProvider(this)[ItemActivityViewModel::class.java]

        //get category info from intent
        categoryId = intent.getIntExtra(AppConstants.categoryId, -1)
        val categoryName = intent.getStringExtra(AppConstants.categoryName)

        //setup the action bar
        supportActionBar?.title = categoryName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //setup RecyclerView with adapter for category items
        val itemRecyclerView = findViewById<RecyclerView>(R.id.recycler_view_items)
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        val itemAdapter = ItemAdapter(this)
        itemRecyclerView.adapter = itemAdapter

        //observe shopping category items
        viewModel.getShoppingCategoryItems(categoryId).observe(this, {
            itemAdapter.items = it as ArrayList<ItemModel>
            itemAdapter.notifyDataSetChanged()
        })

        //add a new item to the category
        val buttonAdd = findViewById<FloatingActionButton>(R.id.fab_add_category_item)
        buttonAdd.setOnClickListener {
            openAddShoppingCategoryItemDialog()
        }
    }

    //Handles home button to return to categories activity
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("InflateParams")
    private fun openAddShoppingCategoryItemDialog() {
        dialog = ItemDialogUtils(this).createAddItemDialog(
            "Add Item",
            layoutInflater.inflate(R.layout.dialog_add_category_item, null),
            this
        ).apply { show() }
    }

    //Dialog callback for adding a new item to shopping category
    override fun onAddItemPositiveButtonClickListener() {
        viewModel.addShoppingCategoryItem(
            categoryId,
            dialog.findViewById<EditText>(R.id.edit_text_add_category_item_name)?.text.toString()
        )
    }

    //Dialog callback for editing an existing shopping category item
    override fun onEditItemPositiveButtonClickListener(item: ItemModel) {
        //get the edited item name
        val editText = dialog.findViewById<EditText>(R.id.edit_text_edit_category_item_name)
        item.name = editText?.text.toString()

        //update the item
        viewModel.editShoppingCategoryItem(item, categoryId)
    }

    //Dialog callback for canceling action
    override fun onItemNegativeButtonClickListener() {
        return
    }

    //RecyclerView callback for editing an existing shopping category item
    @SuppressLint("InflateParams")
    override fun onEditItemButtonClickListener(item: ItemModel) {
        dialog = ItemDialogUtils(this).createEditItemDialog(
            "Edit Item",
            layoutInflater.inflate(R.layout.dialog_edit_category_item, null),
            this,
            item
        ).apply {
            show()
        }.also {
            //display the item info
            val editText = it.findViewById<EditText>(R.id.edit_text_edit_category_item_name)
            editText?.setText(item.name)
        }
    }

    //RecyclerView callback
    override fun onDeleteItemButtonViewClickListener(item: ItemModel) {
        TODO("Not yet implemented")
    }
}