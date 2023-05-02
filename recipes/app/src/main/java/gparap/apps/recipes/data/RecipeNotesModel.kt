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

data class RecipeNotesModel(
    @SerializedName("n_1")
    val note1: String,
    @SerializedName("n_2")
    val note2: String,
    @SerializedName("n_3")
    val note3: String,
    @SerializedName("n_4")
    val note4: String,
    @SerializedName("n_5")
    val note5: String,
    @SerializedName("n_6")
    val note6: String,
    @SerializedName("n_7")
    val note7: String,
    @SerializedName("n_8")
    val note8: String,
    @SerializedName("n_9")
    val note9: String,
    @SerializedName("ph_n_x0")
    val placeholder0: String,
    @SerializedName("ph_n_x1")
    val placeholder1: String,
    @SerializedName("ph_n_x2")
    val placeholder2: String,
)
