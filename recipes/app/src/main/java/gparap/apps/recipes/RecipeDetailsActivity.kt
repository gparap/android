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

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import gparap.apps.recipes.data.RecipeModel
import gparap.apps.recipes.utils.AppConstants

class RecipeDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)

        //get recipe data from intent
        val recipe: RecipeModel? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(AppConstants.RECIPE_PARCELABLE_EXTRA, RecipeModel::class.java)
        } else {
            intent.getParcelableExtra(AppConstants.RECIPE_PARCELABLE_EXTRA)
        }

        //display image
        findViewById<ImageView>(R.id.image_view_recipe_details).apply {
            Picasso.get().load(recipe?.image?.get(0)?.url)
                .placeholder(R.drawable.ic_image_placeholder_24)
                .into(this)
        }

        //TODO: licence

        //display title
        findViewById<TextView>(R.id.text_view_recipe_details_title).apply {
            text = recipe?.title
        }

        //display category
        findViewById<TextView>(R.id.text_view_recipe_details_category).apply {
            text = recipe?.information?.get(0)?.category
        }

        //display servings
        findViewById<TextView>(R.id.text_view_recipe_details_servings).apply {
            text = recipe?.information?.get(0)?.servings
        }

        //display preparation time
        findViewById<TextView>(R.id.text_view_recipe_details_time).apply {
            text = recipe?.information?.get(0)?.preparationTime
        }

        //display difficulty
        findViewById<TextView>(R.id.text_view_recipe_details_difficulty).apply {
            text = recipe?.information?.get(0)?.difficulty
        }

        //display description
        findViewById<TextView>(R.id.text_view_recipe_details_desc).apply {
            text = recipe?.description
        }

        //display ingredient    TODO:utils
        findViewById<TextView>(R.id.text_view_recipe_details_ingredients).apply {
            text = recipe?.ingredients?.get(0)?.ingredient1.plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient2).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient3).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient4).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient5).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient6).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient7).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient8).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient9).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient10).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient11).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient12).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient13).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient14).plus('\n')
                .plus(recipe?.ingredients?.get(0)?.ingredient15).trimEnd()
        }

        //display steps TODO:utils
        findViewById<TextView>(R.id.text_view_recipe_details_steps).apply {
            text = recipe?.preparationSteps?.get(0)?.step1.plus('\n')
                .plus(recipe?.preparationSteps?.get(0)?.step2).plus('\n')
                .plus(recipe?.preparationSteps?.get(0)?.step3).plus('\n')
                .plus(recipe?.preparationSteps?.get(0)?.step4).plus('\n')
                .plus(recipe?.preparationSteps?.get(0)?.step5).plus('\n')
                .plus(recipe?.preparationSteps?.get(0)?.step6).plus('\n')
                .plus(recipe?.preparationSteps?.get(0)?.step7).plus('\n')
                .plus(recipe?.preparationSteps?.get(0)?.step8).plus('\n')
                .plus(recipe?.preparationSteps?.get(0)?.step9).trimEnd()
        }

        //display notes, if there are any notes TODO:utils
        if (recipe?.preparationNotes?.get(0)?.note1?.isNotEmpty() == true) {
            findViewById<TextView>(R.id.text_view_recipe_details_notes_label).apply {
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.text_view_recipe_details_notes).apply {
                visibility = View.VISIBLE
                text = recipe.preparationNotes[0].note1.plus('\n')
                    .plus(recipe.preparationNotes[0].note2).plus('\n')
                    .plus(recipe.preparationNotes[0].note3).plus('\n')
                    .plus(recipe.preparationNotes[0].note4).plus('\n')
                    .plus(recipe.preparationNotes[0].note5).plus('\n')
                    .plus(recipe.preparationNotes[0].note6).plus('\n')
                    .plus(recipe.preparationNotes[0].note7).plus('\n')
                    .plus(recipe.preparationNotes[0].note8).plus('\n')
                    .plus(recipe.preparationNotes[0].note9).trimEnd()
            }
        }
    }
}