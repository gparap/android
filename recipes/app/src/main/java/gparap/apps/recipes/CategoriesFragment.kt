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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.recipes.adapters.RecipeCategoryAdapter
import gparap.apps.recipes.viewmodels.CategoriesViewModel

class CategoriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        //get the ViewModel of this fragment
        val viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]

        //consume the web service to fetch recipe categories
        viewModel.getRecipeCategories()

        //setup the categories RecyclerView with adapter
        val categoriesRecyclerView =
            view.findViewById<RecyclerView>(R.id.recycle_view_recipe_categories)
        categoriesRecyclerView.layoutManager = GridLayoutManager(view.context, 2)
        val adapter = RecipeCategoryAdapter()
        categoriesRecyclerView.adapter = adapter

        //observe the recipe categories live data
        viewModel.getRecipeCategoriesLiveData().observe(viewLifecycleOwner) {
            adapter.setRecipeCategories(it)
        }

        //observe the loading progress
        viewModel.getLoadingProgressVisibility().observe(viewLifecycleOwner) {
            view.findViewById<ProgressBar>(R.id.progress_categories).visibility = it
        }

        //inflate the layout for this fragment
        return view
    }
}