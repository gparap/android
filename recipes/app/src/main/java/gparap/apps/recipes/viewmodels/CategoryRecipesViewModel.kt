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

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gparap.apps.recipes.api.RecipeService
import gparap.apps.recipes.data.RecipeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryRecipesViewModel : ViewModel() {
    private var categoryRecipesLiveData = MutableLiveData<ArrayList<RecipeModel>>()
    private var progressBarVisibilityLiveData = MutableLiveData<Int>()

    /** Consume the web service to fetch recipe categories from the API. */
    fun getCategoryRecipes(category: String?): LiveData<ArrayList<RecipeModel>> {
        progressBarVisibilityLiveData.value = View.VISIBLE
        RecipeService.create().getCategoryRecipes(category).enqueue(object :
            Callback<List<RecipeModel>> {
            override fun onResponse(
                call: Call<List<RecipeModel>>,
                response: Response<List<RecipeModel>>
            ) {
                val recipes: ArrayList<RecipeModel> = response.body() as ArrayList<RecipeModel>
                categoryRecipesLiveData.value = recipes
                progressBarVisibilityLiveData.value = View.INVISIBLE
            }

            override fun onFailure(call: Call<List<RecipeModel>>, t: Throwable) {
                progressBarVisibilityLiveData.value = View.INVISIBLE
                println(t.message.toString())   //DEBUG
            }
        })
        return categoryRecipesLiveData
    }

    fun getLoadingProgressVisibility(): MutableLiveData<Int> {
        return progressBarVisibilityLiveData
    }
}