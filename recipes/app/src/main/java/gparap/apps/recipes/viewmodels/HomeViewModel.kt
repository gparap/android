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
package gparap.apps.recipes.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gparap.apps.recipes.api.RecipeService
import gparap.apps.recipes.data.RecipeModel
import gparap.apps.recipes.data.RecipeResponseModel
import gparap.apps.recipes.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private var featuredRecipesLiveData = MutableLiveData<List<RecipeModel>>()
    private var randomFeaturedRecipeLiveData = MutableLiveData<RecipeModel>()

    /** Consume the web service to fetch featured recipes from the API. */
    fun getFeaturedRecipes() {
        RecipeService.create().getFeaturedRecipes().enqueue(object : Callback<RecipeResponseModel> {
            override fun onResponse(
                call: Call<RecipeResponseModel>,
                response: Response<RecipeResponseModel>
            ) {
                featuredRecipesLiveData.value = response.body()?.recipes

                //get a random featured recipe
                val randomRecipes = response.body()?.recipes
                randomFeaturedRecipeLiveData.value = randomRecipes?.random()
            }

            override fun onFailure(call: Call<RecipeResponseModel>, t: Throwable) {
                t.message?.let { Log.d(AppConstants.RECIPES_LOG, it) }
            }
        })
    }

    fun getFeaturedRecipesLiveData() : LiveData<List<RecipeModel>> {
        return featuredRecipesLiveData
    }

    fun getRandomFeaturedRecipeLiveData() : LiveData<RecipeModel> {
        return randomFeaturedRecipeLiveData
    }
}