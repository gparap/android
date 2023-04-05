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
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import gparap.apps.recipes.adapters.RecipeCategoryAdapter
import gparap.apps.recipes.api.RecipeService
import gparap.apps.recipes.data.RecipeCategoryModel
import gparap.apps.recipes.data.RecipeCategoryResponse
import gparap.apps.recipes.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_categories, container, false)

        //consume the web service to fetch recipe categories
        var categories: ArrayList<RecipeCategoryModel>
        RecipeService.create().getRecipeCategories()
            .enqueue(object : Callback<RecipeCategoryResponse> {
                override fun onResponse(
                    call: Call<RecipeCategoryResponse>,
                    response: Response<RecipeCategoryResponse>
                ) {
                    //get the recipe categories
                    categories = response.body()?.categories as ArrayList<RecipeCategoryModel>

                    //setup the categories RecyclerView with adapter
                    val categoriesRecyclerView =
                        view.findViewById<RecyclerView>(R.id.recycle_view_recipe_categories)
                    categoriesRecyclerView.layoutManager = GridLayoutManager(view.context, 2)
                    val adapter = RecipeCategoryAdapter()
                    adapter.recipeCategories = categories
                    categoriesRecyclerView.adapter = adapter
                }

                override fun onFailure(call: Call<RecipeCategoryResponse>, t: Throwable) {
                    t.message?.let { Log.d(AppConstants.RECIPES_LOG, it) }
                }
            })

        //inflate the layout for this fragment
        return view
    }
}