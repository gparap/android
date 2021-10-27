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
package gparap.apps.shopping_list

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.shopping_list.adapters.CategoryAdapter
import gparap.apps.shopping_list.data.CategoryModel
import gparap.apps.shopping_list.utils.DialogUtils

class MainActivity : AppCompatActivity(), DialogUtils.DialogCallback,
    CategoryAdapter.CategoryAdapterCallback {
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup RecyclerView with adapter for shopping categories
        val categoryRecyclerView = findViewById<RecyclerView>(R.id.recycler_view_categories)
        categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        val categoryAdapter = CategoryAdapter(this)
        categoryRecyclerView.adapter = categoryAdapter
        //TODO categoryAdapter.categories from database

        //add shopping category
        val fabAddCategory = findViewById<FloatingActionButton>(R.id.fab_add_shopping_category)
        fabAddCategory.setOnClickListener {
            addShoppingCategory()
        }
    }

    @SuppressLint("InflateParams")
    private fun addShoppingCategory() {
        //open dialog for adding a new shopping category
        dialog = DialogUtils(this).createDialog(
            this.resources.getString(R.string.text_add_shopping_category),
            layoutInflater.inflate(R.layout.dialog_add_category, null),
            this
        ).apply { show() }
    }

    //Dialog callback
    override fun onPositiveButtonClickListener() {
        TODO("Not yet implemented")
    }

    //Dialog callback
    override fun onNegativeButtonClickListener() {
        TODO("Not yet implemented")
    }

    //RecyclerView callback
    override fun onItemViewClickListener(category: CategoryModel) {
        TODO("Not yet implemented")
    }

    //RecyclerView callback
    override fun onEditCategoryButtonClickListener(category: CategoryModel) {
        TODO("Not yet implemented")
    }

    //RecyclerView callback
    override fun onDeleteCategoryButtonViewClickListener(category: CategoryModel) {
        TODO("Not yet implemented")
    }
}