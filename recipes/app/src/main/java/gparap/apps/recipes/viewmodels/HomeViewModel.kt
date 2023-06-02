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

import android.content.SharedPreferences
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gparap.apps.recipes.api.RecipeService
import gparap.apps.recipes.data.RecipeModel
import gparap.apps.recipes.utils.AppConstants
import gparap.apps.recipes.utils.AppConstants.PREFS_TODAY
import gparap.apps.recipes.utils.AppConstants.PREFS_TODAY_RECIPE
import gparap.apps.recipes.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar
import java.util.TimeZone

class HomeViewModel : ViewModel() {
    private var featuredRecipesLiveData = MutableLiveData<List<RecipeModel>>()
    private var randomFeaturedRecipeLiveData = MutableLiveData<RecipeModel?>()
    private var progressBarVisibilityLiveData = MutableLiveData<Int>()

    /** Consume the web service to fetch featured recipes from the API. */
    fun getFeaturedRecipes(
        prefsRecipeOfTheDay: SharedPreferences?,
        prefsWhatDayIsToday: SharedPreferences?
    ) {
        setLoadingProgressVisibility(View.VISIBLE)
        RecipeService.create().getFeaturedRecipes().enqueue(object : Callback<List<RecipeModel>> {
            override fun onResponse(
                call: Call<List<RecipeModel>>,
                response: Response<List<RecipeModel>>
            ) {
                //get featured recipes
                featuredRecipesLiveData.value = response.body()
                val recipes = response.body()

                //get the shared preferences
                val recipeOfTheDay = prefsRecipeOfTheDay?.getString(PREFS_TODAY_RECIPE, "")
                val whatDayIsToday = prefsWhatDayIsToday?.getInt(PREFS_TODAY, 0)

                //handle the shared preferences for the 1st time running the app
                if (recipeOfTheDay!!.isEmpty()) {
                    setRecipeOfTheDay(recipes, arrayOf(prefsRecipeOfTheDay, prefsWhatDayIsToday))
                }
                //handle the shared preferences from the 2nd time running the app and onwards
                else {
                    //are we in the same day? - keep the same recipe
                    val calendar = Calendar.getInstance(TimeZone.getDefault())
                    if (whatDayIsToday == calendar.get(Calendar.DAY_OF_YEAR)) {
                        run recipe@{
                            recipes?.forEach {
                                if (it.title.equals(recipeOfTheDay)) {
                                    //set the recipe of the day value
                                    randomFeaturedRecipeLiveData.value = it
                                    return@recipe
                                }
                            }
                        }
                    }
                    //a day has passed - choose another recipe
                    else {
                        setRecipeOfTheDay(recipes, arrayOf(prefsRecipeOfTheDay, prefsWhatDayIsToday))
                    }
                }
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

    fun getRandomFeaturedRecipeLiveData(): MutableLiveData<RecipeModel?> {
        return randomFeaturedRecipeLiveData
    }

    fun getLoadingProgressVisibility(): MutableLiveData<Int> {
        return progressBarVisibilityLiveData
    }

    private fun setLoadingProgressVisibility(visibility: Int) {
        progressBarVisibilityLiveData.value = visibility
    }

    private fun setRecipeOfTheDay(recipes: List<RecipeModel>?, preferences: Array<SharedPreferences?>) {
        //get a calendar using the specified time zone and default locale
        val calendar = Calendar.getInstance(TimeZone.getDefault())

        //pick a random recipe
        val randomRecipe = recipes?.random()

        //save recipe of the day preference
        Utils.saveSharedPreferences(preferences[0], PREFS_TODAY_RECIPE, randomRecipe?.title)

        //save the today preference
        Utils.saveSharedPreferences(
            preferences[1], PREFS_TODAY, calendar.get(Calendar.DAY_OF_YEAR)
        )

        //set the recipe of the day value
        randomFeaturedRecipeLiveData.value = randomRecipe
    }
}