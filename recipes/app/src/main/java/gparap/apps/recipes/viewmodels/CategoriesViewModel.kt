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
import gparap.apps.recipes.data.RecipeCategoryModel
import gparap.apps.recipes.data.RecipeCategoryResponse
import gparap.apps.recipes.utils.AppConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoriesViewModel : ViewModel() {
    private var recipeCategoriesLiveData = MutableLiveData<ArrayList<RecipeCategoryModel>>()

    /** Consume the web service to fetch recipe categories from the API. */
    fun getRecipeCategories() {
        var categories: ArrayList<RecipeCategoryModel>
        RecipeService.create().getRecipeCategories()
            .enqueue(object : Callback<RecipeCategoryResponse> {
                override fun onResponse(
                    call: Call<RecipeCategoryResponse>,
                    response: Response<RecipeCategoryResponse>
                ) {
                    //get the recipe categories
                    categories = response.body()?.categories as ArrayList<RecipeCategoryModel>
                    recipeCategoriesLiveData.value = categories
                }

                override fun onFailure(call: Call<RecipeCategoryResponse>, t: Throwable) {
                    t.message?.let { Log.d(AppConstants.RECIPES_LOG, it) }
                }
            })
    }

    fun getRecipeCategoriesLiveData() : LiveData<ArrayList<RecipeCategoryModel>> {
        return recipeCategoriesLiveData
    }
}