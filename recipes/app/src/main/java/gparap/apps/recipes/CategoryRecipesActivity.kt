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
package gparap.apps.recipes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.recipes.adapters.RecipeAdapter
import gparap.apps.recipes.data.RecipeModel
import gparap.apps.recipes.utils.AppConstants
import gparap.apps.recipes.viewmodels.CategoryRecipesViewModel

class CategoryRecipesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_recipes)

        //get the category name from intent
        val categoryName: String? = intent.getStringExtra(AppConstants.CATEGORY_NAME_EXTRA)

        //get the view model for this activity
        val viewModel = ViewModelProvider(this)[CategoryRecipesViewModel::class.java]

        //setup recycler view with adapter
        val recyclerViewCategoryRecipes =
            findViewById<RecyclerView>(R.id.recycler_view_category_recipes)
        recyclerViewCategoryRecipes.layoutManager = LinearLayoutManager(this)
        val adapter = RecipeAdapter()
        recyclerViewCategoryRecipes.adapter = adapter

        //observe & display recipe categories
        viewModel.getCategoryRecipes(categoryName).observe(this) {
            adapter.setRecipes(it as ArrayList<RecipeModel>)
        }

        //observe the progress bar visibility
        viewModel.getLoadingProgressVisibility().observe(this) {
            this.findViewById<ProgressBar>(R.id.progress_category_recipes).visibility = it
        }

        //display the category name
        findViewById<TextView>(R.id.text_view_category_recipes_category_name).apply {
            this.text = categoryName
        }
    }
}