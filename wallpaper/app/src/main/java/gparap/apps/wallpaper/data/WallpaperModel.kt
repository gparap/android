/*
 * Copyright (c) 2022 gparap
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
package gparap.apps.wallpaper.data

import com.google.gson.annotations.SerializedName

/**
 * Model class for a wallpaper (includes Creator and Tag models).
 */
data class WallpaperModel(
    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val imageUrl: String,

    @SerializedName("alt")
    val title: String,

    @SerializedName("sort")
    val sortingBy: String,

    @SerializedName("category")
    val category: String,

    @SerializedName("tags")
    val filterTags: List<TagModel>,

    @SerializedName("artist")
    val creator: List<CreatorModel>,
)
