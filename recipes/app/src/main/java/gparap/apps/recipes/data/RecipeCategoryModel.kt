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

data class RecipeCategoryModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("desc")
    val description: String,
    @SerializedName("descAttribText")
    val descriptionAttribution: String,
    @SerializedName("descAttribLink")
    val descriptionAttributionLink: String,
    @SerializedName("img")
    val imageUri: String,
    @SerializedName("imgAttrib")
    val imageAttributionLink: String,
    @SerializedName("keywords")
    val keywords: String,
)