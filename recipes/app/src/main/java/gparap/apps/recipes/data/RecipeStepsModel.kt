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

data class RecipeStepsModel(
    @SerializedName("s_1")
    val step1: String,
    @SerializedName("s_2")
    val step2: String,
    @SerializedName("s_3")
    val step3: String,
    @SerializedName("s_4")
    val step4: String,
    @SerializedName("s_5")
    val step5: String,
    @SerializedName("s_6")
    val step6: String,
    @SerializedName("s_7")
    val step7: String,
    @SerializedName("s_8")
    val step8: String,
    @SerializedName("s_9")
    val step9: String,
    @SerializedName("ph_s_x0")
    val placeholder0: String,
    @SerializedName("ph_s_x1")
    val placeholder1: String,
    @SerializedName("ph_s_x2")
    val placeholder2: String,
)
