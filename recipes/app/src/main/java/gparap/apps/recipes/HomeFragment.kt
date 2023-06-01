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

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gparap.apps.recipes.adapters.RecipeAdapter
import gparap.apps.recipes.data.RecipeModel
import gparap.apps.recipes.utils.AppConstants
import gparap.apps.recipes.viewmodels.HomeViewModel

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //get the SharedPreferences
        val prefsRecipeOfTheDay = this.context?.getSharedPreferences("recipe_of_the_day", Context.MODE_PRIVATE)
        val prefsWhatDayIsToday = this.context?.getSharedPreferences("what_day_is_today", Context.MODE_PRIVATE)

        //get the ViewModel of this fragment
        val viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        //consume the web service to fetch featured recipes
        viewModel.getFeaturedRecipes(prefsRecipeOfTheDay, prefsWhatDayIsToday)

        //setup the featured recipes RecyclerView with adapter
        val featureRecipes = view.findViewById<RecyclerView>(R.id.recycler_view_featured_recipes)
        featureRecipes.layoutManager = LinearLayoutManager(this.context)
        val adapter = RecipeAdapter()
        featureRecipes.adapter = adapter

        //observe the featured recipes live data
        viewModel.getFeaturedRecipesLiveData().observe(viewLifecycleOwner) {
            adapter.setRecipes(it as ArrayList<RecipeModel>)
        }

        //observe the random featured recipe live data
        var recipeModel: RecipeModel? = null
        viewModel.getRandomFeaturedRecipeLiveData().observe(viewLifecycleOwner) {
            Picasso.get()
                .load(it?.imageUrl)
                .placeholder(R.drawable.ic_image_placeholder_24)
                .into(view.findViewById<ImageView>(R.id.image_view_random_recipe))

            recipeModel = it
        }.also {
            //open recipe in its details activity
            view.findViewById<ImageView>(R.id.image_view_random_recipe).setOnClickListener {
                val intent = Intent(context, RecipeDetailsActivity::class.java)
                intent.putExtra(AppConstants.RECIPE_PARCELABLE_EXTRA, recipeModel)
                startActivity(intent)
            }
        }

        //observe the loading progress
        viewModel.getLoadingProgressVisibility().observe(viewLifecycleOwner) {
            view.findViewById<ProgressBar>(R.id.progress_home).visibility = it
        }

        //return the layout for this fragment
        return view
    }
}