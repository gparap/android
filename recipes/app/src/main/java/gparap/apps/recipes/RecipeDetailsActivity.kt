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
import android.util.Base64
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import gparap.apps.recipes.data.RecipeModel
import gparap.apps.recipes.utils.AppConstants
import gparap.apps.recipes.utils.Utils

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
            Picasso.get().load(recipe?.imageUrl)
                .placeholder(R.drawable.ic_image_placeholder_24)
                .into(this)
        }

        //display image license
        findViewById<WebView>(R.id.web_view_recipe_details_image_license).apply {
            val unencodedHtml = "<html><body style=\"font-size: small; font-style: oblique; " +
                    "text-align: center; \" >" + recipe?.imageAttribution + "<hr>" + "</body></html>";
            val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)
            this.loadData(encodedHtml, "text/html", "base64")
        }

        //display title
        findViewById<TextView>(R.id.text_view_recipe_details_title).apply {
            text = recipe?.title
        }

        //display category
        findViewById<TextView>(R.id.text_view_recipe_details_category).apply {
            text = recipe?.category
        }

        //display servings
        findViewById<TextView>(R.id.text_view_recipe_details_servings).apply {
            text = recipe?.servings
        }

        //display preparation time
        findViewById<TextView>(R.id.text_view_recipe_details_time).apply {
            text = recipe?.preparationTime
        }

        //display difficulty
        findViewById<TextView>(R.id.text_view_recipe_details_difficulty).apply {
            text = recipe?.difficulty
        }

        //display description
        findViewById<TextView>(R.id.text_view_recipe_details_desc).apply {
            text = recipe?.description
        }

        //display ingredient
        findViewById<TextView>(R.id.text_view_recipe_details_ingredients).apply {
            text = Utils.getOrderedListString(recipe?.ingredients)
        }

        //display steps
        findViewById<TextView>(R.id.text_view_recipe_details_steps).apply {
            text = Utils.getOrderedListString(recipe?.preparationSteps)
        }

        //display notes, if there are any notes
        if (!recipe?.preparationNotes.isNullOrEmpty()) {
            findViewById<TextView>(R.id.text_view_recipe_details_notes_label).apply {
                visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.text_view_recipe_details_notes).apply {
                visibility = View.VISIBLE
                text = Utils.getOrderedListString(recipe?.preparationNotes)
            }
        }

        //display text license
        findViewById<WebView>(R.id.web_view_recipe_details_text_license).apply {
            val unencodedHtml = "<html><body style=\"font-size: small; font-style: italic; " +
                    "text-align: justify;\" >" + "<hr>" + "Text is available under the " +
                    "<a href=\"https://creativecommons.org/licenses/by-sa/3.0/\">Creative Commons " +
                    "Attribution-ShareAlike License</a>" + "</body></html>";
            val encodedHtml = Base64.encodeToString(unencodedHtml.toByteArray(), Base64.NO_PADDING)
            this.loadData(encodedHtml, "text/html", "base64")
        }
    }
}