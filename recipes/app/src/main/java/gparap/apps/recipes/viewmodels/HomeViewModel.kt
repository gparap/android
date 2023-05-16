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
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gparap.apps.recipes.api.RecipeService
import gparap.apps.recipes.data.RecipeModel
import gparap.apps.recipes.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private var featuredRecipesLiveData = MutableLiveData<List<RecipeModel>>()
    private var randomFeaturedRecipeLiveData = MutableLiveData<RecipeModel>()
    private var progressBarVisibilityLiveData = MutableLiveData<Int>()

    /** Consume the web service to fetch featured recipes from the API. */
    fun getFeaturedRecipes() {
        setLoadingProgressVisibility(View.VISIBLE)
        RecipeService.create().getFeaturedRecipes().enqueue(object : Callback<List<RecipeModel>> {
            override fun onResponse(
                call: Call<List<RecipeModel>>,
                response: Response<List<RecipeModel>>
            ) {
                featuredRecipesLiveData.value = response.body()

                //get a random featured recipe
                val randomRecipes = response.body()
                randomFeaturedRecipeLiveData.value = randomRecipes?.random()

                setLoadingProgressVisibility(View.INVISIBLE)
            }

            override fun onFailure(call: Call<List<RecipeModel>>, t: Throwable) {
                t.message?.let { Log.d(AppConstants.RECIPES_LOG, it) }
                setLoadingProgressVisibility(View.INVISIBLE)
            }
        })
    }

    fun getFeaturedRecipesLiveData(): LiveData<List<RecipeModel>> {
        return featuredRecipesLiveData
    }

    fun getRandomFeaturedRecipeLiveData(): LiveData<RecipeModel> {
        return randomFeaturedRecipeLiveData
    }

    fun getLoadingProgressVisibility(): MutableLiveData<Int> {
        return progressBarVisibilityLiveData
    }

    private fun setLoadingProgressVisibility(visibility: Int) {
        progressBarVisibilityLiveData.value = visibility
    }
}