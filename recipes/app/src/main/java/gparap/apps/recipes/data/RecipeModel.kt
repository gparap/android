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
package gparap.apps.recipes.data

import com.google.gson.annotations.SerializedName

data class RecipeModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("descr")
    val description: String,
    @SerializedName("keywords")
    val keywords: String,
    @SerializedName("placeholder_r0")
    val placeholder0: String,
    @SerializedName("placeholder_r1")
    val placeholder1: String,

    @SerializedName("img")
    val image: List<RecipeImageModel>,

    @SerializedName("info")
    val information: List<RecipeInformationModel>,

    @SerializedName("ingreds")
    val ingredients: List<RecipeIngredientsModel>,

    @SerializedName("steps")
    val preparationSteps: List<RecipeStepsModel>,

    @SerializedName("notes")
    val preparationNotes: List<RecipeNotesModel>,
)
