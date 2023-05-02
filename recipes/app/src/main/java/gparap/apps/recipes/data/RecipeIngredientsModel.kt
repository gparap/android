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

data class RecipeIngredientsModel(
    @SerializedName("i_1")
    val ingredient1: String,
    @SerializedName("i_2")
    val ingredient2: String,
    @SerializedName("i_3")
    val ingredient3: String,
    @SerializedName("i_4")
    val ingredient4: String,
    @SerializedName("i_5")
    val ingredient5: String,
    @SerializedName("i_6")
    val ingredient6: String,
    @SerializedName("i_7")
    val ingredient7: String,
    @SerializedName("i_8")
    val ingredient8: String,
    @SerializedName("i_9")
    val ingredient9: String,
    @SerializedName("i_0")
    val ingredient10: String,
    @SerializedName("i_x1")
    val ingredient11: String,
    @SerializedName("i_x2")
    val ingredient12: String,
    @SerializedName("i_x3")
    val ingredient13: String,
    @SerializedName("i_x4")
    val ingredient14: String,
    @SerializedName("i_x5")
    val ingredient15: String,
    @SerializedName("ph_i_x6")
    val placeholder1: String,
    @SerializedName("ph_i_x7")
    val placeholder2: String,
    @SerializedName("ph_i_x8")
    val placeholder3: String,
    @SerializedName("ph_i_x9")
    val placeholder4: String,
    @SerializedName("ph_i_x0")
    val placeholder5: String,
)
