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
package gparap.apps.movies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Model which contains all necessary attribution details for the movie's article.
 */
@Parcelize
data class ArticleModel(
    @SerializedName("title")
    val title: String,

    @SerializedName("link")
    val articleLink: String,

    @SerializedName("license")
    val licenseLink: String,

    @SerializedName("authors")
    val authorsLink: String,
) : Parcelable
