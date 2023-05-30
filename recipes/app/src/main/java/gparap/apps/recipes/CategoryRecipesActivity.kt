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
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.recipes.adapters.RecipeAdapter
import gparap.apps.recipes.data.RecipeModel
import gparap.apps.recipes.viewmodels.CategoryRecipesViewModel

class CategoryRecipesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_recipes)

        //get the view model for this activity
        val viewModel = ViewModelProvider(this)[CategoryRecipesViewModel::class.java]

        //..temp list of category's recipes
        val tempCategoryRecipes = ArrayList<RecipeModel>()
        tempCategoryRecipes.add(RecipeModel("1", "recipe 1", "description 1", "", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Ají_de_gallina_%28gourmet%29-b.jpg/290px-Ají_de_gallina_%28gourmet%29-b.jpg", "","","","","","","","",""))
        tempCategoryRecipes.add(RecipeModel("2", "recipe 2", "description 2", "", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Ají_de_gallina_%28gourmet%29-b.jpg/290px-Ají_de_gallina_%28gourmet%29-b.jpg", "","","","","","","","",""))
        tempCategoryRecipes.add(RecipeModel("3", "recipe 3", "description 3", "", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Ají_de_gallina_%28gourmet%29-b.jpg/290px-Ají_de_gallina_%28gourmet%29-b.jpg", "","","","","","","","",""))
        tempCategoryRecipes.add(RecipeModel("4", "recipe 4", "description 4", "", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/de/Ají_de_gallina_%28gourmet%29-b.jpg/290px-Ají_de_gallina_%28gourmet%29-b.jpg", "","","","","","","","",""))

        //setup recycler view with adapter
        val recyclerViewCategoryRecipes =
            findViewById<RecyclerView>(R.id.recycler_view_category_recipes)
        recyclerViewCategoryRecipes.layoutManager = LinearLayoutManager(this)
        val adapter = RecipeAdapter()
        adapter.setRecipes(tempCategoryRecipes)
        recyclerViewCategoryRecipes.adapter = adapter

        //observe recipe categories
        viewModel.getCategoryRecipesLiveData().observe(this) {
            adapter.setRecipes(it)
        }

        //display the category name
        val categoryName: String? = intent.getStringExtra("category_name")
        findViewById<TextView>(R.id.text_view_category_recipes_category_name).apply {
            this.text = categoryName
        }
    }
}