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
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.floatingactionbutton.FloatingActionButton
import gparap.apps.shopping_list.R
import gparap.apps.shopping_list.adapters.CategoryAdapter
import gparap.apps.shopping_list.data.CategoryModel
import gparap.apps.shopping_list.utils.AppConstants
import gparap.apps.shopping_list.utils.CategoryDialogUtils
import gparap.apps.shopping_list.viewmodels.CategoryActivityViewModel

class CategoryActivity : AppCompatActivity(), CategoryDialogUtils.DialogCallback,
    CategoryAdapter.CategoryAdapterCallback {
    private lateinit var dialog: AlertDialog
    private lateinit var viewModel: CategoryActivityViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //create the ViewModel of the activity
        viewModel = ViewModelProvider(this)[CategoryActivityViewModel::class.java]

        //setup RecyclerView with adapter for shopping categories
        val categoryRecyclerView = findViewById<RecyclerView>(R.id.recycler_view_categories)
        categoryRecyclerView.layoutManager = LinearLayoutManager(this)
        val categoryAdapter = CategoryAdapter(this)
        categoryRecyclerView.adapter = categoryAdapter

        //observe shopping categories
        viewModel.getShoppingCategories().observe(this, {
            categoryAdapter.categories = it as ArrayList<CategoryModel>
            categoryAdapter.notifyDataSetChanged()
        })

        //add shopping category
        val fabAddCategory = findViewById<FloatingActionButton>(R.id.fab_add_shopping_category)
        fabAddCategory.setOnClickListener {
            openAddShoppingCategoryDialog()
        }

        //initialize the Mobile Ads SDK
        MobileAds.initialize(this) {}

        //create an ad request
        val adRequest = AdRequest.Builder().build()

        //load and place banner ad
        val adView = findViewById<AdView>(R.id.ad_view_category)
        adView.loadAd(adRequest)
    }

    //Open dialog for adding a new shopping category
    @SuppressLint("InflateParams")
    private fun openAddShoppingCategoryDialog() {
        dialog = CategoryDialogUtils(this).createAddCategoryDialog(
            this.resources.getString(R.string.title_add_shopping_category),
            layoutInflater.inflate(R.layout.dialog_add_category, null),
            this
        ).apply { show() }
    }

    //Dialog callback for adding a new shopping category
    override fun onAddCategoryPositiveButtonClickListener() {
        viewModel.addShoppingCategory(
            dialog.findViewById<EditText>(R.id.edit_text_add_category_name)?.text.toString()
        )
    }

    //Dialog callback for editing an existing shopping category
    override fun onEditCategoryPositiveButtonClickListener(category: CategoryModel) {
        val editText = dialog.findViewById<EditText>(R.id.edit_text_edit_category_name)
        category.name = editText?.text.toString()
        viewModel.editShoppingCategory(category)
    }

    //Dialog callback for canceling action
    override fun onCategoryNegativeButtonClickListener() {
        return
    }

    //RecyclerView callback for showing the items of a category
    override fun onCategoryViewClickListener(category: CategoryModel) {
        val intent = Intent(this, ItemActivity::class.java)
        intent.putExtra(AppConstants.categoryId, category.id)
        intent.putExtra(AppConstants.categoryName, category.name)
        startActivity(intent)
    }

    //RecyclerView callback for opening dialog to edit an existing shopping category
    @SuppressLint("InflateParams")
    override fun onEditCategoryButtonClickListener(category: CategoryModel) {
        dialog = CategoryDialogUtils(this).createAddCategoryDialog(
            this.resources.getString(R.string.title_edit_shopping_category),
            layoutInflater.inflate(R.layout.dialog_edit_category, null),
            this,
            category
        ).apply {
            show()
        }.also {
            //display the category info
            val editText = it.findViewById<EditText>(R.id.edit_text_edit_category_name)
            editText?.setText(category.name)
        }
    }

    //RecyclerView callback for opening dialog to delete a shopping category
    override fun onDeleteCategoryButtonViewClickListener(category: CategoryModel) {
        AlertDialog.Builder(this)
            .setTitle(this.resources.getString(R.string.title_delete_shopping_category))
            .setMessage(this.resources.getString(R.string.text_delete_shopping_category))
            .setPositiveButton(this.resources.getString(R.string.dialog_button_ok)) { _, _ ->
                viewModel.deleteShoppingCategory(category)
            }
            .setNegativeButton(
                this.resources.getString(R.string.dialog_button_cancel),
                DialogInterface.OnClickListener { _, _ -> return@OnClickListener })
            .create().also {
                it.show()
            }
    }
}